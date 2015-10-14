package controllers;

import java.util.ArrayList;

import DAO.GuestDAO;
import DAO.OrderDAO;
import javafx.beans.property.BooleanProperty;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;
import javafx.scene.control.cell.CheckBoxTableCell;
import javafx.scene.control.cell.PropertyValueFactory;
import models.Guest;
import models.Order;
import models.OrderLine;
import models.Wine;
import views.DebtorsView;
import views.OrderView;

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
    private TableColumn<Order, Guest> emailCol;
    private TableColumn<Order, Boolean> activeCol;
    private ScreensController screensController;
    

    public DebtorsController(DebtorsView debtorsView, OrderDAO orderDAO, GuestDAO guestDAO, ScreensController screensController) {
        this.debtorsView = debtorsView;
        this.orderDAO = orderDAO;
        this.guestDAO = guestDAO;
        this.screensController = screensController;
        generateHandlers();   
        
    }
    private void generateHandlers() {
    	makeTable(); 
    	this.debtorsView.getGenerateButton().setOnAction(e -> generateDebtors());
    	this.debtorsView.getSaveButton().setOnAction(e -> submitChanges());
    }
    private void makeTable() {
    	data = FXCollections.observableArrayList();
    	 emailCol = new TableColumn<Order, Guest>("Gast email");
         emailCol.setMinWidth(250);
         emailCol.setCellValueFactory(new PropertyValueFactory<>("guest"));
         emailCol.setCellFactory(e -> {
             return new TableCell<Order, Guest>() {
                 @Override protected void updateItem(Guest item, boolean empty) {
                     super.updateItem(item, empty);
                     if (!empty) {
                         setText(item.getEmail());
                     }
                 }
             };
         });
         activeCol = new TableColumn<Order, Boolean>("Order voltooid");
         activeCol.setCellValueFactory(new PropertyValueFactory<>("completed"));
         activeCol.setMinWidth(150);
         activeCol.setCellFactory(col -> {
	            CheckBoxTableCell<Order, Boolean> cell = new CheckBoxTableCell<>(index -> {
	                BooleanProperty active = new SimpleBooleanProperty(debtorsView.getTableView().getItems().get(index).getCompleted());
	                active.addListener((obs, wasActive, isNowActive) -> {
	                    Order item = debtorsView.getTableView().getItems().get(index);
	                    item.setCompleted(isNowActive);
	                });
	                return active ;
	            });
	            return cell ;
	        });
        debtorsView.getTableView().getColumns().clear();
 		debtorsView.getTableView().setItems(data);
 		debtorsView.getTableView().getColumns().addAll(emailCol,activeCol);
        
    }
    private void generateDebtors() {
    	debtorsArrayList = orderDAO.getAllNativeOrders();
    	data.clear();
    	int j = 0;
    	while (debtorsArrayList.size() > j) {
    		data.add(debtorsArrayList.get(j));
    		j++;
    	}
    	debtorsArrayList.clear();
    	debtorsView.getTableView().getColumns().clear();
		debtorsView.getTableView().setItems(data);
		debtorsView.getTableView().getColumns().addAll(emailCol, activeCol);
    }
    private void submitChanges() {
    int n = 0;
    while(data.size() > n) {
    	orderDAO.updateOrder(data.get(n));
    	n++;
    }
    screensController.screenRemove(ControllersController.getDEBTORID());
    this.debtorsView = new DebtorsView();
    screensController.screenLoadSet(ControllersController.getDEBTORID(), debtorsView);
    generateHandlers();
	debtorsArrayList.clear();
	debtorsView.getTableView().getItems().clear();
	debtorsView.getTableView().getColumns().clear();
    }
    
}
