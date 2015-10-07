package controllers;

import DAO.CustomerDAO;
import DAO.OrderDAO;
import views.DebtorsView;

/**
 * Created by Sander de Jong on 28-9-2015.
 */
public class DebtorsController {
    private DebtorsView debtorsView;
    private OrderDAO orderDAO;
    private CustomerDAO customerDAO;

    public DebtorsController(DebtorsView debtorsView, OrderDAO orderDAO, CustomerDAO customerDAO) {
        this.debtorsView = debtorsView;
        this.orderDAO = orderDAO;
        this.customerDAO = customerDAO;
    }
}
