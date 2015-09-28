package controllers;

import views.OrderListView;
import views.OrderlistPrintView;

/**
 * Created by Sander de Jong on 28-9-2015.
 */
public class OrderlistPrintController {
    private OrderlistPrintView orderlistPrintViewView;

    public OrderlistPrintController(OrderlistPrintView orderlistPrintViewView) {
        this.orderlistPrintViewView = new OrderlistPrintView();
    }
}
