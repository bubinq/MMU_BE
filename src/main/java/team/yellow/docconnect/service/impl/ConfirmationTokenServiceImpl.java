package team.yellow.docconnect.service.impl;

import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import team.yellow.docconnect.entity.ConfirmationToken;
import team.yellow.docconnect.entity.User;
import team.yellow.docconnect.exception.HealthCareAPIException;
import team.yellow.docconnect.exception.ResourceNotFoundException;
import team.yellow.docconnect.repository.ConfirmationTokenRepository;
import team.yellow.docconnect.repository.UserRepository;
import team.yellow.docconnect.service.ConfirmationTokenService;

import java.time.LocalDateTime;
import java.util.UUID;

@Service
public class ConfirmationTokenServiceImpl implements ConfirmationTokenService {

    private final ConfirmationTokenRepository confirmationTokenRepository;
    private final UserRepository userRepository;

    public ConfirmationTokenServiceImpl(ConfirmationTokenRepository confirmationTokenRepository, UserRepository userRepository) {
        this.confirmationTokenRepository = confirmationTokenRepository;
        this.userRepository = userRepository;
    }

    @Override
    public void saveConfirmationToken(ConfirmationToken token) {
        confirmationTokenRepository.save(token);
    }

    @Override
    public String confirmToken(String token) {
        ConfirmationToken confirmationToken = confirmationTokenRepository.findByToken(token)
                .orElseThrow(() -> new ResourceNotFoundException("confirmationToken", "token", token));
        if(confirmationToken.getConfirmedAt() != null){
            throw new HealthCareAPIException(HttpStatus.BAD_REQUEST, "Email already confirmed");
        }
        if(confirmationToken.getExpiresAt().isBefore((LocalDateTime.now()))){
            throw new HealthCareAPIException(HttpStatus.BAD_REQUEST, "Expired Token");
        }
        setConfirmationDate(token);
        userRepository.confirmEmail(confirmationToken.getUser().getEmail());
        return "Successfully confirmed";
    }

    @Override
    public ConfirmationToken confirmResetToken(String token) {
        ConfirmationToken confirmationToken = confirmationTokenRepository.findByToken(token)
                .orElseThrow(() -> new ResourceNotFoundException("confirmationToken", "token", token));
        if(confirmationToken.getConfirmedAt() != null){
            throw new HealthCareAPIException(HttpStatus.BAD_REQUEST, "Email already confirmed");
        }
        if(confirmationToken.getExpiresAt().isBefore((LocalDateTime.now()))){
            throw new HealthCareAPIException(HttpStatus.BAD_REQUEST, "Expired Token");
        }
        setConfirmationDate(token);
        return confirmationToken;
    }


    @Override
    public void setConfirmationDate(String token) {
        confirmationTokenRepository.updateConfirmedAt(token, LocalDateTime.now());
    }

    @Override
    public String createNewConfirmationToken(User user) {
        String token = UUID.randomUUID().toString();
        ConfirmationToken confirmationToken = new ConfirmationToken();
        confirmationToken.setToken(token);
        confirmationToken.setCreatedAt(LocalDateTime.now());
        confirmationToken.setExpiresAt(LocalDateTime.now().plusMinutes(15));
        confirmationToken.setUser(user);
        this.saveConfirmationToken(confirmationToken);
        return token;
    }
}
