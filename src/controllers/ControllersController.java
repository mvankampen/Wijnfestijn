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
   // private OrderListPrintController orderListPrintController;
    private OrderListController orderListController;
    private RegistrationController registrationController;
    
    private HomeView homeView;
    private MailView mailView;
    private OrderListPrintView orderListPrintView;
    private CustomersView customersView;
    private RegistrationView registrationView;
    private OrderListView orderListView;
    private DebtorsView debtorsView; 

    private static final String HOMEID = "home";
    private static final String ORDERLISTPRINTID = "orderlistprint";
    private static final String ORDERLISTID = "orderlist";
    private static final String MAILID = "mail";
    private static final String CUSTOMERSID = "customers";
    private static final String DEBITEURENID = "debiteuren";
    private static final String REGISTRATIONID = "registration";

    public ControllersController() {
    	createViews();
    	createControllers();
        fillScreensController();
    }

    
    private void createViews() {
    	homeView = new HomeView();
    	mailView = new MailView();
    	orderListPrintView = new OrderListPrintView();
    	customersView = new CustomersView();
    	registrationView = new RegistrationView();
    	orderListView = new OrderListView();
    	debtorsView = new DebtorsView();
    	
    }
    private void createControllers() {
        this.screensController = new ScreensController();
        this.homeController = new HomeController(homeView, this.screensController);
        this.navigationController = new NavigationController(this.screensController);
        this.customerController = new CustomerController(customersView, new CustomerDAO());
        this.debtorsController = new DebtorsController(debtorsView, new OrderDAO(), new CustomerDAO());
        this.mailController = new MailController(mailView);
    //    this.orderListPrintController = new OrderListPrintController(orderListPrintView);
        this.orderListController = new OrderListController(orderListView);
        this.registrationController = new RegistrationController(registrationView, new CustomerDAO(),
                new Customer());
    }
    public void fillScreensController() {
        // Id's komen nog uit applet, nog fixen.
    	screensController.screenLoad(getHOMEID(),homeView);
        screensController.screenLoad(getORDERLISTPRINTID(), orderListPrintView);
       	screensController.screenLoad(getMAILID(), mailView);
        screensController.screenLoad(getCUSTOMERSID(), customersView);
        screensController.screenLoad(getREGISTRATIONID(), registrationView);
        screensController.screenLoad(getORDERLISTID(), orderListView);
        screensController.screenLoad(getDEBITEURENID(), debtorsView);
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

    public static String getDEBITEURENID() {
        return DEBITEURENID;
    }

    public static String getREGISTRATIONID() {
        return REGISTRATIONID;
    }


	public static String getORDERLISTID() {
		return ORDERLISTID;
	}
}
