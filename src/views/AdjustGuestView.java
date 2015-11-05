package views;

import controllers.ScreensController;
import interfaces.ControlledScreen;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;
import models.Guest;

/**
 * <P>AdjustGuestView generates an output presentation to the user based on changes in the model.</P>
 *
 * @author Alex van der Wal
 * @version 0.1, november 2015
 */

public class AdjustGuestView extends AnchorPane implements ControlledScreen {
    private Button updateButton;
    private TableView<Guest> editableGuest = new TableView<Guest>();
    public TextField surnameTextField;

    public void setScreenController(ScreensController screensController) {
    /*
     * Used for registering itself in the hashMap of the ScreensController
		 * to enable navigation
		 */
    }

    /**
     * <P>Default Constructor</P>
     */
    public AdjustGuestView() {
        // calling the methods that initialize different aspects of the view
        createView();
        setUpContentPane();
    }

    /**
     * <P>Setting up the display. Adds the style class and sets the fixed height to the screen</P>
     */
    private void createView() {
        getStyleClass().add("background");
        setMinSize(1200, 800);

    }

    /**
     * <P>Setting up the Content Pane</P>
     */
    public void setUpContentPane() {
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
        String introText = "Hier kunt u de data van een geselecteerde klant aanpassen.";
        String customerText = "Vul hier de achternaam van de klant in:";

		/*
		 * creating all Labels, used to instruct the user towards what actions
		 * he can perform within the view
		 */
        Label introLabel = new Label(introText);
		/*
		 * this label is used to work around setting a placeholder for a
		 * tableView
		 */
        Label placeholder = new Label();
        placeholder.setText("Je hebt nog geen klant geselecteerd om te wijzigen, "
            + "Selecteer iemand & druk op enter");
        Label customerLabel = new Label(customerText);

		/* Creating all vboxes that are used to organize the sectors used in the
		* contentPane
		*/
        VBox customerBox = new VBox(15);

		/* TableView we use to show the selected Guest that the user wants to
		* modify
		*/
        editableGuest.setMaxHeight(120);
        editableGuest.setMinWidth(1000);
        editableGuest.setMaxWidth(1000);
        editableGuest.setPlaceholder(placeholder);

        // creating the textfields and setting their properties
        surnameTextField = new TextField();
        surnameTextField.setMaxWidth(200);

        // creating the buttons and setting their properties
        updateButton = new Button("Klant updaten");
        updateButton.getStyleClass().add("form_buttons");

        // Filling the VBoxes with the content they are responsible for
        customerBox.getChildren().addAll(customerLabel, surnameTextField);

        // Lastly, filling the contentpane with all displayable elements
        contentPane.add(introLabel, 0, 1);
        contentPane.add(customerBox, 0, 2);
        contentPane.add(editableGuest, 0, 3);
        contentPane.add(updateButton, 0, 4);

        // Add the contentPane to the view
        getChildren().add(contentPane);
    }

    /**
     * @return TextField object Surname TextField
     */
    public TextField getSurnameTextField() {
        return surnameTextField;
    }

    /**
     * @return TableView object EditableGuest
     */
    public TableView<Guest> getEditableGuest() {
        return editableGuest;
    }

    /**
     * @return Button object Update Button
     */
    public Button getUpdateButton() {
        return updateButton;
    }

    /**
     * @param editableGuest is a new TableView Object
     */
    public void setEditableGuest(TableView<Guest> editableGuest) {
        this.editableGuest = editableGuest;
    }
}
