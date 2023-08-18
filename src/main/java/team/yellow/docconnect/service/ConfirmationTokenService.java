package team.yellow.docconnect.service;

import team.yellow.docconnect.entity.ConfirmationToken;
import team.yellow.docconnect.entity.User;

public interface ConfirmationTokenService {

    void saveConfirmationToken(ConfirmationToken token);
    String confirmToken(String token);

    ConfirmationToken confirmResetToken(String token);
    void setConfirmationDate(String token);

    String createNewConfirmationToken(User user);
}
