package views;

import controllers.ScreensController;

import interfaces.ControlledScreen;
import javafx.fxml.FXML;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
//This screen is a AnchorPane and uses ControlledScreen as navigation manager
public class OrderListPrintView extends AnchorPane implements ControlledScreen {

    @FXML Label introLabel, exampleLabel, listLabel, amountLabel;
    @FXML Button printButton;
    @FXML ComboBox listItems;
    @FXML TextArea listArea;

    public void setScreenController(ScreensController screensController) {
    	/*
		 * Used for registering itself in the hashMap of the ScreensController
		 * to enable navigation
		 */
    }

    public OrderListPrintView() {
        createView();
        setUpContentPane();
    }
    
    // adds the style class and sets the fixed height to the screen
    private void createView() {
        getStyleClass().add("background");
        setMinSize(1200, 800);
       
    }

    public void setUpContentPane() {
    	// creating the gridpane, this is where all the displayed content goes
        GridPane contentPane = new GridPane();
        contentPane.setLayoutX(100);
        contentPane.setLayoutY(200);
        /* Creating all vboxes that are used to organize the sectors used in the
		* contentPane
		*/ 
        HBox introBox = new HBox();
        VBox vertBox1 = new VBox();
        vertBox1.setSpacing(20);
        VBox vertBox2 = new VBox(10);
        vertBox2.setPadding(new Insets(0, 0, 0, 40));
        VBox buttonBox = new VBox();
        /*
		 * this label is used to work around setting a placeholder for a
		 * tableView
		 */
        introLabel =
            new Label("Hier kunt u de gepersonaliseerde bestellijsten uitprinten voor de klanten.");
        exampleLabel = new Label("Print voorbeeld:");
        amountLabel = new Label("Aantal bestellijsten die geprint\nzullen worden: 10");
        listLabel = new Label("Selecteer welke bestellijst u wilt uitprinten:");
        
        // creating the buttons and setting their properties
        printButton = new Button("Printen");
        printButton.getStyleClass().add("form_buttons");
        
        listItems = new ComboBox();
        listArea = new TextArea();

        //Add all items to their corresponding containers
        vertBox1.getChildren().addAll(amountLabel, listLabel, listItems);
        vertBox2.getChildren().addAll(exampleLabel, listArea);
        buttonBox.getChildren().add(printButton);
        buttonBox.setAlignment(Pos.BASELINE_RIGHT);
        vertBox2.setAlignment(Pos.CENTER);
        introBox.getChildren().add(introLabel);

        contentPane.add(introBox, 0, 0);
        contentPane.add(vertBox1, 0, 1);
        contentPane.add(vertBox2, 1, 1);
        contentPane.add(buttonBox, 1, 2);

        getChildren().addAll(contentPane);

    }
}