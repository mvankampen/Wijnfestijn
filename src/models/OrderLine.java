package models;

/**
 * Created by michael on 05-10-15.
 */
public class OrderLine {
    private Order order;
    private Wine wine;
    private int amount;


    public OrderLine(int amount, Order order, Wine wine) {
        this.amount = amount;
        this.order = order;
        this.wine = wine;

    }

    public OrderLine(int amount, Wine wine) {
        this.amount = amount;
        this.wine = wine;
    }

    public int getAmount() {
        return amount;
    }

    public void setAmount(int amount) {
        this.amount = amount;
    }

    public Order getOrder() {
        return this.order;
    }

    public void setOrder(Order order) {
        this.order = order;
    }

    public Wine getWine() {
        return wine;
    }

    public void setWine(Wine wine) {
        this.wine = wine;
    }
}