package controllers;

import DAO.MailDAO;
import enums.MailType;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.scene.control.Toggle;
import models.Mail;
import services.MailService;
import views.MailView;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;

/**
 * Created by Sander de Jong on 28-9-2015.
 */
public class MailController {
    private MailView mailView;
    private Mail mail;
    private MailService mailService;
    private MailDAO mailDAO;
    private BufferedReader bufferedReader;
    private FileReader fileReader;

    public MailController(MailView mailView, MailDAO mailDAO) {
        this.mail = new Mail("", "");
        this.mailDAO = mailDAO;
        this.mailView = mailView;
        this.mailView.setMail(this.mail);
        this.mailService = new MailService();
        this.mailView.getMailButton().setOnAction(e -> sendMail());
        mailViewListener();
    }

    private void mailViewListener() {
        this.mailView.getInviteGroup().selectedToggleProperty().addListener(new ChangeListener<Toggle>() {
            @Override
            public void changed(ObservableValue<? extends Toggle> observable, Toggle oldValue, Toggle newValue) {
                if (mailView.getInviteGroup().getSelectedToggle() != null) {
                    mail.setMailType(MailType.values()[mailView.getInviteGroup().getToggles().indexOf(newValue)]);
                    setTextFields();
                }
            }
        });
    }

    // Method moet worden verbeterd..
    // ???? wat doet dit ?? - Sander
    private void setTextFields() {
        try {
            this.mail.setBody("");
            String line;
            boolean subjectSet = false;
            BufferedReader br = new BufferedReader(new FileReader(new File("src/images/" +
                    this.mail.getMailType().toString() + ".txt").toString()));

            while ((line = br.readLine()) != null) {
                if (!subjectSet) {
                    this.mail.setSubject(line);
                    subjectSet = true;
                } else {
                    this.mail.setBody(this.mail.getBody() + line + "\n");
                }
            }
            this.mailView.updateFields();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void sendMail() {
        System.out.println("test");
        this.mail = new Mail(this.mailView.getSubject(), this.mailView.getBody());
        switch (this.mail.getMailType()) {
            case REMINDER:
                System.out.println("reminder method aanroepen");
                break;
            case INVITATIONAL:
                System.out.println("invitational method aanroepen (wss zelfde als reminder)");
                break;
            case THANKYOU:
                System.out.println("Thank you method aanroepen");
                break;
            case OPENORDER:
                this.mail.setRecipients(this.mailDAO.getOpenOrderGuests());
                break;
        }
        this.mailService.setMail(this.mail);
        this.mailService.sendMail();
    }
}