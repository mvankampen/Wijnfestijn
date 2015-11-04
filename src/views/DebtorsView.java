package views;

import controllers.ScreensController;
import interfaces.ControlledScreen;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import models.Order;

/**
 * Created by Sander de Jong on 27-9-2015.
 */
// This screen is a AnchorPane and uses ControlledScreen as navigation manager
public class DebtorsView extends AnchorPane implements ControlledScreen {
	private Button generateButton;
	private TableView<Order> tableView;
	private Button saveButton;

	public DebtorsView() {
		// calling the methods that initialize different aspects of the view
		createView();
		setUpContentPane();
	}

	@Override
	public void setScreenController(ScreensController screensController) {
		/*
		 * Used for registering itself in the hashMap of the ScreensController
		 * to enable navigation
		 */
	}

	// adds the style class and sets the fixed height to the screen
	private void createView() {
		getStyleClass().add("background");
		setMinSize(1200, 800);

	}

	private void setUpContentPane() {
		// creating the gridpane, this is where all the displayed content goes
		GridPane contentPane = new GridPane();
		contentPane.setLayoutY(175);
		contentPane.setLayoutX(100);
		contentPane.setVgap(25);
		contentPane.setHgap(150);
		/*
		 * creating all Strings, used to make the labels their content easier to
		 * adjust
		 */
		String introText = "Hier worden de klanten die nog een order open hebben staan in 1 lijst gezet.";
		String debListText = "Debiteurenlijst:";

		/*
		 * creating all Labels, used to instruct the user towards what actions
		 * he can perform within the view
		 */
		Label introLabel = new Label(introText);
		Label lbldebtListText = new Label(debListText);

		/* Creating all vboxes and hboxes that are used to organize the sectors
		* used in the contentPane
		*/
		VBox debtBox = new VBox(15);
		HBox buttonBox = new HBox(15);
		
		// tableview used to show all the debtors
		tableView = new TableView<Order>();
		tableView.setEditable(true);
		generateButton = new Button("Genereren");
		saveButton = new Button("Opslaan");
		generateButton.getStyleClass().add("form_buttons");
		saveButton.getStyleClass().add("form_buttons");

		// Adding to the v/hboxes
		debtBox.getChildren().addAll(lbldebtListText, tableView);
		buttonBox.getChildren().addAll(generateButton, saveButton);

		// Adding all items to the contentpane
		contentPane.add(introLabel, 0, 1);
		contentPane.add(debtBox, 0, 2);
		contentPane.add(buttonBox, 0, 3);
		
		// Add the contentPane to the view
		getChildren().add(contentPane);
	}
	//getters
	public Button getSaveButton() {
		return saveButton;
	}

	public TableView<Order> getTableView() {
		return tableView;
	}

	public Button getGenerateButton() {
		return generateButton;
	}
}
