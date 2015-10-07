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

public class OrderListPrintView extends AnchorPane implements ControlledScreen {

    @FXML Label introLabel, exampleLabel, listLabel, amountLabel;
    @FXML Button printButton;
    @FXML ComboBox listItems;
    @FXML TextArea listArea;

    private ScreensController screensController;

    public void setScreenController(ScreensController screensController) {
        this.screensController = screensController;
    }

    public OrderListPrintView() {
        createView();
    }

    private void createView() {
        getStyleClass().add("background");
        setMinSize(1200, 800);
        setUpContentPane();
    }

    public void setUpContentPane() {
        GridPane contentPane = new GridPane();
        contentPane.setLayoutX(100);
        contentPane.setLayoutY(200);

        HBox introBox = new HBox();

        this.introLabel =
            new Label("Hier kunt u de gepersonaliseerde bestellijsten uit printen voor de klanten");
        introBox.getChildren().add(introLabel);

        VBox vertBox1 = new VBox();
        vertBox1.setSpacing(20);
        VBox vertBox2 = new VBox();
        vertBox2.setPadding(new Insets(0, 0, 0, 40));
        VBox buttonBox = new VBox();


        this.exampleLabel = new Label("Print voorbeeld:");
        this.amountLabel = new Label("Aantal bestellijsten die geprint\nzullen worden: 10");
        this.listLabel = new Label("Selecteer welke bestellijst u wilt uit printen");
        this.listItems = new ComboBox();
        this.printButton = new Button("Printen");
        this.printButton.getStyleClass().add("form_buttons");

        this.listArea = new TextArea();

        //Add items to vertBoxes
        vertBox1.getChildren().addAll(amountLabel, listLabel, listItems);
        vertBox2.getChildren().addAll(exampleLabel, listArea);
        buttonBox.getChildren().add(printButton);
        buttonBox.setAlignment(Pos.BASELINE_RIGHT);
        vertBox2.setAlignment(Pos.CENTER);


        contentPane.add(introBox, 0, 0);
        contentPane.add(vertBox1, 0, 1);
        contentPane.add(vertBox2, 1, 1);
        contentPane.add(buttonBox, 1, 2);

        getChildren().addAll(contentPane);

    }
}