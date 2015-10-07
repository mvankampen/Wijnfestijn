package controllers;

import DAO.CustomerDAO;
import models.Customer;
import views.RegistrationView;

/**
 * Created by sande on 28-9-2015.
 */
public class RegistrationController {
    private RegistrationView registrationView;
    private CustomerDAO customerDAO;
    private Customer customer;

    public RegistrationController(RegistrationView registrationView, CustomerDAO customerDAO, Customer customer) {
        this.registrationView = registrationView;
        this.customerDAO = customerDAO;
        this.customer = customer;
    }
}
