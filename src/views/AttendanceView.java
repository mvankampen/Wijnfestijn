package views;

import controllers.ScreensController;
import interfaces.ControlledScreen;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import models.Guest;

//This screen is a AnchorPane and uses ControlledScreen as navigation manager


/**
 * <p>This screen is an {@link AnchorPane} and uses {@link ControlledScreen} as navigation manager.</p>
 *
 * @author Alex van der Wal
 * @version 0.1, november 2015
 */
public class AttendanceView extends AnchorPane implements ControlledScreen {
    private TableView<Guest> tableView;
    private Button updateBtn, resetBtn;

    /**
     * Used for registering itself in the hashmap of the {@link ScreensController} to enable navigation.
     *
     * @param screensController
     */
    public void setScreenController(ScreensController screensController) {

    }

    /**
     * <p>Calling the methods that initializedifferent aspects of the view.</p>
     */
    public AttendanceView() {
        createView();
        setUpContentPane();
    }

    /**
     * <p>Adds the style class and sets the fixed height to the screen.</p>
     */
    private void createView() {
        getStyleClass().add("background");
        setMinSize(1200, 800);
    }

    /**
     * <p>Creating the gridpane, this is where all the displayed content goes. It creats all Strings, used to make
     * the labels their content easier to adjust.</p>
     */
    public void setUpContentPane() {
        GridPane contentPane = new GridPane();
        contentPane.setLayoutX(100);
        contentPane.setLayoutY(200);
        contentPane.setVgap(10);

        String introText = "Hier kunt u de data van een geselecteerde klant aanpassen.";
        Label introLabel = new Label(introText);
        Label placeholder = new Label();
        placeholder.setText(
            "Je hebt nog geen klant geselecteerd om te wijzigen, Selecteer iemand & druk op enter");
        // Shows all guests so that the user can edit their presence setting
        tableView = new TableView<>();
        tableView.setPlaceholder(placeholder);
        tableView.setMaxHeight(350);
        tableView.setMinWidth(1000);
        tableView.setMaxWidth(1000);

        // creating the buttons and setting their properties
        updateBtn = new Button("Update presentie");
        updateBtn.getStyleClass().add("form_buttons");
        resetBtn = new Button("Reset presentie");
        resetBtn.getStyleClass().add("form_buttons");
        // creating a Horizontal box, vbox didnt do the job here
        HBox hBox = new HBox();
        hBox.setSpacing(10);
        hBox.getChildren().addAll(updateBtn, resetBtn);
        // filling the contentpane with all displayable elements
        contentPane.add(introLabel, 0, 1);
        contentPane.add(tableView, 0, 2);
        contentPane.add(hBox, 0, 3);

        // Add the contentPane to the View
        getChildren().add(contentPane);
    }

    public TableView<Guest> getTableView() {
        return tableView;
    }

    public Button getUpdateBtn() {
        return updateBtn;
    }

    public Button getResetBtn() {
        return resetBtn;
    }
}
