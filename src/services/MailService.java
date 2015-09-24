package services;

import models.Mail;

import javax.mail.*;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import java.util.Properties;

/**
 * Created by Sander de Jong on 22-9-2015.
 */
public class MailService {
    private final String senderID = "testmail@mail.com";
    private final String host = "localhost";
    private Mail mail;

    public MailService(Mail mail) {
        this.mail = mail;
    }

    public void sendMail() {
        Properties properties = System.getProperties();
        properties.setProperty("mail.smtp.host", this.host);
        Session session = Session.getDefaultInstance(properties);

        Address[] recipients = new InternetAddress[this.mail.getRecipients().size()];
        for (int i = 0; i < recipients.length; i++) {
            recipients[i] = this.mail.getRecipients().get(i);
        }

        try {
            MimeMessage message = new MimeMessage(session);
            message.setFrom(new InternetAddress(this.senderID));
            message.addRecipients(Message.RecipientType.TO, recipients);
            message.setSubject(this.mail.getSubject());
            message.setText(this.mail.getBody());
            Transport.send(message);

        } catch (MessagingException ex) {
            ex.printStackTrace();
        }
    }
}
