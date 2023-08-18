package team.yellow.docconnect.service.impl;

import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import team.yellow.docconnect.entity.Role;
import team.yellow.docconnect.entity.User;
import team.yellow.docconnect.exception.HealthCareAPIException;
import team.yellow.docconnect.exception.ResourceNotFoundException;
import team.yellow.docconnect.payload.dto.GoogleUserDto;
import team.yellow.docconnect.payload.dto.LoginDto;
import team.yellow.docconnect.payload.dto.RegisterDto;
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


    public AuthenticationServiceImpl(AuthenticationManager authenticationManager, UserRepository userRepository, RoleRepository roleRepository, PasswordEncoder passwordEncoder, JwtTokenProvider jwtTokenProvider, GoogleTokenDecoder googleTokenDecoder, EmailBuilderService emailBuilderService, EmailService emailService, ConfirmationTokenService confirmationTokenService) {
        this.authenticationManager = authenticationManager;
        this.userRepository = userRepository;
        this.roleRepository = roleRepository;
        this.passwordEncoder = passwordEncoder;
        this.jwtTokenProvider = jwtTokenProvider;
        this.googleTokenDecoder = googleTokenDecoder;
        this.emailBuilderService = emailBuilderService;
        this.emailService = emailService;
        this.confirmationTokenService = confirmationTokenService;
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

        if(userRepository.existsByEmailIgnoreCase(googleUserDto.email())){
            user = userRepository.findUserByEmailIgnoreCase(googleUserDto.email())
                    .orElseThrow( () ->new ResourceNotFoundException("User", "Email", googleUserDto.email()));

        }
        user.setEmail(googleUserDto.email());
        user.setFirstName(googleUserDto.given_name());
        user.setLastName(googleUserDto.last_name());
        user.setPassword(encodedPassword);
        user.setIsVerified(true);
        userRepository.save(setRoles(user));

        user = userRepository.findUserByEmailIgnoreCase(googleUserDto.email())
                .orElseThrow( () ->new ResourceNotFoundException("User", "Email", googleUserDto.email()));

        Authentication authentication = authenticationManager
                        .authenticate(new UsernamePasswordAuthenticationToken(user.getEmail(), randomPassword));

        SecurityContextHolder.getContext().setAuthentication(authentication);
        return jwtTokenProvider.generateToken(authentication);

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
}
