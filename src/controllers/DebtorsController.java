package controllers;

import DAO.GuestDAO;
import DAO.OrderDAO;
import views.DebtorsView;

/**
 * Created by Sander de Jong on 28-9-2015.
 */
public class DebtorsController {
    private DebtorsView debtorsView;
    private OrderDAO orderDAO;
    private GuestDAO guestDAO;

    public DebtorsController(DebtorsView debtorsView, OrderDAO orderDAO, GuestDAO guestDAO) {
        this.debtorsView = debtorsView;
        this.orderDAO = orderDAO;
        this.guestDAO = guestDAO;
    }
}
