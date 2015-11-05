package models;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Date;

/**
 * @author Sander de Jong.
 * @version 0.1, November 2015.
 * 		Description:
 * 		The Order class holds all the information of an order.
 */
public class Order {
    private int id;
    private Guest guest;
    private ArrayList<OrderLine> orderLines;
    private Date date;
    private boolean completed = false;

    /**
     * Constructor
     * 
     * @param guest
     */
    public Order(Guest guest) {
        this.guest = guest;
        this.date = new Date();
    }

    public Order(int id, Guest guest, Timestamp orderTimestamp, boolean completed) {
        this.id = id;
        this.guest = guest;
        this.date = new Date(orderTimestamp.getTime());
        this.completed = completed;
    }

    public Date getDate() {
        return date;
    }

    public int getId() {
        return id;
    }

    public boolean getCompleted() {
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
