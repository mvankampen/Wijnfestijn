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
import views.AttendanceView;
import views.DebtorsView;

/**
 * Created by Sander de Jong on 28-9-2015.
 */
public class DebtorsController {
    private DebtorsView debtorsView;
    private OrderDAO orderDAO;
    private ArrayList<Order> debtorsArrayList;
    private ObservableList<Order> data;
    private TableColumn<Order, Guest> emailCol;
    private TableColumn<Order, Boolean> activeCol;
    private ScreensController screensController;
    public DebtorsController(DebtorsView debtorsView, OrderDAO orderDAO, GuestDAO guestDAO, ScreensController screensController) {
        this.debtorsView = debtorsView;
        this.orderDAO = orderDAO;
        this.screensController = screensController;
        generateHandlers();   
        
    }
    //makes the button handlers and calls the maketable method
    private void generateHandlers() {
        makeTable();
    	this.debtorsView.getGenerateButton().setOnAction(e -> generateDebtors());
    	this.debtorsView.getSaveButton().setOnAction(e -> submitChanges());
    }
    //Creating the columns for the tableview
    @SuppressWarnings("unchecked")
	private void makeTable() {
    	data = FXCollections.observableArrayList();
        this.debtorsView.getTableView().setMaxHeight(400);
    	 emailCol = new TableColumn<Order, Guest>("Gast email");
         emailCol.setMinWidth(250);
         emailCol.setCellValueFactory(new PropertyValueFactory<>("guest"));
         emailCol.setCellFactory(e -> {
             return new TableCell<Order, Guest>() {
            	 //allows showing the email of the guest instead of the modelnumber
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
        	 //checkbox that changes the boolean value according to if it's checked or not
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
        //clearing the columns to prevent double columns
        debtorsView.getTableView().getColumns().clear();
 		debtorsView.getTableView().setItems(data);
 		debtorsView.getTableView().getColumns().addAll(emailCol,activeCol);
        
    }
    //reads out all the debtors and adds them to the tableview array
    @SuppressWarnings("unchecked")
	private void generateDebtors() {
    	debtorsArrayList = orderDAO.getAllNativeOrders();
    	data.clear();
    	int j = 0;
    	while (debtorsArrayList.size() > j) {
    		data.add(debtorsArrayList.get(j));
    		j++;
    	}
    	debtorsArrayList.clear();
    	//updates the tableview so that the new data is shown
    	debtorsView.getTableView().getColumns().clear();
		debtorsView.getTableView().setItems(data);
		debtorsView.getTableView().getColumns().addAll(emailCol, activeCol);
    }
    //updates the changed data in the database
    private void submitChanges() {
	    int n = 0;
	    while(data.size() > n) {
	    	orderDAO.updateOrder(data.get(n));
	    	n++;
	    }
	    generateHandlers();
		debtorsArrayList.clear();
    }
    //clearing all content in the controller
	public void resetFields() {
		screensController.screenRemove(ControllersController.getATTENDANCEID());
		this.debtorsView = new DebtorsView();
		screensController.screenLoadSet(ControllersController.getATTENDANCEID(), debtorsView);
		generateHandlers();
	}
    
}
