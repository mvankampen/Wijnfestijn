package views;

import controllers.ScreensController;
import interfaces.ControlledScreen;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleGroup;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;

public class OrderListView extends AnchorPane implements ControlledScreen {

    private ScreensController screensController;

    public void setScreenController(ScreensController screensController) {
        this.screensController = screensController;
    }

    public OrderListView() {
    	createView();
	}
	
	private void createView() {
		getStyleClass().add("background");
		setMinSize(1200,800);
		setUpContentPane();
	}
	
	public void setUpContentPane(){
		VBox vertBox1 = new VBox();
		VBox vertBox2 = new VBox();
		
		Button printButton = new Button("Printen");
		
		//Label texts
		String introText = "Hier kunt u de gepersonaliseerde bestellijsten uit printen voor de klanten";
		String amountText = "Aantal bestellijsten die geprint\nzullen worden: 10";
		String listText = "Selecteer welke bestellijst u wilt uit printen";
		String exampleText = "Print voorbeeld:";
		
		//Create items vertBox 1
		Label introLabel = new Label(introText);
		Label amountLabel = new Label(amountText);
		Label listLabel = new Label(listText);
		ComboBox listItems = new ComboBox();
		
		//Create items vertBox 2
		Label exampleLabel = new Label(exampleText);
		TextArea listArea = new TextArea();
		
		//Add items to vertBoxes
		vertBox1.getChildren().addAll(introLabel, amountLabel, listLabel, listItems);
		vertBox2.getChildren().addAll(exampleLabel, listArea);
		
		getChildren().addAll(vertBox1, vertBox2, printButton);
	}
}
