package team.yellow.docconnect.service.impl;

import com.azure.communication.email.EmailClient;
import com.azure.communication.email.EmailClientBuilder;
import com.azure.communication.email.models.EmailMessage;
import com.azure.communication.email.models.EmailSendResult;
import com.azure.core.credential.AzureKeyCredential;
import com.azure.core.util.polling.PollResponse;
import com.azure.core.util.polling.SyncPoller;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import team.yellow.docconnect.service.EmailService;

@Service
public class EmailServiceImpl implements EmailService {

    @Value("${azure.communication.email.endpoint}")
    private String endpoint;
    @Value("${azure.communication.email.credential}")
    private String key;
    @Value("${email.sender}")
    private String sender;
    @Override
    public void sendMail(String subject, String recipient, String body) {
        AzureKeyCredential azureKeyCredential = new AzureKeyCredential(key);
        EmailClient emailClient = new EmailClientBuilder()
                .endpoint(endpoint)
                .credential(azureKeyCredential)
                .buildClient();
        EmailMessage message = new EmailMessage()
                .setSenderAddress(sender)
                .setToRecipients(recipient)
                .setSubject(subject)
                .setBodyPlainText(body);

        SyncPoller<EmailSendResult, EmailSendResult> poller = emailClient.beginSend(message);
        PollResponse<EmailSendResult> response = poller.waitForCompletion();
        System.out.println("Operation Id: " + response.getValue().getId());
    }
}
