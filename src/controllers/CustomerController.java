package controllers;

import DAO.GuestDAO;
import views.CustomersView;

/**
 * Created by Sander de Jong on 22-9-2015.
 */
public class CustomerController {
    private CustomersView customersView;
    private GuestDAO guestDAO;

    public CustomerController(CustomersView customersView, GuestDAO guestDAO) {
        this.customersView = customersView;
        this.guestDAO = guestDAO;
    }
}
