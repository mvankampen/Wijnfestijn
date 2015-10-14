package views;

import controllers.ScreensController;
import interfaces.ControlledScreen;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableView;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import models.Order;


/**
 * Created by Sander de Jong on 27-9-2015.
 */
public class DebtorsView extends AnchorPane implements ControlledScreen {
    private ScreensController screensController;
    private  Button generateButton;
    private TableView<Order> tableView;
    private Button saveButton;
    public DebtorsView() {
        createView();
    }

    @Override
    public void setScreenController(ScreensController screensController) {
        this.screensController = screensController;
    }

    private void createView() {
        getStyleClass().add("background");
        setMinSize(1200, 800);
        setUpContentPane();
    }

    private void setUpContentPane() {
        //contentPane details
        GridPane contentPane = new GridPane();
        contentPane.setLayoutY(175);
        contentPane.setLayoutX(100);
        contentPane.setVgap(25);
        contentPane.setHgap(150);

        //Intro label
        String introText = "Hier worden de klanten die nog een order open hebben staan in 1 lijst gezet.";
        Label introLabel = new Label(introText);

        //Create VBoxes row 1
        VBox debtBox = new VBox(15);
        HBox buttonBox = new HBox(15);

        //Label texts row 1
        String debText = "Aantal debiteuren: 10";
        String debListText = "Debiteurenlijst:";

        //Creating items row 1
        Label lbldebText = new Label(debText);
        Label lbldebtListText = new Label(debListText);
	    tableView = new TableView<Order>();
	    tableView.setEditable(true);
	    
        generateButton = new Button("Genereren");
        saveButton = new Button("Opslaan");
        generateButton.getStyleClass().add("form_buttons");
        saveButton.getStyleClass().add("form_buttons");

        //Adding to VBoxes
        debtBox.getChildren().addAll(lbldebText, lbldebtListText, tableView);
        buttonBox.getChildren().addAll(generateButton, saveButton);

        //Adding items row 1
        contentPane.add(introLabel, 0, 1);
        contentPane.add(debtBox, 0, 2);
        contentPane.add(buttonBox,0, 3);

        getChildren().add(contentPane);
    }
    public Button getSaveButton(){
    	return saveButton;
    }
    public TableView<Order> getTableView(){
    	return tableView;
    }
    public Button getGenerateButton(){
    	return generateButton;
    }
}
