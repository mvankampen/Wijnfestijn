package controllers;

import views.OrderListView;

/**
 * Created by Sander de Jong on 28-9-2015.
 */
public class OrderListController {
    private OrderListView orderListView;

    public OrderListController(OrderListView orderListView) {
        this.orderListView = orderListView;
    }
}
