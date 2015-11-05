package models;

/**
 * @author Michael van Kampen.
 * @version 0.1, November 2015
 *          Description:
 *          The OrderLine class is used for a single line of an Order.
 */
public class OrderLine {
    private Order order;
    private Wine wine;
    private int amount;

    /**
     * Constructor
     *
     * @param amount Sets the amount
     * @param order  Sets an Order
     * @param wine   Sets a Wine
     */
    public OrderLine(int amount, Order order, Wine wine) {
        this.amount = amount;
        this.order = order;
        this.wine = wine;

    }

    /**
     * Constructor
     *
     * @param amount Sets the amount
     * @param wine   Sets a Wine
     */
    public OrderLine(int amount, Wine wine) {
        this.amount = amount;
        this.wine = wine;
    }

    // ***** GETTERS *****

    /**
     * @return Returns the amount
     */
    public int getAmount() {
        return amount;
    }

    /**
     * @return Returns an order
     */
    public Order getOrder() {
        return this.order;
    }

    /**
     * @return Returns a Wine
     */
    public Wine getWine() {
        return wine;
    }

    // ***** SETTERS *****

    /**
     * @param amount Used to change amount
     */
    public void setAmount(int amount) {
        this.amount = amount;
    }

    /**
     * @param order Used to change order
     */
    public void setOrder(Order order) {
        this.order = order;
    }

    /**
     * @param wine Used to change wine
     */
    public void setWine(Wine wine) {
        this.wine = wine;
    }
}
