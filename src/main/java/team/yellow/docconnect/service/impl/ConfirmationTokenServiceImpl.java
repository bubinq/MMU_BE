package team.yellow.docconnect.service.impl;

import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import team.yellow.docconnect.entity.ConfirmationToken;
import team.yellow.docconnect.entity.TokenType;
import team.yellow.docconnect.entity.User;
import team.yellow.docconnect.exception.HealthCareAPIException;
import team.yellow.docconnect.exception.ResourceNotFoundException;
import team.yellow.docconnect.repository.ConfirmationTokenRepository;
import team.yellow.docconnect.repository.TokenTypeRepository;
import team.yellow.docconnect.service.ConfirmationTokenService;
import team.yellow.docconnect.utils.Messages;

import java.time.LocalDateTime;
import java.util.UUID;

@Service
@AllArgsConstructor
public class ConfirmationTokenServiceImpl implements ConfirmationTokenService {

    private final ConfirmationTokenRepository confirmationTokenRepository;
    private final TokenTypeRepository tokenTypeRepository;

    @Override
    public void saveConfirmationToken(ConfirmationToken token) {
        confirmationTokenRepository.save(token);
    }

    @Override
    public void validateConfirmationToken(String token, String tokenTypeName) {
        ConfirmationToken confirmationToken = confirmationTokenRepository.findByToken(token)
                .orElseThrow(() -> new ResourceNotFoundException("confirmationToken", "token", token));
        if(confirmationToken.getConfirmedAt() != null){
            throw new HealthCareAPIException(HttpStatus.BAD_REQUEST, Messages.TOKEN_EXPIRED_INVALID);
        }
        if(confirmationToken.getExpiresAt().isBefore((LocalDateTime.now()))){
            throw new HealthCareAPIException(HttpStatus.BAD_REQUEST,Messages.TOKEN_EXPIRED_INVALID );
        }
        if(!confirmationToken.getTokenType().getName().equalsIgnoreCase(tokenTypeName)){
            throw new HealthCareAPIException(HttpStatus.BAD_REQUEST, Messages.INVALID_TOKEN_TYPE);
        }
        confirmToken(token);
    }

    @Override
    public void confirmToken(String token) {
        confirmationTokenRepository.updateConfirmedAtAndExpiresAt(token, LocalDateTime.now());
    }

    @Override
    public String createNewConfirmationToken(User user, String tokenType) {

        TokenType foundType =  tokenTypeRepository.findTokenTypeByName(tokenType)
                .orElseThrow(() -> new ResourceNotFoundException("TokenType", "name", tokenType));

        String token = UUID.randomUUID().toString();
        ConfirmationToken confirmationToken = new ConfirmationToken();
        confirmationToken.setToken(token);
        confirmationToken.setTokenType(foundType);
        confirmationToken.setCreatedAt(LocalDateTime.now());
        confirmationToken.setExpiresAt(LocalDateTime.now().plusMinutes(60));
        confirmationToken.setUser(user);

        this.saveConfirmationToken(confirmationToken);
        return token;
    }

    @Override
    public void checkTokenExpired(String token) {
        ConfirmationToken confirmationToken = confirmationTokenRepository.findByToken(token)
                .orElseThrow(() -> new ResourceNotFoundException("confirmationToken", "token", token));

        if(confirmationToken.getExpiresAt().isAfter(LocalDateTime.now())){
            throw new HealthCareAPIException(HttpStatus.BAD_REQUEST, Messages.PREVIOUS_TOKEN_NOT_EXPIRED);
        }
    }

    @Override
    public void checkForPendingTokens(User user, String tokenType) {
        TokenType foundToken =  tokenTypeRepository.findTokenTypeByName(tokenType)
                .orElseThrow(() -> new ResourceNotFoundException("TokenType", "name", tokenType));

        String lastToken= confirmationTokenRepository.findLatestTokenByUserIdAndTokenTypeId(user.getId(),foundToken.getId());
        if(lastToken!=null) {
            checkTokenExpired(lastToken);
        }
    }
}
