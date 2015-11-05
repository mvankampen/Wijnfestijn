package controllers;

import DAO.*;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import models.Mail;
import services.MailService;
import views.*;

import java.sql.Connection;
import java.sql.SQLException;

/**
 * Created by Sander de Jong on 28-9-2015.
 */
public class ControllersController {
	private ScreensController screensController;
	private NavigationController navigationController;
	private MailService mailService;
	private HomeView homeView;
	private MailView mailView;
	private OrderListPrintView orderListPrintView;
	private ImportGuestListView importGuestListView;
	private AdjustGuestView adjustGuestView;
	private RegistrationView registrationView;
	private OrderView orderView;
	private DebtorsView debtorsView;
	private Database database;
	private Connection connection;
	private NavigationView navigationView;
	private SettingsView settingsView;
	private ImportWineListView importWineListView;
	private AttendanceView attendanceView;
	private AdjustGuestController adjustGuestController;
	private DebtorsController debtorsController;
	private RegistrationController registrationController;
	private AttendanceController attendanceController;
	private OrderController orderController;
	private ImportGuestListController importGuestListController;
	private ImportWineListController importWineListController;
	private MailController mailController;
	private OrderListPrintController orderListPrintController;
	/*
	 * strings used for screensController so we can identify which view is which
	 * easily
	 */
	private static final String HOMEID = "home";
	private static final String ORDERLISTPRINTID = "orderlistprint";
	private static final String ORDERID = "order";
	private static final String MAILID = "mail";
	private static final String GUESTID = "Guest";
	private static final String DEBTORID = "debiteuren";
	private static final String REGISTRATIONID = "registration";
	private static final String SETTINGSID = "settings";
	private static final String IMPORTWINELISTID = "import list";
	private static final String IMPORTGUESTLISTID = "importguest";
	private static final String ATTENDANCEID = "attendance";

	public ControllersController() {
		createDatabaseConnection();
		createViews();
		createControllers();
		fillScreensController();
	}

	/*
	 * tries to establish the connection with the database through the class
	 * Database
	 */
	public void createDatabaseConnection() {
		try {
			this.database = Database.getInstance();
			this.connection = database.getConnection();
		} catch (SQLException ex) {
			/* shows a alert box that shows the user that the database could not
			* be connected with */ 
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

	/*
	 * creating all views beforehand so we can link them to the controller
	 */
	private void createViews() {
		homeView = new HomeView();
		mailView = new MailView(new Mail("", ""));
		orderListPrintView = new OrderListPrintView();
		adjustGuestView = new AdjustGuestView();
		registrationView = new RegistrationView();
		orderView = new OrderView();
		importGuestListView = new ImportGuestListView();
		debtorsView = new DebtorsView();
		navigationView = new NavigationView();
		settingsView = new SettingsView();
		importWineListView = new ImportWineListView();
		attendanceView = new AttendanceView();

	}

	// Creating the controllers
	private void createControllers() {
		this.orderListPrintController = new OrderListPrintController(this.orderListPrintView, new WineDAO(this.connection));
		this.screensController = new ScreensController();
		this.mailService = new MailService();
		new HomeController(homeView, this.screensController, this);
		this.navigationController = new NavigationController(this.screensController, navigationView, this);
		adjustGuestController = new AdjustGuestController(adjustGuestView, new GuestDAO(connection), screensController);
		setDebtorsController(new DebtorsController(debtorsView, new OrderDAO(connection), new GuestDAO(connection), screensController));
		setMailController(new MailController(mailView, new MailDAO(connection), this.mailService, screensController));
		new SettingsController(settingsView);
		orderController = new OrderController(orderView, new GuestDAO(connection), new WineDAO(connection), new OrderLineDAO(connection),
				new OrderDAO(connection), screensController, mailService);
		setRegistrationController(new RegistrationController(registrationView, new GuestDAO(connection), screensController));
		setImportGuestListController(new ImportGuestListController(importGuestListView, this.screensController, new GuestDAO(connection)));
		setImportWineListController(new ImportWineListController(importWineListView, this.screensController, new WineDAO(connection)));
		setAttendanceController(new AttendanceController(attendanceView, new GuestDAO(connection), screensController));
	}

	/*
	 * filling the screenscontroller with all the views that we just made and
	 * linking them with their strings so we can find them in the hashmap of
	 * screenscontroller with ease
	 */
	public void fillScreensController() {
		screensController.screenLoad(getHOMEID(), homeView);
		screensController.screenLoad(getORDERLISTPRINTID(), orderListPrintView);
		screensController.screenLoad(getMAILID(), mailView);
		screensController.screenLoad(getGUESTID(), adjustGuestView);
		screensController.screenLoad(getREGISTRATIONID(), registrationView);
		screensController.screenLoad(getORDERID(), orderView);
		screensController.screenLoad(getDEBTORID(), debtorsView);
		screensController.screenLoad(getSETTINGSID(), settingsView);
		screensController.screenLoad(getIMPORTWINELISTID(), importWineListView);
		screensController.screenLoad(getIMPORTGUESTLISTID(), importGuestListView);
		screensController.screenLoad(getATTENDANCEID(), attendanceView);
		// sets the screen that we want to show on start of the application
		screensController.screenSet(getHOMEID());
	}

	// getters
	public ScreensController getScreensController() {
		return screensController;
	}

	public NavigationController getNavigationController() {
		return navigationController;
	}
	public NavigationView getNavigationView() {
		return navigationView;
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

	public static String getGUESTID() {
		return GUESTID;
	}

	public static String getDEBTORID() {
		return DEBTORID;
	}

	public static String getREGISTRATIONID() {
		return REGISTRATIONID;
	}

	public static String getORDERID() {
		return ORDERID;
	}

	public static String getSETTINGSID() {
		return SETTINGSID;
	}

	public static String getIMPORTWINELISTID() {
		return IMPORTWINELISTID;
	}

	public static String getATTENDANCEID() {
		return ATTENDANCEID;
	}

	public static String getIMPORTGUESTLISTID() {
		return IMPORTGUESTLISTID;
	}
	public AdjustGuestController getAdjustGuestControler() {
		return adjustGuestController;
	}

	public DebtorsController getDebtorsController() {
		return debtorsController;
	}

	public void setDebtorsController(DebtorsController debtorsController) {
		this.debtorsController = debtorsController;
	}

	public RegistrationController getRegistrationController() {
		return registrationController;
	}

	public void setRegistrationController(RegistrationController registrationController) {
		this.registrationController = registrationController;
	}

	public AttendanceController getAttendanceController() {
		return attendanceController;
	}

	public void setAttendanceController(AttendanceController attendanceController) {
		this.attendanceController = attendanceController;
	}

	public OrderController getOrderController() {
		return orderController;
	}

	public void setOrderController(OrderController orderController) {
		this.orderController = orderController;
	}

	public ImportGuestListController getImportGuestListController() {
		return importGuestListController;
	}

	public void setImportGuestListController(ImportGuestListController importGuestListController) {
		this.importGuestListController = importGuestListController;
	}

	public ImportWineListController getImportWineListController() {
		return importWineListController;
	}

	public void setImportWineListController(ImportWineListController importWineListController) {
		this.importWineListController = importWineListController;
	}

	public MailController getMailController() {
		return mailController;
	}

	public void setMailController(MailController mailController) {
		this.mailController = mailController;
	}

}
