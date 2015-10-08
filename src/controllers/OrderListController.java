package controllers;

import DAO.GuestDAO;
import javafx.util.StringConverter;
import models.Guest;
import org.controlsfx.control.textfield.AutoCompletionBinding;
import org.controlsfx.control.textfield.TextFields;
import views.OrderListView;

/**
 * Created by Sander de Jong on 28-9-2015.
 */
public class OrderListController {
    private OrderListView orderListView;
    private GuestDAO guestDAO;
    private Guest currentGuest;


    public OrderListController(OrderListView orderListView, GuestDAO guestDAO) {
        this.orderListView = orderListView;
        this.guestDAO = guestDAO;

        AutoCompletionBinding<Guest> autoCompletionBinding = TextFields
            .bindAutoCompletion(orderListView.getSurnameTextField(),
                t -> guestDAO.findGuestByLastname(t.getUserText()), new StringConverter<Guest>() {
                    @Override public String toString(Guest object) {
                        return object.getSurname() + ", " + object.getInfix() + " " + object
                            .getFirstname();
                    }

                    @Override public Guest fromString(String string) {
                        return null;
                    }
                });
        autoCompletionBinding
            .setOnAutoCompleted(event -> this.currentGuest = event.getCompletion());
    }

}
