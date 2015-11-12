package controllers;

import DAO.MailDAO;
import enums.MailType;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.scene.control.Toggle;
import models.Mail;
import services.MailService;
import services.Util;
import splashscreens.MailEmptyMessage;
import splashscreens.MailSendMessage;
import splashscreens.SplashDefault;
import views.MailView;
import views.SplashscreenView;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * <p>Directs the {@link Mail} objects to the {@link MailService} and adds the correct recipients to them,
 * so that they can be send.</p>
 *
 * @author Sander de Jong
 * @author Michael van Kampen
 * @version 0.1, november 2015
 */
public class MailController {
    private MailView mailView;
    private Mail mail;
    private MailService mailService;
    private MailDAO mailDAO;
    private String context, title, header;
    private SplashscreenView splashScreenView;
    private ScreensController screensController;

    /**
     * @param mailView          The view from where the mail gets send.
     * @param mailDAO           The Data Access Object that gets the recipeints for the different types of mails.
     * @param mailService       Used to send a {@link Mail} object.
     * @param screensController Used to direct the different windows within the application.
     */
    public MailController(MailView mailView, MailDAO mailDAO, MailService mailService,
        ScreensController screensController) {
        this.screensController = screensController;
        this.mail = new Mail("", "");
        this.mailDAO = mailDAO;
        this.mailView = mailView;
        this.mailView.setMail(this.mail);
        this.mailService = mailService;
        generateHandlers();
    }

    /**
     * <p>Generates different event handlers.</p>
     */
    void generateHandlers() {
        this.mailView.getMailButton().setOnAction(e -> sendMail());
        mailViewListener();
    }

    /**
     * <p>Resets all the fields in the {@link MailView}</p>
     */
    void resetFields() {
        screensController.screenRemove(ControllersController.getATTENDANCEID());
        this.mail = new Mail("", "");
        this.mailView = new MailView(mail);
        this.mailView.setMail(this.mail);
        screensController.screenLoadSet(ControllersController.getATTENDANCEID(), mailView);
        generateHandlers();
    }

    /**
     * <p></p>Listens to the change of the togglegroup to determine which mail template needs to be shown.</p>
     */
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

    /**
     * <p>Fills the textfields in the {@link MailView} so that they have the value of the correct mailtemplate.</p>
     */
    private void setTextFields() {
        try {
            String s = readFile(this.mail.getMailType().toString() + ".html");
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

    /*Gets the title/header/context properties from guestCsvSplash and creates a 
     * splashscreenview with it, showing the message to the user
	 */

    /**
     * <p>Gets the title, header and context properties from the guestCsvSplash and creates a {@link SplashscreenView}
     * with it, showing the message to the user.</p>
     *
     * @param mailSplash The splashscreen used in the {@link MailView}.
     */
    private void setSplashScreenView(SplashDefault mailSplash) {
        context = "";
        title = "";
        header = "";
        title = mailSplash.getTitleText();
        header = mailSplash.getHeaderText();
        context = mailSplash.getContextText();
        splashScreenView = new SplashscreenView(title, header, context);
    }

    /**
     * <p>Sends the correct {@link Mail} according to the mailtype.</p>
     */
    public void sendMail() {
        if (!this.mailView.getSubject().isEmpty() && !this.mailView.getBody().isEmpty()) {
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
            SplashDefault mailSplash = new SplashDefault();
            mailSplash = new MailSendMessage(mailSplash);
            setSplashScreenView(mailSplash);
            resetFields();
        } else {
            SplashDefault mailSplash = new SplashDefault();
            mailSplash = new MailEmptyMessage(mailSplash);
            setSplashScreenView(mailSplash);
        }
    }

    /**
     * <p>Sets the recipients for the invitation mail.</p>
     */
    public void invitation() {
        this.mail.setRecipients(this.mailDAO.invitationMail());
    }

    /**
     * <p>Reads a file into a String.</p>
     *
     * @param fileName Location of the file
     * @return String with file content
     * @throws IOException IOException Signals that an I/O exception of some sort has occurred. This
     *                     class is the general class of exceptions produced by failed or
     *                     interrupted I/O operations.
     */
    String readFile(String fileName) throws IOException {
        return new Util().getTxtFileFromResource(fileName);
    }
}
