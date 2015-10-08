package models;

import enums.MailType;

import javax.mail.internet.InternetAddress;
import java.util.ArrayList;

/**
 * Created by sander on 22-9-2015.
 */
public class Mail {
    private ArrayList<InternetAddress> recipients;
    private String subject;
    private String body;



    private MailType mailType;

    public Mail(String subject, String body) {
        this.subject = subject;
        this. body = body;
    }

    public ArrayList<InternetAddress> getRecipients() {
        return recipients;
    }

    public String getSubject() {
        return subject;
    }

    public String getBody() {
        return body;
    }

    public void setRecipients(ArrayList<InternetAddress> recipients) {
        this.recipients = recipients;
    }

    public void setSubject(String subject) {
        this.subject = subject;
    }

    public void setBody(String body) {
        this.body = body;
    }

    public MailType getMailType() {
        return mailType;
    }

    public void setMailType(MailType mailType) {
        this.mailType = mailType;
    }


}