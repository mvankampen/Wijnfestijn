package controllers;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import views.Applet;


public class NavigationController extends AnchorPane {
    private Button homeButton, mailButton, customersButton, debtorsButton;
    private ComboBox<String> orderMenu;
    private ObservableList<String> orderOptions;
    private ScreensController screensController;

    public NavigationController(ScreensController screensController) {
        this.screensController = screensController;
        createNavbar();
    }

    public void createNavbar() {
     generateDropDownOptions();
     generateContentGrid();
    }
    public void generateContentGrid(){
     //Make the gridpane for the Navigation buttons
        GridPane navGrid = new GridPane();
        HBox hbButtons = new HBox();
        navGrid.setVgap(59);
        navGrid.setHgap(10);
        //Home Button
        homeButton = new Button("Home");
        homeButton.getStyleClass().add("nav_item");
        //Mail Button
        mailButton = new Button("Mail Menu");
        mailButton.getStyleClass().add("nav_item");
        //Order ComboBox
        orderMenu = new ComboBox<String>(orderOptions);
        orderMenu.getStyleClass().add("nav_item");
        orderMenu.setValue("Bestel Menu");
        //Customer Button
        customersButton = new Button("Klanten Menu");
        customersButton.getStyleClass().add("nav_item");
        //Debtors Button
        debtorsButton = new Button("Debiteuren Menu");
        debtorsButton.getStyleClass().add("nav_end");
        /*make lambda event handling*/
        //For the home button, set HomeScreen
        homeButton.setOnAction(e -> {
            screensController.screenSet(Applet.getHomeid());
        });
        //For the mail button, set MailScreen
        mailButton.setOnAction(e -> {
            screensController.screenSet(Applet.getMailid());
        });
        //For the Customer button, set CustomerScreen
        customersButton.setOnAction(e -> {
            screensController.screenSet(Applet.getCustomersid());
        });
        //make the NavBar
        hbButtons.getChildren().addAll(homeButton, mailButton, orderMenu, 
          customersButton, debtorsButton);
        navGrid.add(hbButtons, 2, 2);
        getChildren().addAll(navGrid);
}
    //This will be generating the dropdown options, Event handling in progress
    private void generateDropDownOptions() {
        orderOptions = FXCollections.observableArrayList("option 1", "option 2");
    }
}