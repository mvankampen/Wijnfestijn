package controllers;

import DAO.MailDAO;
import enums.MailType;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.scene.control.Toggle;
import models.Mail;
import services.MailService;
import views.ImportWineListView;
import views.MailView;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by Sander de Jong on 28-9-2015.
 */
public class MailController {
    private MailView mailView;
    private Mail mail;
    private MailService mailService;
    private MailDAO mailDAO;
    private ScreensController screensController;
    public MailController(MailView mailView, MailDAO mailDAO, MailService mailService, ScreensController screensController) {
        this.screensController = screensController;
        this.mail = new Mail("", "");
        this.mailDAO = mailDAO;
        this.mailView = mailView;
        this.mailView.setMail(this.mail);
        this.mailService = mailService;
        generateHandlers();
    }
    void generateHandlers(){
        this.mailView.getMailButton().setOnAction(e -> sendMail());
        mailViewListener();
    }
    void resetFields() {
        screensController.screenRemove(ControllersController.getATTENDANCEID());
        this.mail = new Mail("", "");
        this.mailView = new MailView(mail);
        this.mailView.setMail(this.mail);
        screensController.screenLoadSet(ControllersController.getATTENDANCEID(), mailView);
        generateHandlers();
    }
    private void mailViewListener() {
        this.mailView.getInviteGroup().selectedToggleProperty()
            .addListener(new ChangeListener<Toggle>() {
                @Override
                public void changed(ObservableValue<? extends Toggle> observable, Toggle oldValue,
                    Toggle newValue) {
                    if (mailView.getInviteGroup().getSelectedToggle() != null) {
                        mail.setMailType(MailType.values()[mailView.getInviteGroup().getToggles()
                            .indexOf(newValue)]);
                        setTextFields();
                    }
                }
            });
    }

    // Method moet worden verbeterd..
    // ???? wat doet dit ?? - Sander
    private void setTextFields() {
        try {
            //            String s = new Scanner(new File("src/templates/" + this.mail.getMailType().toString() + ".html")).
            //                    useDelimiter("\\Z").next();

            String s = readFile("src/templates/" + this.mail.getMailType().toString() + ".html");

            Pattern pattern = Pattern.compile("<title>(.+?)</title>");
            Matcher matcher = pattern.matcher(s);
            matcher.find();
            this.mail.setSubject(matcher.group(1));
            this.mail.setBody(s);
            this.mailView.updateFields();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void sendMail() {
        this.mail.setSubject(this.mailView.getSubject());
        this.mail.setBody(this.mailView.getBody());
        switch (this.mail.getMailType()) {
            case REMINDER:
                this.mail.setRecipients(this.mailDAO.reminderMail());
                break;
            case INVITATIONAL:
                invitation();
                break;
            case THANKYOU:
                this.mail.setRecipients(this.mailDAO.thanksMail());
                break;
            case OPENORDER:
                this.mail.setRecipients(this.mailDAO.getOpenOrderGuests());
                break;
        }
        this.mailService.setMail(this.mail);
        this.mailService.sendMail();
    }

    public void invitation() {
        this.mail.setRecipients(this.mailDAO.invitationMail());
        //this.mailService.addImage();
    }

    String readFile(String fileName) throws IOException {
        BufferedReader br = new BufferedReader(new FileReader(fileName));
        try {
            StringBuilder sb = new StringBuilder();
            String line = br.readLine();

            while (line != null) {
                sb.append(line);
                sb.append("\n");
                line = br.readLine();
            }
            return sb.toString();
        } finally {
            br.close();
        }
    }
}