package team.yellow.docconnect.service;

import team.yellow.docconnect.entity.ConfirmationToken;
import team.yellow.docconnect.entity.User;

public interface ConfirmationTokenService {
    void saveConfirmationToken(ConfirmationToken token);
    void validateConfirmationToken(String token, String tokenTypeName);
    void confirmToken(String token);
    String createNewConfirmationToken(User user, String tokenType);
    void checkTokenExpired(String token);
    void checkForPendingTokens(User user, String tokenType);
}
