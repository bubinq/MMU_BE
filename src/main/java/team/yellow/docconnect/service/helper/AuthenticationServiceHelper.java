package team.yellow.docconnect.service.helper;

import org.springframework.http.HttpStatus;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;
import org.thymeleaf.context.Context;
import team.yellow.docconnect.entity.ConfirmationToken;
import team.yellow.docconnect.entity.Role;
import team.yellow.docconnect.entity.User;
import team.yellow.docconnect.exception.HealthCareAPIException;
import team.yellow.docconnect.payload.dto.RegisterDto;
import team.yellow.docconnect.repository.ConfirmationTokenRepository;
import team.yellow.docconnect.repository.RoleRepository;
import team.yellow.docconnect.service.ConfirmationTokenService;
import team.yellow.docconnect.utils.Messages;

import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Optional;
import java.util.Set;
import java.util.UUID;

@Component
public class AuthenticationServiceHelper {

    private final RoleRepository roleRepository;
    private final PasswordEncoder passwordEncoder;
    private final ConfirmationTokenService confirmationTokenService;
    private final ConfirmationTokenRepository confirmationTokenRepository;

    public AuthenticationServiceHelper(RoleRepository roleRepository, PasswordEncoder passwordEncoder, ConfirmationTokenService confirmationTokenService, ConfirmationTokenRepository confirmationTokenRepository) {
        this.roleRepository = roleRepository;
        this.passwordEncoder = passwordEncoder;
        this.confirmationTokenService = confirmationTokenService;
        this.confirmationTokenRepository = confirmationTokenRepository;
    }

    public User setRoles(User user) {
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

    public User buildNormalUser(RegisterDto registerDto) {
        User user = new User();
        user.setFirstName(registerDto.firstName());
        user.setLastName(registerDto.lastName());
        user.setPassword(passwordEncoder.encode(registerDto.password()));
        user.setEmail(registerDto.email());
        user.setIsEmailVerified(false);
        user.setIsOver18(false);
        user.setPrivacy_policy_agreement(true);
        user.setUser_agreement(true);
        user.setIs_deleted(false);
        return setRoles(user);
    }

    public String createNewPasswordResetToken(User user) {
        String token = UUID.randomUUID().toString();
        ConfirmationToken confirmationToken = new ConfirmationToken();
        confirmationToken.setToken(token);
        confirmationToken.setCreatedAt(LocalDateTime.now());
        confirmationToken.setExpiresAt(LocalDateTime.now().plusMinutes(60));
        confirmationToken.setUser(user);
        confirmationTokenService.saveConfirmationToken(confirmationToken);
        return token;
    }

    public void checkPasswordResetTokenIsValid(String token) {
        Optional<ConfirmationToken> confirmationToken = confirmationTokenRepository.findByToken(token);
        ConfirmationToken confirmToken = new ConfirmationToken();
        if (confirmationToken.isPresent()) {
            confirmToken = confirmationToken.get();
        }
        if (confirmToken.getExpiresAt().isBefore(LocalDateTime.now())) {
            throw new HealthCareAPIException(HttpStatus.BAD_REQUEST, Messages.TOKEN_EXPIRED_INVALID);
        }
    }

    public Context getForgotPasswordContext(User user, String confirmationLink) {
        Context context = new Context();
        context.setVariable("firstName", user.getFirstName());
        context.setVariable("lastName", user.getLastName());
        context.setVariable("link", confirmationLink);
        return context;
    }
}
