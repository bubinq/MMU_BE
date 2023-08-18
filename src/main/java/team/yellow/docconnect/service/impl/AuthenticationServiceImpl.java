package team.yellow.docconnect.service.impl;

import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.Context;
import team.yellow.docconnect.entity.ConfirmationToken;
import team.yellow.docconnect.entity.Role;
import team.yellow.docconnect.entity.User;
import team.yellow.docconnect.exception.HealthCareAPIException;
import team.yellow.docconnect.exception.ResourceNotFoundException;
import team.yellow.docconnect.payload.dto.*;
import team.yellow.docconnect.repository.ConfirmationTokenRepository;
import team.yellow.docconnect.repository.RoleRepository;
import team.yellow.docconnect.repository.UserRepository;
import team.yellow.docconnect.security.GoogleTokenDecoder;
import team.yellow.docconnect.security.JwtTokenProvider;
import team.yellow.docconnect.service.AuthenticationService;
import team.yellow.docconnect.service.ConfirmationTokenService;
import team.yellow.docconnect.service.EmailBuilderService;
import team.yellow.docconnect.service.EmailService;
import team.yellow.docconnect.utils.Messages;

import java.io.IOException;
import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Optional;
import java.util.Set;
import java.util.UUID;

@Service
public class AuthenticationServiceImpl implements AuthenticationService {

    private final AuthenticationManager authenticationManager;
    private final UserRepository userRepository;
    private final RoleRepository roleRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtTokenProvider jwtTokenProvider;
    private final GoogleTokenDecoder googleTokenDecoder;
    private final EmailBuilderService emailBuilderService;
    private final EmailService emailService;
    private final ConfirmationTokenService confirmationTokenService;
    private final TemplateEngine templateEngine;
    private final ConfirmationTokenRepository confirmationTokenRepository;

    public AuthenticationServiceImpl(AuthenticationManager authenticationManager, UserRepository userRepository, RoleRepository roleRepository, PasswordEncoder passwordEncoder, JwtTokenProvider jwtTokenProvider, GoogleTokenDecoder googleTokenDecoder, EmailBuilderService emailBuilderService, EmailService emailService, ConfirmationTokenService confirmationTokenService, TemplateEngine templateEngine, ConfirmationTokenRepository confirmationTokenRepository) {
        this.authenticationManager = authenticationManager;
        this.userRepository = userRepository;
        this.roleRepository = roleRepository;
        this.passwordEncoder = passwordEncoder;
        this.jwtTokenProvider = jwtTokenProvider;
        this.googleTokenDecoder = googleTokenDecoder;
        this.emailBuilderService = emailBuilderService;
        this.emailService = emailService;
        this.confirmationTokenService = confirmationTokenService;
        this.templateEngine = templateEngine;
        this.confirmationTokenRepository = confirmationTokenRepository;
    }

