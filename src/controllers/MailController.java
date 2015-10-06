package controllers;

import DAO.MailDAO;
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

    public MailController(MailView mailView, MailDAO mailDAO) {
        this.mailDAO = mailDAO;
        this.mailView = mailView;
        this.mailService = new MailService();
        this.mailView.getMailButton().setOnAction(e -> sendMail());
    }

    public void sendMail() {
        System.out.println("test");
        this.mail = new Mail(this.mailView.getSubject(), this.mailView.getBody());
        this.mail.setRecipients(this.mailDAO.getOpenOrderGuests());
        this.mailService.setMail(this.mail);
        this.mailService.sendMail();
    }
}
