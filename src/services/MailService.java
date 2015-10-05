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
    private final String senderID = "";
    private final String host = "";
    private final String password = "";
    private Mail mail;

    public void sendMail() {
        if (!this.mail.equals(null)) {
            Properties properties = System.getProperties();
            properties.setProperty("mail.smtp.host", this.host);
            properties.setProperty("mail.smtp.password", this.password);
            Session session = Session.getDefaultInstance(properties);

            Address[] recipients = new InternetAddress[this.mail.getRecipients().size()];
            for (int i = 0; i < recipients.length; i++) {
                recipients[i] = this.mail.getRecipients().get(i);
            }

            try {
                MimeMessage message = new MimeMessage(session);
                message.setFrom(new InternetAddress(this.senderID));
                message.addRecipients(Message.RecipientType.CC, recipients);
                message.setSubject(this.mail.getSubject());
                message.setText(this.mail.getBody());
                Transport.send(message);

            } catch (MessagingException ex) {
                ex.printStackTrace();
            }
        }
    }



    public void setMail(Mail mail) {
        this.mail = mail;
    }
}
