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
import team.yellow.docconnect.entity.User;
import team.yellow.docconnect.exception.HealthCareAPIException;
import team.yellow.docconnect.exception.ResourceNotFoundException;
import team.yellow.docconnect.payload.dto.*;
import team.yellow.docconnect.repository.ConfirmationTokenRepository;
import team.yellow.docconnect.repository.UserRepository;
import team.yellow.docconnect.security.GoogleTokenDecoder;
import team.yellow.docconnect.security.JwtTokenProvider;
import team.yellow.docconnect.service.AuthenticationService;
import team.yellow.docconnect.service.ConfirmationTokenService;
import team.yellow.docconnect.service.EmailBuilderService;
import team.yellow.docconnect.service.EmailService;
import team.yellow.docconnect.service.helper.AuthenticationServiceHelper;
import team.yellow.docconnect.utils.Messages;

import java.io.IOException;
import java.util.UUID;

@Service
public class AuthenticationServiceImpl implements AuthenticationService {

    private final AuthenticationManager authenticationManager;
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtTokenProvider jwtTokenProvider;
    private final GoogleTokenDecoder googleTokenDecoder;
    private final EmailBuilderService emailBuilderService;
    private final EmailService emailService;
    private final ConfirmationTokenService confirmationTokenService;
    private final TemplateEngine templateEngine;
    private final ConfirmationTokenRepository confirmationTokenRepository;
    private final AuthenticationServiceHelper authenticationServiceHelper;

    public AuthenticationServiceImpl(AuthenticationManager authenticationManager, UserRepository userRepository, PasswordEncoder passwordEncoder, JwtTokenProvider jwtTokenProvider, GoogleTokenDecoder googleTokenDecoder, EmailBuilderService emailBuilderService, EmailService emailService, ConfirmationTokenService confirmationTokenService, TemplateEngine templateEngine, ConfirmationTokenRepository confirmationTokenRepository, AuthenticationServiceHelper authenticationServiceHelper) {
        this.authenticationManager = authenticationManager;
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
        this.jwtTokenProvider = jwtTokenProvider;
        this.googleTokenDecoder = googleTokenDecoder;
        this.emailBuilderService = emailBuilderService;
        this.emailService = emailService;
        this.confirmationTokenService = confirmationTokenService;
        this.templateEngine = templateEngine;
        this.confirmationTokenRepository = confirmationTokenRepository;
        this.authenticationServiceHelper = authenticationServiceHelper;
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

        User user = authenticationServiceHelper.buildNormalUser(registerDto);
        userRepository.save(user);
        String token = confirmationTokenService.createNewConfirmationToken(user);
        emailService.sendMail("Email Confirmation", registerDto.email(),
                emailBuilderService.buildConfirmationMail(registerDto.firstName(),
                        "http://localhost:5173/auth/confirm?token=" + token));
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
        user.setIsEmailVerified(true);
        user.setIsOver18(true);
        user.setPrivacy_policy_agreement(true);
        user.setUser_agreement(true);
        user.setIs_deleted(false);
        userRepository.save(authenticationServiceHelper.setRoles(user));

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
        return Messages.SUCCESSFULLY_PASSWORD_RESET;
    }

    @Override
    public void forgotPassword(ForgotPasswordDto forgotPasswordDto) {
        User userToResetPassword = userRepository
                .findUserByEmailIgnoreCase(forgotPasswordDto.email())
                .orElseThrow(() -> new ResourceNotFoundException("User", "email", forgotPasswordDto.email()));

        // Optional ---- TO DELETE
        if (!userToResetPassword.getIsEmailVerified()) {
            throw new HealthCareAPIException(HttpStatus.BAD_REQUEST, "User email is not verified!");
        }

        String userEmail = userToResetPassword.getEmail();
        String token = authenticationServiceHelper.createNewPasswordResetToken(userToResetPassword);

        authenticationServiceHelper.checkPasswordResetTokenIsValid(token);

        String confirmationLink = "http://localhost:5173/auth/reset?token=" + token;
        Context context = authenticationServiceHelper.getForgotPasswordContext(userToResetPassword, confirmationLink);

        emailService.sendMail("Email Reset Password", userEmail, templateEngine.process("email-forgot-password", context));
    }
}
