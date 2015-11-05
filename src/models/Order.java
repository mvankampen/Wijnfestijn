package models;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Date;

/**
 * <p> The Order class holds all the information of an order.</p>
 * @author Sander de Jong.
 * @version 0.1, November 2015.
 *          
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
     * @param guest Guest object
     */
    public Order(Guest guest) {
        this.guest = guest;
        this.date = new Date();
    }

    /**
     * Constructor
     *
     * @param id             Sets the id for order
     * @param guest          Holds guest details
     * @param orderTimestamp Sets the time of an order
     * @param completed      Sets the completion of the order
     */
    public Order(int id, Guest guest, Timestamp orderTimestamp, boolean completed) {
        this.id = id;
        this.guest = guest;
        this.date = new Date(orderTimestamp.getTime());
        this.completed = completed;
    }

    // ***** GETTERS *****

    /**
     * @return Returns the date
     */
    public Date getDate() {
        return date;
    }

    /**
     * @return Returns the id
     */
    public int getId() {
        return id;
    }

    /**
     * @return Returns the status of completion
     */
    public boolean getCompleted() {
        return completed;
    }

    /**
     * @return Returns a guest
     */
    public Guest getGuest() {
        return guest;
    }

    /**
     * @return Returns the list of order lines
     */
    public ArrayList<OrderLine> getOrderLines() {
        return orderLines;
    }

    // ***** SETTERS *****

    /**
     * @param date Used to change date
     */
    public void setDate(Date date) {
        this.date = date;
    }

    /**
     * @param completed Used to change completed
     */
    public void setCompleted(boolean completed) {
        this.completed = completed;
    }

    /**
     * @param guest Used to change guest
     */
    public void setGuest(Guest guest) {
        this.guest = guest;
    }

    /**
     * @param orderLines Used to change the order lines
     */
    public void setOrderLines(ArrayList<OrderLine> orderLines) {
        this.orderLines = orderLines;
    }
}
