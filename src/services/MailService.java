package services;

import enums.MailType;
import models.Mail;

import javax.activation.DataHandler;
import javax.activation.DataSource;
import javax.activation.FileDataSource;
import javax.mail.*;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;
import java.io.File;
import java.util.ArrayList;
import java.util.Properties;

/**
 * <p>Provides methods for sending {@link Mail} objects using the provided mail address and password.</p>
 *
 * @author Sander de Jong
 * @author Michael van Kampen
 * @version 0.1, november 2015
 */
public class MailService {
    private final String username = "groep5ipsenontvangbot@gmail.com";
    private final String password = "Gro3p5IPSEN2";
    private Mail mail;
    private Multipart multipart = new MimeMultipart("related");
    private BodyPart messageBodyPart = new MimeBodyPart();
    private DataSource fds;

    /**
     * <p>Sends a mail using the Gmail servers.</p>
     */
    public void sendMail() {
        Properties props = new Properties();
        props.put("mail.smtp.auth", "true");
        props.put("mail.smtp.starttls.enable", "true");
        props.put("mail.smtp.host", "smtp.gmail.com");
        props.put("mail.smtp.port", "587");

        Session session = Session.getInstance(props, new javax.mail.Authenticator() {
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication(username, password);
            }
        });

        Runnable task = new Runnable() {
            @Override
            public void run() {
                try {
                    Message message = new MimeMessage(session);
                    message.setFrom(new InternetAddress("from-email@gmail.com"));
                    message.setRecipients(Message.RecipientType.BCC, addRecipients());
                    message.setSubject(mail.getSubject());

                    messageBodyPart.setContent(mail.getBody(), "text/html");
                    multipart.addBodyPart(messageBodyPart);
                    message.setContent(multipart);

                    Transport.send(message);
                } catch (MessagingException e) {
                    throw new RuntimeException(e);
                }
            }
        };
        Thread thread = new Thread(task);
        thread.start();
    }

    /**
     * @return An array of {@link Address} objects so it can be used in the {@link MailService}
     */
    private Address[] addRecipients() {
        Address[] recipients = new InternetAddress[this.mail.getRecipients().size()];
        for (int i = 0; i < recipients.length; i++) {
            recipients[i] = this.mail.getRecipients().get(i);
        }
        return recipients;
    }

    /**
     * <p>Adds an attachmant to the mail.</p>
     * @param file The file that will be attached.
     */
    public void addAttachment(File file) {
        try {
            MimeBodyPart mimeBodyPart = new MimeBodyPart();
            DataSource source = new FileDataSource(file);
            mimeBodyPart.setDataHandler(new DataHandler(source));
            mimeBodyPart.setFileName(file.getName());
            this.multipart.addBodyPart(mimeBodyPart);
        } catch (MessagingException e) {
            e.printStackTrace();
        }
    }

//    public void addImage() {
//        try {
//            BodyPart messageBodyPart1 = new MimeBodyPart();
//            DataSource fds = new FileDataSource("src/images/header.png");
//            messageBodyPart1.setDataHandler(new DataHandler(fds));
//            messageBodyPart1.setHeader("Content-ID", "<header>");
//            multipart.addBodyPart(messageBodyPart1);
//
//            BodyPart messageBodyPart2 = new MimeBodyPart();
//            fds = new FileDataSource("src/images/inschrijfKnop.png");
//            messageBodyPart2.setDataHandler(new DataHandler(fds));
//            messageBodyPart2.addHeader("Content-ID", "<inschrijf>");
//            multipart.addBodyPart(messageBodyPart2);
//
//            BodyPart messageBodyPart3 = new MimeBodyPart();
//            fds = new FileDataSource("src/images/facebookKnop.png");
//            messageBodyPart3.setDataHandler(new DataHandler(fds));
//            messageBodyPart3.addHeader("Content-ID", "<facebook>");
//            multipart.addBodyPart(messageBodyPart3);
//
//            BodyPart messageBodyPart4 = new MimeBodyPart();
//            fds = new FileDataSource("src/images/contactKnop.png");
//            messageBodyPart4.setDataHandler(new DataHandler(fds));
//            messageBodyPart4.addHeader("Content-ID", "<contact>");
//            multipart.addBodyPart(messageBodyPart4);
//        } catch (MessagingException e) {
//            e.printStackTrace();
//        }
//    }

    /**
     * <p>Sets the current {@link Mail} object.</p>
     * @param mail
     */
    public void setMail(Mail mail) {
        this.mail = mail;
    }
}