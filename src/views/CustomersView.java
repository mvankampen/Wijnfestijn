package views;

import controllers.ScreensController;
import interfaces.ControlledScreen;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.EventHandler;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableColumn.CellEditEvent;
import javafx.scene.control.TableView;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleGroup;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.control.cell.TextFieldTableCell;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;
import javafx.util.Callback;
import models.Guest;

public class CustomersView extends AnchorPane implements ControlledScreen {

    private Button updateButton;
	private ScreensController screensController;
    private TableView<Guest> editableGuest = new TableView<Guest>();
    public TextField surnameTextField;
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
		String introText = "Hier kunt u de data van een geselecteerde klant aanpassen.";
		Label introLabel = new Label(introText);
		
		//Create VBoxes row 1
		VBox customerBox = new VBox(15);
		VBox selectionBox = new VBox(15);
		VBox updateBox = new VBox(10);
		editableGuest.setMaxHeight(50);
		editableGuest.setMinWidth(1000);
		Label placeholder = new Label();
		placeholder.setText("Je hebt nog geen klant geselecteerd om te wijzigen, Selecteer iemand & druk op enter");
		editableGuest.setPlaceholder(placeholder);
		//Create VBoxes row 2
		
		//Label texts row 1
		String customerText = "Vul hier de achternaam van de klant in:";
		String selectionText = "Selecteer welke klant u wilt wijzigen:";
		
		//Label texts row 2
		String adjustText = "Testing!";
		//Creating items row 1
		Label customerLabel = new Label(customerText);
		Label selectionLabel = new Label(selectionText);
		this.surnameTextField = new TextField();
		surnameTextField.setMaxWidth(200);
		
		
		//Creating items row 2
		Label adjustLabel = new Label(adjustText);
		updateButton = new Button("Klant updaten");
		updateButton.getStyleClass().add("form_buttons");
		//Adding to VBoxes
		customerBox.getChildren().addAll(customerLabel, surnameTextField);
		selectionBox.getChildren().addAll(selectionLabel);
		
		//Adding items row 1
		contentPane.add(introLabel, 0, 1);
		contentPane.add(customerBox, 0, 2);
		contentPane.add(editableGuest, 0, 3);
		contentPane.add(updateButton, 0, 4);
		
		//Add the contentPane
		getChildren().add(contentPane);
	}
	
	public TextField getSurnameTextField() {
		return surnameTextField;
	}

	public TableView<Guest> getEditableGuest() {
		return editableGuest;
	}
	public Button getUpdateButton() {
		return updateButton;
	}

	public void setEditableGuest(TableView<Guest> editableGuest) {
		this.editableGuest = editableGuest;
	}
}
