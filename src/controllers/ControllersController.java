package controllers;

import DAO.Database;
import DAO.GuestDAO;
import DAO.MailDAO;
import DAO.OrderDAO;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import models.Guest;
import views.*;

import java.sql.Connection;
import java.sql.SQLException;

/**
 * Created by Sander de Jong on 28-9-2015.
 */
public class ControllersController {
    private ScreensController screensController;
    private NavigationController navigationController;
    private CustomerController customerController;
    private DebtorsController debtorsController;
    private HomeController homeController;
    private MailController mailController;
    // private OrderListPrintController orderListPrintController;
    private OrderListController orderListController;
    private RegistrationController registrationController;
    private SettingsController settingsController;

    private HomeView homeView;
    private MailView mailView;
    private OrderListPrintView orderListPrintView;
    private CustomersView customersView;
    private RegistrationView registrationView;
    private OrderListView orderListView;
    private DebtorsView debtorsView;
    private Database database;
    private Connection connection;
    private NavigationView navigationView;
    private SettingsView settingsView;

    private static final String HOMEID = "home";
    private static final String ORDERLISTPRINTID = "orderlistprint";
    private static final String ORDERLISTID = "orderlist";
    private static final String MAILID = "mail";
    private static final String CUSTOMERSID = "customers";
    private static final String DEBTORID = "debiteuren";
    private static final String REGISTRATIONID = "registration";
    private static final String SETTINGSID = "settings";

    public ControllersController() {
        createDatabaseConnection();
        createViews();
        createControllers();
        fillScreensController();
    }

    public void createDatabaseConnection() {
        try {
            this.database = Database.getInstance();
            this.connection = database.getConnection();
        } catch (SQLException ex) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setHeaderText("Database Connection - ERROR");
            alert.setContentText("Er kan geen verbinding gemaakt worden met de database");
            alert.showAndWait().ifPresent(response -> {
                if (response == ButtonType.OK) {
                    System.exit(0);
                }
            });
        }
    }

    private void createViews() {
        homeView = new HomeView();
        mailView = new MailView();
        orderListPrintView = new OrderListPrintView();
        customersView = new CustomersView();
        registrationView = new RegistrationView();
        orderListView = new OrderListView();
        debtorsView = new DebtorsView();
        navigationView = new NavigationView();
        settingsView = new SettingsView();
    }

    private void createControllers() {
        this.screensController = new ScreensController();
        this.homeController = new HomeController(homeView, this.screensController);
        this.navigationController =
            new NavigationController(this.screensController, navigationView);
        this.customerController = new CustomerController(customersView, new GuestDAO(connection));
        this.debtorsController =
            new DebtorsController(debtorsView, new OrderDAO(connection), new GuestDAO(connection));
        this.mailController = new MailController(mailView, new MailDAO(connection));
        this.settingsController = new SettingsController(settingsView);
        // this.orderListPrintController = new
        // OrderListPrintController(orderListPrintView);
        this.orderListController = new OrderListController(orderListView, new GuestDAO(connection));
        this.registrationController =
            new RegistrationController(registrationView, new GuestDAO(connection));
    }

    public void fillScreensController() {
        // Id's komen nog uit applet, nog fixen.
        screensController.screenLoad(getHOMEID(), homeView);
        screensController.screenLoad(getORDERLISTPRINTID(), orderListPrintView);
        screensController.screenLoad(getMAILID(), mailView);
        screensController.screenLoad(getCUSTOMERSID(), customersView);
        screensController.screenLoad(getREGISTRATIONID(), registrationView);
        screensController.screenLoad(getORDERLISTID(), orderListView);
        screensController.screenLoad(getDEBTORID(), debtorsView);
        screensController.screenLoad(getSETTINGSID(), settingsView);
        screensController.screenSet(getHOMEID());
    }

    public ScreensController getScreensController() {
        return screensController;
    }

    public NavigationController getNavigationController() {
        return navigationController;
    }

    public static String getHOMEID() {
        return HOMEID;
    }

    public static String getORDERLISTPRINTID() {
        return ORDERLISTPRINTID;
    }

    public static String getMAILID() {
        return MAILID;
    }

    public static String getCUSTOMERSID() {
        return CUSTOMERSID;
    }

    public static String getDEBTORID() {
        return DEBTORID;
    }

    public static String getREGISTRATIONID() {
        return REGISTRATIONID;
    }

    public static String getORDERLISTID() {
        return ORDERLISTID;
    }

    public static String getSETTINGSID() {
        return SETTINGSID;
    }

    public NavigationView getNavigationView() {
        return navigationView;
    }
}
