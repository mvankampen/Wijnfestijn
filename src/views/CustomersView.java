package views;

import controllers.ScreensController;
import interfaces.ControlledScreen;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleGroup;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;

public class CustomersView extends AnchorPane implements ControlledScreen {

    private ScreensController screensController;

    public void setScreenController(ScreensController screensController) {
        this.screensController = screensController;
    }

    public CustomersView() {
    	createView();
	}
	
	private void createView() {
		getStyleClass().add("background");
		setMinSize(1200,800);
		setUpContentPane();
	}
	
	public void setUpContentPane(){
		//contentPane details
		GridPane contentPane = new GridPane();
		contentPane.setLayoutY(175);
		contentPane.setLayoutX(100);
		contentPane.setVgap(25);
		contentPane.setHgap(150);
		
		//Intro label
		String introText = "Hier kunt u de data van een geselecteerde klant aanpassen";
		Label introLabel = new Label(introText);
		
		//Create VBoxes row 1
		VBox customerBox = new VBox(15);
		VBox selectionBox = new VBox(15);
		VBox updateBox = new VBox(10);
		
		//Create VBoxes row 2
		
		//Label texts row 1
		String customerText = "Vul hier de achternaam van de klant in :";
		String selectionText = "Selecteer welke klant u wilt wijzigen";
		
		//Label texts row 2
		String adjustText = "Selecteer data om te wijzigen & wijzig dit:";
		//Creating items row 1
		Label customerLabel = new Label(customerText);
		Label selectionLabel = new Label(selectionText);
		TextField customerFilterTextArea = new TextField();
		
		//Creating items row 2
		Label adjustLabel = new Label(adjustText);
		Button updateButton = new Button("Klant updaten");
		updateButton.getStyleClass().add("form_buttons");
		
		//Adding to VBoxes
		customerBox.getChildren().addAll(customerLabel, customerFilterTextArea);
		selectionBox.getChildren().addAll(selectionLabel);
		
		//Adding items row 1
		contentPane.add(introLabel, 0, 1);
		contentPane.add(customerBox, 0, 2);
		contentPane.add(selectionBox, 0, 3);
		contentPane.add(adjustLabel, 1, 1);
		contentPane.add(updateBox, 1, 2);
		contentPane.add(updateButton, 1, 4);
		
		//Add the contentPane
		getChildren().add(contentPane);
	}
}
