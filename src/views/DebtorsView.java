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
 * <p> This view shows the data to the user and uses {@link DebtorsController} as his controller </p>
 * 
 * @author Alex
 */
public class DebtorsView extends AnchorPane implements ControlledScreen {
	private Button generateButton;
	private TableView<Order> tableView;
	private Button saveButton;
	
	/**
	 * constructor
	 */
	public DebtorsView() {
		createView();
		setUpContentPane();
	}

	@Override
	/**
	 * <p> Used for registering itself in the hashMap of the ScreensController
	 * to enable navigation</p>
	 * @param screensController used to set screensController as its controller
	 */
	public void setScreenController(ScreensController screensController) {
	}

	// adds the style class and sets the fixed height to the screen
	private void createView() {
		getStyleClass().add("background");
		setMinSize(1200, 800);

	}
	/**
	 * creating the gridpane, this is where all the displayed content goes
	 */
	private void setUpContentPane() {
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
	/**
	 * 
	 * @return the saveButton
	 */
	public Button getSaveButton() {
		return saveButton;
	}
	
	/**
	 * 
	 * @return the tableView
	 */
	public TableView<Order> getTableView() {
		return tableView;
	}
	/**
	 * 
	 * @return the generateButton
	 */
	public Button getGenerateButton() {
		return generateButton;
	}
}
