package controllers;

import DAO.CustomerDAO;
import DAO.OrderDAO;
import models.Customer;
import models.Mail;
import services.MailService;
import views.*;

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
    private OrderlistPrintController orderlistPrintController;
    private OrderListController orderListController;
    private RegistrationController registrationController;

    private static final String HOMEID = "home";
    private static final String ORDERLISTPRINTID = "orderlistprint";
    private static final String MAILID = "mail";
    private static final String CUSTOMERSID = "customers";
    private static final String DEBITEURENID = "debiteuren";
    private static final String REGISTRATIONID = "registration";

    public ControllersController() {
        createControllers();
    }

    private void createControllers() {
        this.screensController = new ScreensController();
        this.navigationController = new NavigationController(this.screensController);
        this.customerController = new CustomerController(new CustomersView(), new CustomerDAO());
        this.debtorsController = new DebtorsController(new DebtorsView(), new OrderDAO(), new CustomerDAO());
        this.homeController = new HomeController(new HomeView());
        this.mailController = new MailController(new MailView());
        this.orderlistPrintController = new OrderlistPrintController(new OrderlistPrintView());
        this.orderListController = new OrderListController(new OrderListView());
        this.registrationController = new RegistrationController(new RegistrationView(), new CustomerDAO(),
                new Customer());
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

    public static String getDEBITEURENID() {
        return DEBITEURENID;
    }

    public static String getREGISTRATIONID() {
        return REGISTRATIONID;
    }
}
