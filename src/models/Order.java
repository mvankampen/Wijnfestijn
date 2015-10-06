package models;

import java.util.ArrayList;
import java.util.Date;

/**
 * Created by Sander de Jong on 21-9-2015.
 */
public class Order {
    private Guest guest;
    private ArrayList<OrderLine> orderLines;
    private Date date;
    private boolean completed = false;

    public Order(ArrayList<OrderLine> orderLines) {
        this.orderLines = orderLines;
        this.date = new Date();
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

    public Guest getGuest() {
        return guest;
    }

    public void setGuest(Guest guest) {
        this.guest = guest;
    }

    public ArrayList<OrderLine> getOrderLines() {
        return orderLines;
    }
}
