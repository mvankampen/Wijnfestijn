package controllers;

import views.OrderListView;
import views.OrderListPrintView;

/**
 * Created by Sander de Jong on 28-9-2015.
 */
public class OrderListPrintController {
    private OrderListPrintView orderlistPrintViewView;

    public OrderListPrintController(OrderListPrintView orderlistPrintViewView) {
        this.orderlistPrintViewView = new OrderListPrintView();
    }
}
