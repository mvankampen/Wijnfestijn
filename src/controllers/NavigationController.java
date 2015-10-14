package controllers;

import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.scene.layout.AnchorPane;
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
	            		screensController.screenSet(ControllersController.getORDERID());
	            	}
	            	else if(newValue.equals(navigationView.ORDER3)){
	            		screensController.screenSet(ControllersController.getIMPORTWINELISTID());
	            	}
                }
            }
        });
    	
    	navigationView.customerMenu.getSelectionModel().selectedItemProperty().addListener(new ChangeListener<String>() {
            @Override
            public void changed(ObservableValue<? extends String> observable, String oldValue, String newValue) {
            	navigationView.orderMenu.setValue(navigationView.ORDERTITLE);
            	if (newValue != null) {
            		if(newValue.equals(navigationView.CUSTOMER1)){
            			screensController.screenSet(ControllersController.getGUESTID());
	            	}
	            	else if(newValue.equals(navigationView.CUSTOMER2)){
	            		screensController.screenSet(ControllersController.getREGISTRATIONID());
	            	}
	            	else if(newValue.equals(navigationView.CUSTOMER3)){
	            		screensController.screenSet(ControllersController.getDEBTORID());
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
    	navigationView.settingsButton.setOnAction( e ->{
    		screensController.screenSet(ControllersController.getSETTINGSID());
    		setComboBoxDefault();
    	});
    }
    // Set the ComboBox back to default value
    public void setComboBoxDefault(){
    	navigationView.orderMenu.setValue(navigationView.ORDERTITLE);
    	navigationView.customerMenu.setValue(navigationView.CUSTOMERTITLE);
    }
}
