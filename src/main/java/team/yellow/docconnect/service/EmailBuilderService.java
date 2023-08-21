package team.yellow.docconnect.service;

import team.yellow.docconnect.entity.User;

public interface EmailBuilderService {
    String buildConfirmationMail(String firstName, String confirmationUrl);
    String buildResetPasswordMail(User user, String resetUrl);
}
