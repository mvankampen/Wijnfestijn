package views;

import controllers.ScreensController;
import interfaces.ControlledScreen;
import javafx.fxml.FXML;
import javafx.geometry.Insets;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.*;
import models.OrderLine;
import models.Wine;


/**
 * Created by Sander de Jong on 21-9-2015.
 */
public class OrderListView extends AnchorPane implements ControlledScreen {
    private ScreensController screensController;
    @FXML Label introLabel, surnameLabel, selectCustomerLabel, orderLabel, wineLabel, amountLabel;
    @FXML TextField surnameTextField;
    @FXML Button makeOrderBtn, extraInputBtn;
    @FXML TableView<OrderLine> tableView;

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
        vBoxCustomer.getChildren()
            .addAll(this.surnameLabel, this.surnameTextField);


        HBox vBoxButton = new HBox(30);
        this.makeOrderBtn = new Button("Maak order");
        this.makeOrderBtn.getStyleClass().add("form_buttons");
        this.extraInputBtn = new Button("Extra invulvelden");
        this.extraInputBtn.getStyleClass().add("form_buttons");
        vBoxButton.getChildren()
            .addAll(this.makeOrderBtn, this.extraInputBtn);

        HBox vBoxOrder = new HBox();
        vBoxOrder.setPadding(new Insets(0, 0, 0, 20));
        this.tableView = new TableView<>();
        this.tableView.setEditable(true);
        this.tableView.setMaxWidth(400);
        this.tableView.setMaxHeight(400);
        TableColumn<OrderLine, Wine> wineColumn = new TableColumn<>("Wijn nummer");
        TableColumn<OrderLine,Integer> amountColumn = new TableColumn<>("Aantal");
        this.tableView.getColumns().addAll(wineColumn, amountColumn);
        vBoxOrder.getChildren().add(this.tableView);


        contentPane.add(introBox, 0, 0);
        contentPane.add(vBoxCustomer, 0, 1);
        contentPane.add(vBoxButton, 0, 2);
        contentPane.add(vBoxOrder, 1, 1);

        getChildren().addAll(contentPane);
    }

    public Button getExtraInputBtn() {
        return extraInputBtn;
    }

    public Button getMakeOrderBtn() {
        return makeOrderBtn;
    }

    public TextField getSurnameTextField() {
        return surnameTextField;
    }
}
