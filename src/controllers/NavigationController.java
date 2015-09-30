package controllers;

import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;

public class NavigationController extends AnchorPane {
    private Button homeButton, mailButton, debtorsButton;
    private ComboBox<String> orderMenu;
    private ComboBox<String> customerMenu;
    private ObservableList<String> orderOptions;
    private ObservableList<String> customerOptions;
    private ScreensController screensController;
    
    // Create names for links
    private final String ORDERTITLE = "Bestellijsten";
    private final String ORDER1 = "Print lijsten";
    private final String ORDER2 = "Order opstellen";
    private final String CUSTOMERTITLE = "Klanten";
    private final String CUSTOMER1 = "Aanpassen";
    private final String CUSTOMER2 = "Registratie";

    public NavigationController(ScreensController screensController) {
        this.screensController = screensController;
        createNavbar();
    }

    public void createNavbar() {
     //generateDropDownOptions();
     generateContentGrid();
    }
    public void generateContentGrid(){
    	orderOptions = FXCollections.observableArrayList(ORDER1, ORDER2);
        customerOptions = FXCollections.observableArrayList(CUSTOMER1, CUSTOMER2);
    	
        //Make the gridpane for the Navigation buttons
        GridPane navGrid = new GridPane();
        HBox hbButtons = new HBox();
        navGrid.setVgap(59);
        navGrid.setHgap(10);
        
        //Home Button
        homeButton = new Button("Home");
        homeButton.getStyleClass().add("nav_item");
        //Mail Button
        mailButton = new Button("Mail Menu");
        mailButton.getStyleClass().add("nav_item");
        //Order ComboBox
        orderMenu = new ComboBox<>(orderOptions);
        orderMenu.getStyleClass().add("nav_item");
        orderMenu.setValue(ORDERTITLE);
        orderMenu.getSelectionModel().selectedItemProperty().addListener(new ChangeListener<String>() {
            @Override
            public void changed(ObservableValue<? extends String> observable, String oldValue, String newValue) {
            	customerMenu.setValue(CUSTOMERTITLE);
            	if (newValue != null) {
	            	switch(newValue) {
	            	case ORDER1:
	            		screensController.screenSet(ControllersController.getORDERLISTPRINTID());
	            		break;
	            	case ORDER2:
	            		screensController.screenSet(ControllersController.getORDERLISTID());
	            		break;
	            	default:
	            	}
                }
            }
        });

        //Customer Button
        customerMenu = new ComboBox<String>(customerOptions);
        customerMenu.setValue(CUSTOMERTITLE);
        customerMenu.getStyleClass().add("nav_item");
        customerMenu.getSelectionModel().selectedItemProperty().addListener(new ChangeListener<String>() {
            @Override
            public void changed(ObservableValue<? extends String> observable, String oldValue, String newValue) {
            	orderMenu.setValue(ORDERTITLE);
            	if (newValue != null) {
	            	switch(newValue) {
	            	case CUSTOMER1:
	            		screensController.screenSet(ControllersController.getCUSTOMERSID());
	            		break;
	            	case CUSTOMER2:
	            		screensController.screenSet(ControllersController.getREGISTRATIONID());
	            		break;
	            	default:
	            	}
                }
            }
        });
        //Debtors Button
        debtorsButton = new Button("Debiteuren Menu");
        debtorsButton.getStyleClass().add("nav_end");
        /*make lambda event handling*/
        //For the home button, set HomeScreen
        homeButton.setOnAction(e -> {
            screensController.screenSet(ControllersController.getHOMEID());
            setComboBoxDefault();
        });
        //For the mail button, set MailScreen
        mailButton.setOnAction(e -> {
            screensController.screenSet(ControllersController.getMAILID());
            setComboBoxDefault();
        });
        //For the Customer button, set CustomerScreen
        // debtor button event.
        debtorsButton.setOnAction(e -> {
            screensController.screenSet(ControllersController.getDEBITEURENID());
            setComboBoxDefault();
        });
        //make the NavBar
        hbButtons.getChildren().addAll(homeButton, mailButton, orderMenu, 
          customerMenu, debtorsButton);
        navGrid.add(hbButtons, 2, 2);
        getChildren().addAll(navGrid);
}
    // Set the ComboBox back to default value
    public void setComboBoxDefault(){
    	orderMenu.setValue(ORDERTITLE);
        customerMenu.setValue(CUSTOMERTITLE);
    }
}