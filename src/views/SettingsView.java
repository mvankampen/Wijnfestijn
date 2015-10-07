package views;

import controllers.ScreensController;
import interfaces.ControlledScreen;
import javafx.fxml.FXML;
import javafx.geometry.Insets;
import javafx.scene.control.*;
import javafx.scene.layout.*;


/**
 * Created by Sander de Jong on 21-9-2015.
 */
public class SettingsView extends AnchorPane implements ControlledScreen {
    private ScreensController screensController;
    @FXML Label introLabel, templateLabel, selectCustomerLabel, orderLabel, wineLabel, amountLabel;
    @FXML TextField surnameTextField, wineTextField1, wineTextField2, wineTextField3,
        amountTextField1, amountTextField2, amountTextField3;
    @FXML ListView customerListview;
    @FXML ComboBox orderlistComboBox;
    @FXML CheckBox standardCheckbox;
    @FXML Button makeOrderBtn, orderContinueBtn, extraInputBtn;
	private Label exampleLabel;
	private TextField exampleField;

    @Override public void setScreenController(ScreensController screensController) {
        this.screensController = screensController;
    }

    public SettingsView() {
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
        contentPane.setVgap(20);

        HBox introBox = new HBox();
        this.introLabel = new Label(
            "Hier kunt u de mailtemplates & login gegevens van de afzender mail invullen");
        introBox.getChildren().add(this.introLabel);

        VBox vBoxCustomer = new VBox();
        templateLabel = new Label("Selecteer de desbetreffende template:");
        surnameTextField = new TextField();
        vBoxCustomer.getChildren().addAll(templateLabel, surnameTextField);

        VBox vBoxOrderList = new VBox(10);
        this.orderLabel = new Label("Voor welke mail is deze Template?");
        this.orderlistComboBox = new ComboBox();
        vBoxOrderList.getChildren().addAll(orderLabel, orderlistComboBox);

        HBox vBoxButton = new HBox(30);
        this.makeOrderBtn = new Button("Voeg toe");
        this.makeOrderBtn.getStyleClass().add("form_buttons");
        vBoxButton.getChildren()
            .addAll(this.makeOrderBtn);

        VBox vBoxOrder = new VBox();
        vBoxOrder.setPadding(new Insets(0, 0, 0, 20));
        vBoxOrder.setSpacing(20);
        this.exampleLabel = new Label("example:");
        this.exampleField = new TextField();
        GridPane orderGridPane = new GridPane();
        orderGridPane.setHgap(0);
        orderGridPane.setVgap(0);
        ColumnConstraints col1 = new ColumnConstraints();
        col1.setPercentWidth(50);
        ColumnConstraints col2 = new ColumnConstraints();
        col2.setPercentWidth(50);
        orderGridPane.getColumnConstraints().addAll(col1, col2);


        //orderGridPane.setHgap(30);
        orderGridPane.add(this.exampleLabel, 0, 0);
        orderGridPane.add(this.exampleField, 0, 1);
        vBoxOrder.getChildren().add(orderGridPane);


        contentPane.add(introBox, 0, 0);
        contentPane.add(vBoxCustomer, 0, 2);
        contentPane.add(vBoxOrderList, 0, 3);
        contentPane.add(vBoxButton, 0, 4);
        contentPane.add(vBoxOrder, 1, 2);

        getChildren().addAll(contentPane);
    }
}