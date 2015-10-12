package models;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Date;

/**
 * Created by Sander de Jong on 21-9-2015.
 */
public class Order {
    private int id;
    private Guest guest;
    private ArrayList<OrderLine> orderLines;
    private Date date;
    private boolean completed = false;

    public Order(Guest guest) {
        this.guest = guest;
        this.date = new Date();
    }

    public Order(int id, Guest guest, Timestamp orderTimestamp) {
        this.id = id;
        this.guest = guest;
        this.date = new Date(orderTimestamp.getTime());
    }

    public Date getDate() {
        return date;
    }

    public int getId() {
        return id;
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

    public void setOrderLines(ArrayList<OrderLine> orderLines) {
        this.orderLines = orderLines;
    }
}