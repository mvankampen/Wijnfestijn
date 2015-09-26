package models;

import javax.mail.Address;
import javax.mail.internet.InternetAddress;
import java.lang.reflect.Array;
import java.util.ArrayList;

/**
 * Created by sander on 22-9-2015.
 */
public class Mail {
    private ArrayList<InternetAddress> recipients;
    private String subject;
    private String body;

    public Mail(InternetAddress recipient, String subject, String body) {
        this.recipients.add(recipient);
        this.subject = subject;
        this. body = body;
    }

    public Mail(ArrayList<InternetAddress> recipients, String subject, String body) {
        this.recipients = recipients;
        this.subject = subject;
        this.body = body;
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
}