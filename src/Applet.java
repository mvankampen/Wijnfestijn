import javafx.application.Application;

import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class Applet extends Application {
    private static Stage stage;
    private static ScreensController screenController;
    private NavigationController navigationController;
    private static final String HOMEID = "home";
    private static final String ORDERLISTID = "orderlist";
    
    public void start(Stage stage) {
    	//maakt de controller voor de schermen aan, handelt het display van de schermen af.
        screenController = new ScreensController();
        navigationController = new NavigationController();
        screenController.schermLaden(Applet.getHomeid(), new HomeView(navigationController));
        screenController.schermLaden(Applet.ORDERLISTID, new OrderListView());
        //zet wel scherm er actief moet zijn
        screenController.setScherm(Applet.getHomeid());
        //Voor jou sander scherm sander, comment hoofdscherm weg en enable deze
        //schermController.setScherm(Applet.getOrderlistid());
        Group root = new Group();
        root.getChildren().add(screenController);
        Scene scene = new Scene(root,1200,800);
        scene.getStylesheets().addAll(this.getClass().getResource("style/style.css").toExternalForm());
        stage.setScene(scene);
        stage.setWidth(1200);
        stage.setHeight(800);
        stage.setResizable(false);
        stage.show();

    };

    public static void main(String[] args) {
        launch(args);
    }

	public static String getHomeid() {
		return HOMEID;
	}
	public static String getOrderlistid() {
		return ORDERLISTID;
	}

}
