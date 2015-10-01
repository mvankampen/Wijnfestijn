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
import views.NavigationView;

public class NavigationController extends AnchorPane {
    private NavigationView navigationView;
    private ScreensController screensController;

    public NavigationController(ScreensController screensController, NavigationView navigationView) {
        this.screensController = screensController;
        this.navigationView = navigationView;
        generateHandlers();
    }

    public void generateHandlers(){
    	navigationView.orderMenu.getSelectionModel().selectedItemProperty().addListener(new ChangeListener<String>() {
            @Override
            public void changed(ObservableValue<? extends String> observable, String oldValue, String newValue) {
            	navigationView.customerMenu.setValue(navigationView.CUSTOMERTITLE);
            	if (newValue != null) {
	            	if(newValue.equals(navigationView.ORDER1)){
	            		screensController.screenSet(ControllersController.getORDERLISTPRINTID());
	            	}
	            	else if(newValue.equals(navigationView.ORDER2)){
	            		screensController.screenSet(ControllersController.getORDERLISTID());
	            	}
                }
            }
        });
    	
    	navigationView.customerMenu.getSelectionModel().selectedItemProperty().addListener(new ChangeListener<String>() {
            @Override
            public void changed(ObservableValue<? extends String> observable, String oldValue, String newValue) {
            	navigationView.orderMenu.setValue(navigationView.ORDERTITLE);
            	if (newValue != null) {
            		if(newValue.equals(navigationView.ORDER1)){
            			screensController.screenSet(ControllersController.getCUSTOMERSID());
	            	}
	            	else if(newValue.equals(navigationView.ORDER2)){
	            		screensController.screenSet(ControllersController.getREGISTRATIONID());
	            	}
                }
            }
        });
    	//For the home button, set HomeScreen
    	navigationView.homeButton.setOnAction(e -> {
            screensController.screenSet(ControllersController.getHOMEID());
            setComboBoxDefault();
        });
        //For the mail button, set MailScreen
    	navigationView.mailButton.setOnAction(e -> {
            screensController.screenSet(ControllersController.getMAILID());
            setComboBoxDefault();
        });
        //For the Customer button, set CustomerScreen
        // debtor button event.
    	navigationView.debtorsButton.setOnAction(e -> {
            screensController.screenSet(ControllersController.getDEBITEURENID());
            setComboBoxDefault();
        });
    }
    // Set the ComboBox back to default value
    public void setComboBoxDefault(){
    	navigationView.orderMenu.setValue(navigationView.ORDERTITLE);
    	navigationView.customerMenu.setValue(navigationView.CUSTOMERTITLE);
    }
}