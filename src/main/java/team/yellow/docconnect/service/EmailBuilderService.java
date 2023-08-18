package team.yellow.docconnect.service;

public interface EmailBuilderService {
    String buildConfirmationMail(String firstName, String confirmationUrl);
    String buildResetPasswordMail(String firstName, String resetUrl);
}
