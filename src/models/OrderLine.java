package models;

/**
 * Created by michael on 05-10-15.
 */
public class OrderLine {
    private Order order;
    private int amount;

    public OrderLine(int amount, Order order) {
        this.amount = amount;
        this.order = order;
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
}
