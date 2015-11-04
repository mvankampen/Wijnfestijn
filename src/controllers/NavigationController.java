package controllers;

import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.scene.layout.AnchorPane;
import views.NavigationView;

public class NavigationController extends AnchorPane {
    private NavigationView navigationView;
    private ScreensController screensController;
    private ControllersController CC;

    public NavigationController(ScreensController screensController,
        NavigationView navigationView, ControllersController controllersController) {
        this.screensController = screensController;
        this.navigationView = navigationView;
        this.CC = controllersController;
        generateHandlers();
    }

    public void generateHandlers() {
        navigationView.getOrderMenu().getSelectionModel().selectedItemProperty()
            .addListener(new ChangeListener<String>() {
                @Override
                public void changed(ObservableValue<? extends String> observable, String oldValue,
                    String newValue) {
                    navigationView.getCustomerMenu().setValue(navigationView.CUSTOMERTITLE);
                    if (newValue != null) {
                        if (newValue.equals(navigationView.ORDER1)) {
                            screensController.screenSet(ControllersController.getORDERLISTPRINTID());
                        } else if (newValue.equals(navigationView.ORDER2)) {
                        	CC.getOrderController().resetFields();
                        } else if (newValue.equals(navigationView.ORDER3)) {
                            CC.getImportWineListController().resetFields();
                        } else if (newValue.equals(navigationView.ORDER4)) {
                        	CC.getImportGuestListController().resetFields();
                        }
                    }
                }
            });

        navigationView.getCustomerMenu().getSelectionModel().selectedItemProperty()
            .addListener(new ChangeListener<String>() {
                @Override
                public void changed(ObservableValue<? extends String> observable, String oldValue,
                    String newValue) {
                    navigationView.getOrderMenu().setValue(navigationView.ORDERTITLE);
                    if (newValue != null) {
                        if (newValue.equals(navigationView.GUEST1)) {
                        	//resetFields creates a new view and reloads the values of the DB
                        	CC.getAdjustGuestControler().resetFields();
                        } else if (newValue.equals(navigationView.GUEST2)) {
                        	CC.getRegistrationController().resetFields();
                        } else if (newValue.equals(navigationView.GUEST3)) {
                        	CC.getDebtorsController().resetFields();
                        } else if (newValue.equals(navigationView.GUEST4)) {
                            CC.getAttendanceController().resetFields();
                        }
                    }
                }
            });
        //For the home button, set HomeScreen
        navigationView.getHomeButton().setOnAction(e -> {
            screensController.screenSet(ControllersController.getHOMEID());
            setComboBoxDefault();
        });
        //For the mail button, set MailScreen
        navigationView.getMailButton().setOnAction(e -> {
           	CC.getMailController().resetFields();
            setComboBoxDefault();
        });
        //For the Customer button, set CustomerScreen
        navigationView.getSettingsButton().setOnAction(e -> {
            screensController.screenSet(ControllersController.getSETTINGSID());
            setComboBoxDefault();
        });
    }

    // Set the ComboBox back to default value
    public void setComboBoxDefault() {
        navigationView.getOrderMenu().setValue(navigationView.ORDERTITLE);
        navigationView.getCustomerMenu().setValue(navigationView.CUSTOMERTITLE);
    }
}
