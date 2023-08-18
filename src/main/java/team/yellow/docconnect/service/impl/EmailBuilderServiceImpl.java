package team.yellow.docconnect.service.impl;

import org.springframework.stereotype.Service;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.Context;
import team.yellow.docconnect.service.EmailBuilderService;

@Service
public class EmailBuilderServiceImpl implements EmailBuilderService {

    private final TemplateEngine templateEngine;

    public EmailBuilderServiceImpl(TemplateEngine templateEngine) {
        this.templateEngine = templateEngine;
    }

    @Override
    public Context buildConfirmationMail(String firstName, String confirmationUrl) {
        Context context = new Context();
        context.setVariable("name", firstName);
        context.setVariable("link", confirmationUrl);
        return context;
    }

    @Override
    public Context buildResetPasswordMail() {
        return null;
    }
}
