package team.yellow.docconnect.service.impl;

import org.springframework.stereotype.Service;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.Context;
import team.yellow.docconnect.entity.User;
import team.yellow.docconnect.service.EmailBuilderService;

@Service
public class EmailBuilderServiceImpl implements EmailBuilderService {

    private final TemplateEngine templateEngine;

    public EmailBuilderServiceImpl(TemplateEngine templateEngine) {
        this.templateEngine = templateEngine;
    }

    @Override
    public String buildConfirmationMail(String firstName, String confirmationUrl) {
        Context context = new Context();
        context.setVariable("name", firstName);
        context.setVariable("link", confirmationUrl);
        return templateEngine.process("email-confirmation", context);
    }

    @Override
    public String buildResetPasswordMail(User user, String resetUrl) {
        Context context = new Context();
        context.setVariable("firstName", user.getFirstName());
        context.setVariable("lastName", user.getLastName());
        context.setVariable("link", resetUrl);
        return templateEngine.process("email-forgot-password", context);
    }
}
