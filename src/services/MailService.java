package services;

import models.Mail;

import javax.activation.DataHandler;
import javax.activation.DataSource;
import javax.activation.FileDataSource;
import javax.mail.*;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;
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

        Session session = Session.getInstance(props, new javax.mail.Authenticator() {
                protected PasswordAuthentication getPasswordAuthentication() {
                    return new PasswordAuthentication(username, password);
                }
            });

        Runnable task = new Runnable() {
            @Override public void run() {
                try {
                    Message message = new MimeMessage(session);
                    message.setFrom(new InternetAddress("from-email@gmail.com"));
                    message.setRecipients(Message.RecipientType.BCC, recipients);
                    message.setSubject(mail.getSubject());

                    MimeMultipart multipart = new MimeMultipart("related");
                    BodyPart messageBodyPart = new MimeBodyPart();
                    messageBodyPart.setContent(mail.getBody(), "text/html");
                    multipart.addBodyPart(messageBodyPart);

                    messageBodyPart = new MimeBodyPart();
                    DataSource fds = new FileDataSource("src/images/header.png");
                    messageBodyPart.setDataHandler(new DataHandler(fds));
                    messageBodyPart.setHeader("Content-ID", "<header>");
                    multipart.addBodyPart(messageBodyPart);


                    messageBodyPart = new MimeBodyPart();
                    DataSource fds2 = new FileDataSource
                        ("src/images/inschrijfKnop.png");
                    messageBodyPart.setDataHandler(new DataHandler(fds2));
                    messageBodyPart.addHeader("Content-ID","<inschrijf>");
                    // add it
                    multipart.addBodyPart(messageBodyPart);

                    messageBodyPart = new MimeBodyPart();
                    DataSource fds3 = new FileDataSource
                        ("src/images/facebookKnop.png");
                    messageBodyPart.setDataHandler(new DataHandler(fds3));
                    messageBodyPart.addHeader("Content-ID","<facebook>");
                    // add it
                    multipart.addBodyPart(messageBodyPart);

                    messageBodyPart = new MimeBodyPart();
                    DataSource fds4 = new FileDataSource
                        ("src/images/contactKnop.png");
                    messageBodyPart.setDataHandler(new DataHandler(fds4));
                    messageBodyPart.addHeader("Content-ID","<contact>");
                    // add it
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




            /*MimeMultipart multipart = new MimeMultipart("related");
            // first part (the html)
            BodyPart messageBodyPart = new MimeBodyPart();
            messageBodyPart.setContent(this.mail.getBody(), "text/html");
            // add it
            multipart.addBodyPart(messageBodyPart);
            // second part (the image)
            messageBodyPart = new MimeBodyPart();
            System.out.println(("/images/header.png"));
            DataSource fds = new FileDataSource("src/images/header.png");
            messageBodyPart.setDataHandler(new DataHandler(fds));
            messageBodyPart.setHeader("Content-ID", "header");
            // add image to the multipart
            multipart.addBodyPart(messageBodyPart);
            // put everything together
            message.setContent(multipart);*/

            /*message.setContent(this.mail.getBody(), "text/html");
            MimeBodyPart imagePart = new MimeBodyPart();
            imagePart.setHeader("Content-ID", "AbcXyz123");
            imagePart.setDisposition(MimeBodyPart.INLINE);
            // attach the image file
            imagePart.attachFile(new File());*/
    }

    public void setMail(Mail mail) {
        this.mail = mail;
    }
}
