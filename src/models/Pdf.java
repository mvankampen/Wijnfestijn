package models;

/**
 * Created by Sander de Jong on 8-10-2015.
 */
public class Pdf {
    private String title;
    private String subject;
    private final String keywords = "factuur";
    private final String author = "Lions Club";
    private final String creator = "Lions Club";

    public Pdf(String title, String subject) {
        this.title = title;
        this.subject = subject;
    }

    public String getSubject() {
        return subject;
    }

    public void setSubject(String subject) {
        this.subject = subject;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getKeywords() {
        return keywords;
    }

    public String getAuthor() {
        return author;
    }

    public String getCreator() {
        return creator;
    }
}