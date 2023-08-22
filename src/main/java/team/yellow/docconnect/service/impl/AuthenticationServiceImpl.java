package team.yellow.docconnect.service.impl;

import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
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
import team.yellow.docconnect.utils.PropertyVariables;
import team.yellow.docconnect.utils.TokenName;

import java.io.IOException;
import java.util.UUID;

@Service
@AllArgsConstructor
public class AuthenticationServiceImpl implements AuthenticationService {

    private final AuthenticationManager authenticationManager;
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtTokenProvider jwtTokenProvider;
    private final GoogleTokenDecoder googleTokenDecoder;
    private final EmailBuilderService emailBuilderService;
    private final EmailService emailService;
    private final ConfirmationTokenService confirmationTokenService;
    private final ConfirmationTokenRepository confirmationTokenRepository;
    private final AuthenticationServiceHelper authenticationServiceHelper;
    private final PropertyVariables propertyVariables;


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

        String token = confirmationTokenService.createNewConfirmationToken(user, TokenName.VERIFICATION.getName());

        emailService.sendMail("Email Confirmation", registerDto.email(),
                emailBuilderService.buildConfirmationMail(registerDto.firstName(),
                        propertyVariables.getEmailUri() + token));

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

        confirmationTokenService.validateConfirmationToken(token, TokenName.RESET.getName());

        user.setPassword(passwordEncoder.encode(changePasswordDto.password()));
        userRepository.save(user);
        return Messages.SUCCESSFULLY_PASSWORD_RESET;
    }

    @Override
    public void forgotPassword(ForgotPasswordDto forgotPasswordDto) {
        User userToResetPassword = userRepository
                .findUserByEmailIgnoreCase(forgotPasswordDto.email())
                .orElseThrow(() -> new ResourceNotFoundException("User", "email", forgotPasswordDto.email()));

        if (!userToResetPassword.getIsEmailVerified()) {
            throw new HealthCareAPIException(HttpStatus.BAD_REQUEST, "User email is not verified!");
        }

        String userEmail = userToResetPassword.getEmail();
        confirmationTokenService.checkForPendingTokens(userToResetPassword, TokenName.RESET.getName());

        String token = confirmationTokenService.createNewConfirmationToken(userToResetPassword, TokenName.RESET.getName());

        authenticationServiceHelper.checkConfirmationTokenIsValid(token);

        String confirmationLink = propertyVariables.getPasswordUri() + token;
        emailService.sendMail("Email Reset Password", userEmail, emailBuilderService
                .buildResetPasswordMail(userToResetPassword, confirmationLink));
    }

    @Override
    public String resendForgotPassword(String token) {
        Long userId = confirmationTokenRepository.findUserIdByToken(token);

        User user = userRepository.findById(userId)
                .orElseThrow(() -> new ResourceNotFoundException("User", "id", userId));

        confirmationTokenService.checkForPendingTokens(user, TokenName.RESET.getName());

        String newToken = confirmationTokenService.createNewConfirmationToken(user, TokenName.RESET.getName());

        authenticationServiceHelper.checkConfirmationTokenIsValid(newToken);

        String confirmationLink = propertyVariables.getPasswordUri() + newToken;
        emailService.sendMail("Email Reset Password", user.getEmail(), emailBuilderService
                .buildResetPasswordMail(user, confirmationLink));

        return Messages.SUCCESSFULLY_RESEND_FORGOT_PASSWORD;
    }

    @Override
    public String sendEmailVerification(Long userId) {
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new ResourceNotFoundException("User", "id", userId));

        if (user.getIsEmailVerified()) {
            throw new HealthCareAPIException(HttpStatus.BAD_REQUEST, Messages.EMAIL_ALREADY_VERIFIED);
        }

        confirmationTokenService.checkForPendingTokens(user, TokenName.VERIFICATION.getName());

        String newToken = confirmationTokenService.createNewConfirmationToken(user, TokenName.VERIFICATION.getName());
        authenticationServiceHelper.checkEmailVerificationTokenIsValid(newToken);

        emailService.sendMail("Email Confirmation", user.getEmail(),
                emailBuilderService.buildConfirmationMail(user.getFirstName(),
                        propertyVariables.getEmailUri() + newToken));
        return Messages.SUCCESSFULLY_RESEND_VERIFICATION_EMAIL;
    }

    @Override
    public String verifyEmail(String token) {
        confirmationTokenService.validateConfirmationToken(token, TokenName.VERIFICATION.getName());

        Long userId = confirmationTokenRepository.findUserIdByToken(token);
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new ResourceNotFoundException("User", "id", userId));

        userRepository.confirmEmail(user.getEmail());
        userRepository.save(user);

        return Messages.SUCCESSFULLY_VERIFIED_EMAIL;
    }

}
