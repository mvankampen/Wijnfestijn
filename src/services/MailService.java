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
    private final String username = "groep5ipsenontvangbot@gmail.com";
    private final String password = "Gro3p5IPSEN2";
    private Mail mail;

    public void sendMail() {
        Address[] recipients = new InternetAddress[this.mail.getRecipients().size()];
        for (int i = 0; i < recipients.length; i++) {
            recipients[i] = this.mail.getRecipients().get(i);
        }

        Properties props = new Properties();
        props.put("mail.smtp.auth", "true");
        props.put("mail.smtp.starttls.enable", "true");
        props.put("mail.smtp.host", "smtp.gmail.com");
        props.put("mail.smtp.port", "587");

        Session session = Session.getInstance(props,
                new javax.mail.Authenticator() {
                    protected PasswordAuthentication getPasswordAuthentication() {
                        return new PasswordAuthentication(username, password);
                    }
                });

        try {

            Message message = new MimeMessage(session);
            message.setFrom(new InternetAddress("from-email@gmail.com"));
            message.setRecipients(Message.RecipientType.BCC, recipients);
            message.setSubject(this.mail.getSubject());
            message.setText(this.mail.getBody());
            Transport.send(message);

        } catch (MessagingException e) {
            throw new RuntimeException(e);
        }
    }

    public void setMail(Mail mail) {
        this.mail = mail;
    }
}
