import controllers.NavigationController;
import controllers.ScreensController;
import javafx.application.Application;

import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.stage.Stage;
import views.CustomersView;
import views.HomeView;
import views.MailView;
import views.OrderlistPrintView;

public class Applet extends Application {
    private static Stage stage;
    private static ScreensController screensController;
    private NavigationController navigationController;
    private static final String HOMEID = "home";
    private static final String ORDERLISTPRINTID = "orderlistprint";
    private static final String MAILID = "mail";
    private static final String CUSTOMERSID = "customers";
    private static final String DEBITEURENID = "debiteuren";
    
    public void start(Stage stage) {
    	//maakt de controller voor de schermen aan, handelt het display van de schermen af.
        fillScreensController();
        navigationController = new NavigationController(screensController);
        //zet wel scherm er actief moet zijn
        Group root = new Group();
        root.getChildren().addAll(screensController,navigationController);
        Scene scene = new Scene(root,1200,800);
        scene.getStylesheets().addAll(this.getClass().getResource("style/style.css").toExternalForm());
        stage.setScene(scene);
        stage.setWidth(1200);
        stage.setHeight(800);
        stage.setResizable(false);
        stage.setTitle("A WarnerBrothers Product");
        stage.show();

    };
    private void fillScreensController(){
    	screensController = new ScreensController();
    	screensController.screenLoad(Applet.getHomeid(), new HomeView());
        screensController.screenLoad(Applet.getOrderlistprintid(), new OrderlistPrintView());
        screensController.screenLoad(Applet.getMailid(), new MailView());
        screensController.screenLoad(Applet.getCustomersid(), new CustomersView());
        screensController.screenSet(Applet.getHomeid());
    }

    public static void main(String[] args) {
        launch(args);
    }

	public static String getHomeid() {
		return HOMEID;
	}
	public static String getOrderlistprintid() {
		return ORDERLISTPRINTID;
	}

	public static String getMailid() {
		return MAILID;
	}

	public static String getDebiteurenid() {
		return DEBITEURENID;
	}

	public static String getCustomersid() {
		return CUSTOMERSID;
	}

}
