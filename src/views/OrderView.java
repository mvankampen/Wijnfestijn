package views;

import controllers.ScreensController;
import interfaces.ControlledScreen;
import javafx.fxml.FXML;
import javafx.geometry.Insets;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import models.OrderLine;

/**
 * @author Michael van Kampen
 * @version 0.1, november 2015
 *          Description:
 *          Order view generates an output presentation to the user based on changes in the model.
 */
public class OrderView extends AnchorPane implements ControlledScreen {
    private ScreensController screensController;
    @FXML Label introLabel, surnameLabel, wineLabel, amountLabel;
    @FXML TextField surnameTextField, winenumberTextField, amountTextField;
    @FXML Button makeOrderBtn, orderBtn;
    @FXML TableView<OrderLine> tableView;
    @FXML private Button removeBtn;

    @Override public void setScreenController(ScreensController screensController) {
        this.screensController = screensController;
    }

    /**
     * <P>Default Constructor</P>
     */
    public OrderView() {
        createView();
    }

    /**
     * <P>Setting up the display</P>
     */
    private void createView() {
        getStyleClass().add("background");
        setMinSize(1200, 800);
        setUpContentPane();
    }

    /**
     * <P>Setting up the Content Pane</P>
     */
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
        vBoxCustomer.setPadding(new Insets(0, 20, 0, 0));
        surnameLabel = new Label("Klant achternaam:");
        surnameTextField = new TextField();
        vBoxCustomer.getChildren().addAll(this.surnameLabel, this.surnameTextField);


        HBox vBoxButton = new HBox(30);
        this.makeOrderBtn = new Button("Maak order");
        this.makeOrderBtn.getStyleClass().add("form_buttons");
        vBoxButton.getChildren().addAll(this.makeOrderBtn);


        this.tableView = new TableView<>();
        this.tableView.setEditable(true);
        this.tableView.setMaxHeight(50);
        this.tableView.setMinWidth(300);
        Label placeholder = new Label();
        placeholder.setText("Geen bestellingen geplaatst");
        this.tableView.setPlaceholder(placeholder);

        HBox vboxOrder = new HBox(10);
        this.wineLabel = new Label("Wijn nummer");
        this.winenumberTextField = new TextField();
        this.amountLabel = new Label("Aantal");
        this.amountTextField = new TextField();
        vboxOrder.getChildren()
            .addAll(wineLabel, winenumberTextField, amountLabel, amountTextField);
        HBox buttonBox = new HBox(10);
        this.orderBtn = new Button("Orderregel aanmaken");
        this.removeBtn = new Button("Verwijder selectie");
        buttonBox.getChildren().addAll(this.orderBtn);
        buttonBox.getChildren().addAll(this.removeBtn);
        this.removeBtn.getStyleClass().add("form_buttons");
        this.orderBtn.getStyleClass().add("form_buttons");

        contentPane.add(introBox, 0, 0);
        contentPane.add(vBoxCustomer, 0, 1);
        contentPane.add(vBoxButton, 0, 2);
        contentPane.add(tableView, 1, 1);
        contentPane.add(vboxOrder, 1, 2);
        contentPane.add(buttonBox, 1, 3);
        getChildren().addAll(contentPane);
    }

    /**
     * @return Button object Remove button
     */
    public Button getRemoveBtn() {
        return removeBtn;
    }

    /**
     * @return Button object Order button
     */
    public Button getOrderBtn() {
        return orderBtn;
    }

    /**
     * @return Button object Make order button
     */
    public Button getMakeOrderBtn() {
        return makeOrderBtn;
    }

    /**
     * @return TextField object SurnameTextField
     */
    public TextField getSurnameTextField() {
        return surnameTextField;
    }

    /**
     * @return TextField object Winenumber TextField
     */
    public TextField getWinenumberTextField() {
        return winenumberTextField;
    }

    /**
     * @return TextField object Amount TextField
     */
    public TextField getAmountTextField() {
        return amountTextField;
    }

    /**
     * @return Integer Wine number
     */
    public int getWinenumberInt() {
        return Integer.parseInt(winenumberTextField.getText());
    }

    /**
     * @return TableView object TableView
     */
    public TableView<OrderLine> getTableView() {
        return tableView;
    }

    /**
     * @return Integer amount
     */
    public int getAmountInt() {
        return Integer.parseInt(amountTextField.getText());
    }
}
