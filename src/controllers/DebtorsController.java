package controllers;

import java.util.ArrayList;

import DAO.GuestDAO;
import DAO.OrderDAO;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import models.Order;
import views.DebtorsView;

/**
 * Created by Sander de Jong on 28-9-2015.
 */
public class DebtorsController {
    private DebtorsView debtorsView;
    private OrderDAO orderDAO;
    private GuestDAO guestDAO;
    private Order selectedDebtor;
    private ArrayList<Order> debtorsArrayList;
    private ObservableList<Order> data;

    public DebtorsController(DebtorsView debtorsView, OrderDAO orderDAO, GuestDAO guestDAO) {
        this.debtorsView = debtorsView;
        this.orderDAO = orderDAO;
        this.guestDAO = guestDAO;
        generateHandlers();   
    }
    private void generateHandlers() {
    	makeTable(); 
    	this.debtorsView.getGenerateButton().setOnAction(e -> testFunction());
    }
    private void makeTable() {
    	data = FXCollections.observableArrayList();
    }
    private void testFunction() {
    	debtorsArrayList = orderDAO.getAllNativeOrders();
    	int j = 0;
    	while (debtorsArrayList.size() > j) {
    		data.add(debtorsArrayList.get(j));
    		System.out.println(data);
    		j++;
    	}
    }
    
}
