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
 *<p>Description: this Controller is used to have a central class
 *where all vital elements are created so that the system can start.
 *In this class you will find the creation of the database connection,
 *the Views, the Controllers, and the procedure to make the Screenscontroller work correctly </p>
 *@author Alex van der wal
 *@author Sander de Jong 
 *@author Dennis Sloove
 *@author Michael van Kampen
 *@version v1.0
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
	
	/**
	 * Constructor
	 */
	public ControllersController() {
		createDatabaseConnection();
		createViews();
		createControllers();
		fillScreensController();
	}

	/**
	 * <p>tries to establish the connection with the database through the class
	 * Database</p>
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

	/**
	 * <p>creating all views beforehand so we can link them to the controller</p>
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

	/**
	 * <p> Used to create all controllers used by the System </p>
	 */
	private void createControllers() {
		this.orderListPrintController = new OrderListPrintController(this.orderListPrintView, new WineDAO(this.connection),new GuestDAO(this.connection));
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

	/**
	 * <p>filling the screenscontroller with all the views that we just made and
	  linking them with their strings so we can find them in the hashmap of
	  screenscontroller with ease </p>
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
		screensController.screenSet(getHOMEID());
	}

	/**
	 * @return the screenController
	 */
	public ScreensController getScreensController() {
		return screensController;
	}
	/**
	 * @return the navigationController
	 */
	public NavigationController getNavigationController() {
		return navigationController;
	}
	/**
	 * @return the navigationView
	 */
	public NavigationView getNavigationView() {
		return navigationView;
	}
	/**
	 * @return the Home ID
	 */
	public static String getHOMEID() {
		return HOMEID;
	}
	/**
	 * @return the Orderlistprint ID
	 */
	public static String getORDERLISTPRINTID() {
		return ORDERLISTPRINTID;
	}
	/**
	 * @return the Mail ID
	 */
	public static String getMAILID() {
		return MAILID;
	}
	/**
	 * @return the Guest ID
	 */
	public static String getGUESTID() {
		return GUESTID;
	}
	/**
	 * @return the Debtor ID
	 */
	public static String getDEBTORID() {
		return DEBTORID;
	}
	/**
	 * @return the Registration ID
	 */
	public static String getREGISTRATIONID() {
		return REGISTRATIONID;
	}
	/**
	 * @return the Order ID
	 */
	public static String getORDERID() {
		return ORDERID;
	}
	/**
	 * @return the Settings ID
	 */
	public static String getSETTINGSID() {
		return SETTINGSID;
	}
	/**
	 * @return the Importwinelist ID
	 */
	public static String getIMPORTWINELISTID() {
		return IMPORTWINELISTID;
	}
	/**
	 * @return the Attendance ID
	 */
	public static String getATTENDANCEID() {
		return ATTENDANCEID;
	}
	/**
	 * @return the Importguestlist ID
	 */
	public static String getIMPORTGUESTLISTID() {
		return IMPORTGUESTLISTID;
	}
	/**
	 * @return the Adjustguestcontroller
	 */
	public AdjustGuestController getAdjustGuestControler() {
		return adjustGuestController;
	}
	/**
	 * @return the debtorsController
	 */
	public DebtorsController getDebtorsController() {
		return debtorsController;
	}
	/**
	 * @param debtorsController used to set the debtorsController that is used
	 */
	public void setDebtorsController(DebtorsController debtorsController) {
		this.debtorsController = debtorsController;
	}
	/**
	 * @return the registrationController
	 */
	public RegistrationController getRegistrationController() {
		return registrationController;
	}
	/**
	 * @param registrationController used to set the registrationController that is used
	 */
	public void setRegistrationController(RegistrationController registrationController) {
		this.registrationController = registrationController;
	}
	
	/**
	 * @return the attendanceController
	 */
	public AttendanceController getAttendanceController() {
		return attendanceController;
	}
	
	/**
	 * @param attendanceController used to set the attendanceController that is used
	 */
	public void setAttendanceController(AttendanceController attendanceController) {
		this.attendanceController = attendanceController;
	}
	/**
	 * 
	 * @return the orderController
	 */
	public OrderController getOrderController() {
		return orderController;
	}
	/**
	 * 
	 * @param orderController used to set the orderController that is used
	 */
	public void setOrderController(OrderController orderController) {
		this.orderController = orderController;
	}
	/**
	 * 
	 * @return the importGuestlistController
	 */
	public ImportGuestListController getImportGuestListController() {
		return importGuestListController;
	}
	
	/**
	 * 
	 * @param importGuestListController used to set the importGuestlistController that is used
	 */
	public void setImportGuestListController(ImportGuestListController importGuestListController) {
		this.importGuestListController = importGuestListController;
	}

	/**
	 * 
	 * @return the importWineListController
	 */
	public ImportWineListController getImportWineListController() {
		return importWineListController;
	}
	/**
	 * 
	 * @param importWineListController used to set the importWineListController that is used
	 */
	public void setImportWineListController(ImportWineListController importWineListController) {
		this.importWineListController = importWineListController;
	}
	/**
	 * 
	 * @return the mailController
	 */
	public MailController getMailController() {
		return mailController;
	}
	
	/**
	 * 
	 * @param mailController used to set the mailController that is used
	 */
	public void setMailController(MailController mailController) {
		this.mailController = mailController;
	}

}
