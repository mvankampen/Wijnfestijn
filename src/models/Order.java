package models;

import java.util.Date;

/**
 * Created by Sander de Jong on 21-9-2015.
 */
public class Order {
    private Date date;
    private boolean completed;

    public Order(Date date, boolean completed) {
        this.date = date;
        this.completed = completed;
    }

    public Date getDate() {
        return date;
    }

    public boolean isCompleted() {
        return completed;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public void setCompleted(boolean completed) {
        this.completed = completed;
    }
}
