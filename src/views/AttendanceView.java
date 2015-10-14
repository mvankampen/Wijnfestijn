package views;

import controllers.ScreensController;
import interfaces.ControlledScreen;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import models.Guest;

public class AttendanceView extends AnchorPane implements ControlledScreen {

    @FXML
    private TableView<Guest> tableView;
    @FXML
    private Button updateBtn;
    @FXML
    private Button resetBtn;
    private ScreensController screensController;


    public AttendanceView() {
        createView();
    }

    @Override public void setScreenController(ScreensController screensController) {
        this.screensController = screensController;
    }

    private void createView() {
        getStyleClass().add("background");
        setMinSize(1200,800);
        setUpContentPane();
    }

    public void setUpContentPane(){
        //contentPane details
        GridPane contentPane = new GridPane();
        contentPane.setLayoutX(100);
        contentPane.setLayoutY(200);
        contentPane.setVgap(10);

        //Intro label
        String introText = "Hier kunt u de data van een geselecteerde klant aanpassen.";
        Label introLabel = new Label(introText);

        tableView = new TableView<>();
        Label placeholder = new Label();
        placeholder.setText("Je hebt nog geen klant geselecteerd om te wijzigen, Selecteer iemand & druk op enter");
        tableView.setPlaceholder(placeholder);
        tableView.setMaxHeight(350);
        tableView.setMinWidth(1000);

        HBox hBox = new HBox();
        updateBtn = new Button("Update presentie");
        updateBtn.getStyleClass().add("form_buttons");
        resetBtn = new Button("Reset presentie");
        resetBtn.getStyleClass().add("form_buttons");
        hBox.setSpacing(10);
        hBox.getChildren().addAll(updateBtn,resetBtn);

        contentPane.add(introLabel, 0, 1);
        contentPane.add(tableView, 0, 2);
        contentPane.add(hBox, 0, 3);

        //Add the contentPane
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
