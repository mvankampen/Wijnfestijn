package controllers;

import DAO.GuestDAO;
import javafx.scene.control.TextField;
import models.Guest;
import org.controlsfx.control.textfield.TextFields;
import views.OrderListView;

import javax.xml.soap.Text;
import java.util.ArrayList;

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
            /*ArrayList<Guest> guestArrayList = guestDAO.findGuestByLastname(t.getUserText());
            ArrayList<String> guestName = new ArrayList<String>();
            for (Guest g : guestArrayList) {
                guestName.add(g.getSurname() + ", " + g.getInfix() + " " + g.getFirstname());
            }
            return guestName;*/
            return guestDAO.findGuestByLastname(t.getUserText());

        });
    }

}
