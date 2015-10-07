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
public class OrderListView extends AnchorPane implements ControlledScreen {
    private ScreensController screensController;
    @FXML Label introLabel, surnameLabel, selectCustomerLabel, orderLabel, wineLabel, amountLabel;
    @FXML TextField surnameTextField, wineTextField1, wineTextField2, wineTextField3,
        amountTextField1, amountTextField2, amountTextField3;
    @FXML ListView customerListview;
    @FXML ComboBox orderlistComboBox;
    @FXML CheckBox standardCheckbox;
    @FXML Button makeOrderBtn, orderContinueBtn, extraInputBtn;

    @Override public void setScreenController(ScreensController screensController) {
        this.screensController = screensController;
    }

    public OrderListView() {
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
            "Hier kunt u de order opstellen van de klant. Vul de gegevens in en klik op de \ngenereer OF genereer & volgende button. Genereer en volgende zorgt er\nvoor dat u terug komt op dit scherm.");
        introBox.getChildren().add(this.introLabel);

        VBox vBoxCustomer = new VBox(10);
        surnameLabel = new Label("Klant achternaam:");
        surnameTextField = new TextField();
        vBoxCustomer.getChildren().addAll(surnameLabel, surnameTextField);

        VBox vBoxListview = new VBox(10);
        this.selectCustomerLabel = new Label("Selecteer klant (indien nodig):");
        this.customerListview = new ListView();
        this.customerListview.setMaxHeight(100);
        vBoxListview.getChildren().addAll(this.selectCustomerLabel, customerListview);

        VBox vBoxOrderList = new VBox(10);
        this.orderLabel = new Label("Welk bestellijst?");
        this.orderlistComboBox = new ComboBox();
        this.standardCheckbox = new CheckBox("Stel in als standaard");
        vBoxOrderList.getChildren().addAll(orderLabel, orderlistComboBox, standardCheckbox);

        HBox vBoxButton = new HBox(30);
        this.makeOrderBtn = new Button("Maak order");
        this.makeOrderBtn.getStyleClass().add("form_buttons");
        this.orderContinueBtn = new Button("Order & doorgaan");
        this.orderContinueBtn.getStyleClass().add("form_buttons");
        this.extraInputBtn = new Button("Extra invulvelden");
        this.extraInputBtn.getStyleClass().add("form_buttons");
        vBoxButton.getChildren()
            .addAll(this.makeOrderBtn, this.orderContinueBtn, this.extraInputBtn);

        VBox vBoxOrder = new VBox();
        vBoxOrder.setPadding(new Insets(0, 0, 0, 20));
        this.wineLabel = new Label("Wijn nummer:");
        this.amountLabel = new Label("Aantal dozen:");
        this.wineTextField1 = new TextField();
        this.amountTextField1 = new TextField();
        GridPane orderGridPane = new GridPane();
        orderGridPane.setHgap(20);
        orderGridPane.setVgap(10);
        ColumnConstraints col1 = new ColumnConstraints();
        col1.setPercentWidth(50);
        ColumnConstraints col2 = new ColumnConstraints();
        col2.setPercentWidth(50);
        orderGridPane.getColumnConstraints().addAll(col1, col2);


        //orderGridPane.setHgap(30);
        orderGridPane.add(this.wineLabel, 0, 0);
        orderGridPane.add(this.wineTextField1, 0, 1);
        orderGridPane.add(this.amountLabel, 1, 0);
        orderGridPane.add(this.amountTextField1, 1, 1);
        vBoxOrder.getChildren().add(orderGridPane);


        contentPane.add(introBox, 0, 0);
        contentPane.add(vBoxCustomer, 0, 1);
        contentPane.add(vBoxListview, 0, 2);
        contentPane.add(vBoxOrderList, 0, 3);
        contentPane.add(vBoxButton, 0, 4);
        contentPane.add(vBoxOrder, 1, 1);

        getChildren().addAll(contentPane);
    }
}