package views;

import DAO.Database;
import controllers.ControllersController;
import controllers.NavigationController;
import controllers.ScreensController;
import javafx.application.Application;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.stage.Stage;

import java.sql.SQLException;

public class Applet extends Application {
    private static Stage stage;
    private Database database;
    private ControllersController CC;
    private static final String HOMEID = "home";
    private static final String ORDERLISTPRINTID = "orderlistprint";
    private static final String MAILID = "mail";
    private static final String CUSTOMERSID = "customers";
    private static final String DEBITEURENID = "debiteuren";
    private static final String REGISTRATIONID = "registration";

    public void start(Stage stage) throws SQLException {
//        try {
//            this.database = Database.getInstance();
//        } catch (SQLException ex) {
//            Alert alert = new Alert(Alert.AlertType.ERROR);
//            alert.setHeaderText("Database Connection - ERROR");
//            alert.setContentText("Er kan geen verbinding gemaakt worden met de database");
//            alert.showAndWait().ifPresent(response -> {
//                if (response == ButtonType.OK) {
//                    System.exit(0);
//                }
//            });
//        }

        //maakt de controller voor de schermen aan, handelt het display van de schermen af.
        this.CC = new ControllersController();
        fillScreensController();
        //zet wel scherm er actief moet zijn
        Group root = new Group();
        root.getChildren().addAll(CC.getScreensController(), this.CC.getNavigationController());
        Scene scene = new Scene(root, 1200, 800);
        scene.getStylesheets()
            .addAll(this.getClass().getResource("../style/style.css").toExternalForm());
        stage.setScene(scene);
        stage.setWidth(1200);
        stage.setHeight(800);
        stage.setResizable(false);
        stage.setTitle("A WarnerBrothers Product");
        stage.show();


    }

    private void fillScreensController() {
        // Id's komen nog uit applet, nog fixen.
        this.CC.getScreensController().screenLoad(this.CC.getHOMEID(), new HomeView());
        this.CC.getScreensController().screenLoad(this.CC.getORDERLISTPRINTID(), new OrderlistPrintView());
        this.CC.getScreensController().screenLoad(this.CC.getMAILID(), new MailView());
        this.CC.getScreensController().screenLoad(this.CC.getCUSTOMERSID(), new CustomersView());
        this.CC.getScreensController().screenLoad(this.CC.getREGISTRATIONID(), new RegistrationView());
        this.CC.getScreensController().screenLoad(this.CC.getDEBITEURENID(), new DebtorsView());
        this.CC.getScreensController().screenSet(this.CC.getHOMEID());
    }
}
