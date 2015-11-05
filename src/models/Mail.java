package models;

import enums.MailType;

import javax.mail.internet.InternetAddress;
import java.util.ArrayList;

/**
 * @author Sander de Jong.
 * @version 0.1. November 2015.
 *          <p>
 *          Discription:
 *          The Mail class holds all the information about the e-mail and
 *          the recipients to send it to.
 */
public class Mail {
    private ArrayList<InternetAddress> recipients;
    private String subject;
    private String body;

    private MailType mailType;

    /**
     * Constructor
     *
     * @param subject Sets the mail subject
     * @param body    Sets the mail body
     */
    public Mail(String subject, String body) {
        this.subject = subject;
        this.body = body;
    }

    // ***** GETTERS *****

    /**
     * @return Returns the list of recipients
     */
    public ArrayList<InternetAddress> getRecipients() {
        return recipients;
    }

    /**
     * @return Returns the subject
     */
    public String getSubject() {
        return subject;
    }

    /**
     * @return Returns the body
     */
    public String getBody() {
        return body;
    }

    /**
     * @return Returns the mail type
     */
    public MailType getMailType() {
        return mailType;
    }

    // ***** SETTERS *****

    /**
     * @param recipients Used to set the list of recipients
     */
    public void setRecipients(ArrayList<InternetAddress> recipients) {
        this.recipients = recipients;
    }

    /**
     * @param subject Used to set the subject
     */
    public void setSubject(String subject) {
        this.subject = subject;
    }

    /**
     * @param body Used to set the body
     */
    public void setBody(String body) {
        this.body = body;
    }

    /**
     * @param mailType Used to set the mail type
     */
    public void setMailType(MailType mailType) {
        this.mailType = mailType;
    }
}
