package controllers;

import DAO.CustomerDAO;
import models.Customer;
import views.CustomersView;

/**
 * Created by Sander de Jong on 22-9-2015.
 */
public class CustomerController {
    private CustomersView customersView;
    private CustomerDAO customerDAO;

    public CustomerController(CustomersView customersView, CustomerDAO customer) {
        this.customersView = customersView;
        this.customerDAO = customer;
    }
}
