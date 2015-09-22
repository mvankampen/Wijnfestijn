import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;

public class NavigationController extends AnchorPane {
    private Button homeButton, mailButton, debtorsButton;
    private ComboBox<String> orderMenu;
    private ComboBox<String> customerMenu;
    private ObservableList<String> orderOptions;
    private ObservableList<String> customerOptions;
    private ScreensController screensController;

    public NavigationController(ScreensController screensController) {
        this.screensController = screensController;
        generateDropDownOptions();
        createMenu();
    }

    public void createMenu() {
        //Home Button
        GridPane navGrid = new GridPane();
        navGrid.setVgap(59);
        navGrid.setHgap(10);
        HBox hbButtons = new HBox();
        homeButton = new Button("Home");

        homeButton.getStyleClass().add("nav_item");
        homeButton.setOnAction(e -> {
            screensController.screenSet(Applet.getHomeid());

        });
        //Mail Button
        mailButton = new Button("Mail Menu");
        mailButton.getStyleClass().add("nav_item");
        mailButton.setOnAction(e -> {
            screensController.screenSet(Applet.getMailid());

        });
        //Order ComboBox
        orderMenu = new ComboBox<String>(orderOptions);
        orderMenu.setValue("Bestel Menu");
        orderMenu.getStyleClass().add("nav_item");
        //Customer ComboBox
        customerMenu = new ComboBox<String>(customerOptions);
        customerMenu.setValue("Klanten Menu");
        customerMenu.getStyleClass().add("nav_item");

        //Debtors Button
        debtorsButton = new Button("Debiteuren Menu");
        debtorsButton.getStyleClass().add("nav_end");

        //make the NavBar
        hbButtons.getChildren()
            .addAll(homeButton, mailButton, orderMenu, customerMenu, debtorsButton);
        navGrid.add(hbButtons, 2, 2);
        getChildren().addAll(navGrid);
    }


    private void generateDropDownOptions() {
        orderOptions = FXCollections.observableArrayList("option 1", "option 2");// not done
        customerOptions = FXCollections.observableArrayList("registratie", "bewerken");
    }



}
