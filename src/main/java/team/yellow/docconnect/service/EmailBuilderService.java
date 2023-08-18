package team.yellow.docconnect.service;

import org.thymeleaf.context.Context;

public interface EmailBuilderService {
    Context buildConfirmationMail(String firstName, String confirmationUrl);
    Context buildResetPasswordMail();
}
