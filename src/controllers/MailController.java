package controllers;

import models.Mail;
import services.MailService;
import views.MailView;

/**
 * Created by Sander de Jong on 28-9-2015.
 */
public class MailController {
    private MailView mailView;
    private Mail mail;
    private MailService mailService;
    private MailDAO mailDAO;

    public MailController(MailView mailView) {
        this.mailView = mailView;
        this.mailService = new MailService();
        this.mailView.getMailButton().setOnAction(e -> sendMail());
    }

    public void sendMail() {
        System.out.println("test");
        this.mail = new Mail(this.mailView.getSubject(), this.mailView.getBody());
        this.mail.setRecipients(this.mailDAO.getblablalist);
        this.mailService.setMail(this.mail);
        this.mailService.sendMail();
    }
}
