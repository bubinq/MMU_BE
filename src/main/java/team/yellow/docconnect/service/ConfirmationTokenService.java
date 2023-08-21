package team.yellow.docconnect.service;

import team.yellow.docconnect.entity.ConfirmationToken;
import team.yellow.docconnect.entity.TokenType;
import team.yellow.docconnect.entity.User;

public interface ConfirmationTokenService {
    void saveConfirmationToken(ConfirmationToken token);
    void validateResetToken(String token);
    void validateVerificationToken(String token);
    void setConfirmationDate(String token);
    String createNewConfirmationToken(User user, TokenType tokenType);
}