    @Override
    public String login(LoginDto loginDto) {
        Authentication authentication =
                authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(loginDto.email(), loginDto.password()));
        SecurityContextHolder.getContext().setAuthentication(authentication);
        return jwtTokenProvider.generateToken(authentication);
    }

    @Override
    public String register(RegisterDto registerDto) {
        if (userRepository.existsByEmailIgnoreCase(registerDto.email())) {
            throw new HealthCareAPIException(HttpStatus.BAD_REQUEST, Messages.EMAIL_EXISTS);
        }

        User user = buildUser(registerDto);
        userRepository.save(user);
        String token = confirmationTokenService.createNewConfirmationToken(user);
        emailService.sendMail("Email Confirmation", registerDto.email(),
                emailBuilderService.buildConfirmationMail(registerDto.firstName(),
                        "http://localhost:8080/api/v1/auth/confirm?token=" + token));
        return Messages.USER_SUCCESSFULLY_REGISTERED;
    }

    @Override
    public String googleSignIn(String token) throws IOException {
        GoogleUserDto googleUserDto = googleTokenDecoder.decodeGoogleToken(token);

        String randomPassword = UUID.randomUUID().toString();
        String encodedPassword = new BCryptPasswordEncoder().encode(randomPassword);

        User user = new User();

        if (userRepository.existsByEmailIgnoreCase(googleUserDto.email())) {
            user = userRepository.findUserByEmailIgnoreCase(googleUserDto.email())
                    .orElseThrow(() -> new ResourceNotFoundException("User", "Email", googleUserDto.email()));

        }
        user.setEmail(googleUserDto.email());
        user.setFirstName(googleUserDto.given_name());
        user.setLastName(googleUserDto.last_name());
        user.setPassword(encodedPassword);
        user.setIsVerified(true);
        userRepository.save(setRoles(user));

        user = userRepository.findUserByEmailIgnoreCase(googleUserDto.email())
                .orElseThrow(() -> new ResourceNotFoundException("User", "Email", googleUserDto.email()));

        Authentication authentication = authenticationManager
                .authenticate(new UsernamePasswordAuthenticationToken(user.getEmail(), randomPassword));

        SecurityContextHolder.getContext().setAuthentication(authentication);
        return jwtTokenProvider.generateToken(authentication);

    }

    @Override
    public String changePassword(ChangePasswordDto changePasswordDto, String token) {
        Long userId = confirmationTokenRepository.findUserIdByToken(token);

        User user = userRepository.findById(userId)
                .orElseThrow(() -> new ResourceNotFoundException("User", "id", userId));

        user.setPassword(passwordEncoder.encode(changePasswordDto.password()));
        userRepository.save(user);
        return "Congratulations!" + "\nYour password has been successfully reset.";
    }

    @Override
    public void forgotPassword(ForgotPasswordDto forgotPasswordDto) {
        User userToResetPassword = userRepository
                .findUserByEmailIgnoreCase(forgotPasswordDto.email())
                .orElseThrow(() -> new ResourceNotFoundException("User", "email", forgotPasswordDto.email()));

        // Optional ---- TO DELETE
        if (!userToResetPassword.getIsVerified()) {
            throw new HealthCareAPIException(HttpStatus.BAD_REQUEST, "User email is not verified!");
        }

        String userEmail = userToResetPassword.getEmail();
        String token = createNewPasswordResetToken(userToResetPassword);

        checkPasswordResetTokenIsValid(token);

        String confirmationLink = "http://localhost:8080/api/v1/auth/reset?token=" + token;
        Context context = getContext(userToResetPassword, confirmationLink);

        emailService.sendMail("Email Reset Password", userEmail, templateEngine.process("email-forgot-password", context));
    }

    private User setRoles(User user) {
        Set<Role> roles = new HashSet<>();
        Optional<Role> userRole = roleRepository.findByName("ROLE_USER");
        Role role = new Role();
        if (userRole.isPresent()) {
            role = userRole.get();
        }
        roles.add(role);
        user.setRoles(roles);
        return user;
    }

    private User buildUser(RegisterDto registerDto) {
        User user = new User();
        user.setFirstName(registerDto.firstName());
        user.setLastName(registerDto.lastName());
        user.setPassword(passwordEncoder.encode(registerDto.password()));
        user.setEmail(registerDto.email());
        return setRoles(user);
    }

    private String createNewPasswordResetToken(User user) {
        String token = UUID.randomUUID().toString();
        ConfirmationToken confirmationToken = new ConfirmationToken();
        confirmationToken.setToken(token);
        confirmationToken.setCreatedAt(LocalDateTime.now());
        confirmationToken.setExpiresAt(LocalDateTime.now().plusMinutes(60));
        confirmationToken.setUser(user);
        confirmationTokenService.saveConfirmationToken(confirmationToken);
        return token;
    }

    private void checkPasswordResetTokenIsValid(String token) {
        Optional<ConfirmationToken> confirmationToken = confirmationTokenRepository.findByToken(token);
        ConfirmationToken confirmToken = new ConfirmationToken();
        if (confirmationToken.isPresent()) {
            confirmToken = confirmationToken.get();
        }
        if (confirmToken.getExpiresAt().isBefore(LocalDateTime.now())) {
            throw new HealthCareAPIException(HttpStatus.BAD_REQUEST, "The token has expired or is invalid");
        }
    }

    private Context getContext(User user, String confirmationLink) {
        Context context = new Context();
        context.setVariable("firstName", user.getFirstName());
        context.setVariable("lastName", user.getLastName());
        context.setVariable("link", confirmationLink);
        return context;
    }
}
