package team.yellow.docconnect.service.helper;

import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;
import team.yellow.docconnect.entity.ConfirmationToken;
import team.yellow.docconnect.entity.Role;
import team.yellow.docconnect.entity.User;
import team.yellow.docconnect.exception.HealthCareAPIException;
import team.yellow.docconnect.exception.ResourceNotFoundException;
import team.yellow.docconnect.payload.dto.RegisterDto;
import team.yellow.docconnect.repository.ConfirmationTokenRepository;
import team.yellow.docconnect.repository.RoleRepository;
import team.yellow.docconnect.utils.Messages;

import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

@Component
@AllArgsConstructor
public class AuthenticationServiceHelper {

    private final RoleRepository roleRepository;
    private final PasswordEncoder passwordEncoder;
    private final ConfirmationTokenRepository confirmationTokenRepository;



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
        user.setIsOver18(true);
        user.setPrivacy_policy_agreement(true);
        user.setUser_agreement(true);
        user.setIs_deleted(false);
        return setRoles(user);
    }


    public void checkConfirmationTokenIsValid(String token) {
        Optional<ConfirmationToken> confirmationToken = confirmationTokenRepository.findByToken(token);
        ConfirmationToken confirmToken = new ConfirmationToken();
        if (confirmationToken.isPresent()) {
            confirmToken = confirmationToken.get();
        }
        if (confirmToken.getExpiresAt().isBefore(LocalDateTime.now())) {
            throw new HealthCareAPIException(HttpStatus.BAD_REQUEST, Messages.TOKEN_EXPIRED_INVALID);
        }
    }
    public void checkEmailVerificationTokenIsValid(String token) {
        ConfirmationToken confirmationToken = confirmationTokenRepository.findByToken(token)
                .orElseThrow(() -> new ResourceNotFoundException("confirmationToken", "token", token));
        if(confirmationToken.getConfirmedAt() != null){
            throw new HealthCareAPIException(HttpStatus.BAD_REQUEST,Messages.EMAIL_ALREADY_VERIFIED);
        }
        if(confirmationToken.getExpiresAt().isBefore((LocalDateTime.now()))){
            throw new HealthCareAPIException(HttpStatus.BAD_REQUEST, Messages.TOKEN_EXPIRED_INVALID);
        }
    }

}
