package controllers;

import DAO.GuestDAO;
import javafx.scene.control.TextField;
import org.controlsfx.control.textfield.TextFields;
import views.OrderListView;

/**
 * Created by Sander de Jong on 28-9-2015.
 */
public class OrderListController {
    private OrderListView orderListView;
    private GuestDAO guestDAO;


    public OrderListController(OrderListView orderListView, GuestDAO guestDAO) {
        this.orderListView = orderListView;
        this.guestDAO = guestDAO;
        TextFields.bindAutoCompletion(orderListView.getSurnameTextField(), t-> {
            System.out.println(t.getUserText());
            return guestDAO.findGuestByLastname(t.getUserText());

        });
    }

}
