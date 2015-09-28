package controllers;

import DAO.CustomerDAO;
import models.Customer;
import views.CustomersView;

/**
 * Created by Sander de Jong on 28-9-2015.
 */
public class ControllersController {
    private CustomerController customerController;

    public ControllersController() {
        createControllers();
    }

    private void createControllers() {
        this.customerController = new CustomerController(new CustomersView(), new CustomerDAO());
    }
}
