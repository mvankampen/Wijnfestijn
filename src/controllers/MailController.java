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

    public MailController(MailView mailView) {
        this.mailView = mailView;
    }
}
