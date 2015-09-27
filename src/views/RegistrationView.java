package views;

import controllers.ScreensController;
import interfaces.ControlledScreen;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;

/**
 * Created by michael on 22-09-15.
 */
public class RegistrationView extends AnchorPane implements ControlledScreen {
    private ScreensController screensController;
    @FXML Label introLabel, surnameLabel, insertionLabel, firstnameLabel, streetnameLabel,
        streetnrLabel, zipcodeLabel, emailLabel, salutationLabel, referralLabel, phoneLabel,
        lionsMemberLabel;
    @FXML TextField surnameTextField, insertionTextField, firstnameTextField, streetnameTextField,
        streetnrTextField, zipcodeTextField, emailTextField, salutationTextField, referralTextField,
        phoneTextField, lionsMemberTextField;
    @FXML Button registrationButton;


    @Override public void setScreenController(ScreensController screensController) {
        this.screensController = screensController;
    }

    public RegistrationView() {
        createView();
    }

    private void createView() {
        getStyleClass().addAll("background");
        setMinSize(1200, 800);
        setupContentPane();
    }

    private void setupContentPane() {
        HBox introbox = new HBox();
        introbox.setLayoutY(200);
        introbox.setLayoutX(100);
        // Create GridPane
        GridPane contentPane = new GridPane();
        contentPane.setLayoutY(275);
        contentPane.setLayoutX(100);
        contentPane.setHgap(150);
        String introText =
            "Registreer jezelf voor het wijnproef evenement doormiddel van het invullen van alle onderstaande data.\nNadat je op de knop hebt gedrukt kun je jouw bestellijst ophalen bij de balie zodat je wijnen kunt gaan proeven.";
        this.introLabel = new Label(introText);
        this.introLabel.layoutBoundsProperty();
        introbox.getChildren().add(introLabel);

        this.surnameLabel = new Label("Achternaam :");
        this.surnameTextField = new TextField();
        this.insertionLabel = new Label("Tussenvoegsel :");
        this.insertionTextField = new TextField();
        this.firstnameLabel = new Label("Voornaam / initialen :");
        this.firstnameTextField = new TextField();
        this.streetnameLabel = new Label("Straat :");
        this.streetnameTextField = new TextField();
        this.streetnrLabel = new Label("Huisnummer :");
        this.streetnrTextField = new TextField();
        this.zipcodeLabel = new Label("Postcode :");
        this.zipcodeTextField = new TextField();
        this.emailLabel = new Label("E-mail :");
        this.emailTextField = new TextField();
        this.salutationLabel = new Label("Aanspreektitel :");
        this.salutationTextField = new TextField();
        this.referralLabel = new Label("Geattendeerd door :");
        this.referralTextField = new TextField();
        this.phoneLabel = new Label("Telefoon nummer :");
        this.phoneTextField = new TextField();
        this.lionsMemberLabel = new Label("Vul naam in van Lions lid :");
        this.lionsMemberTextField = new TextField();
        this.registrationButton = new Button("Registreer");
        this.registrationButton.getStyleClass().add("form_buttons");

        HBox row1 = new HBox(75);
        HBox row2 = new HBox(75);
        HBox row3 = new HBox();
        HBox row4 = new HBox();
        HBox row5 = new HBox();
        HBox row6 = new HBox();
        HBox row7 = new HBox();
        HBox row8 = new HBox();


        row1.getChildren().addAll(surnameLabel, insertionLabel, firstnameLabel);
        row2.getChildren().addAll(surnameTextField, insertionTextField, firstnameTextField);
        row3.getChildren().addAll(streetnameLabel,streetnrLabel,zipcodeLabel);
        row4.getChildren().addAll(streetnameTextField, streetnrTextField, zipcodeTextField);
        row5.getChildren().addAll(emailLabel,salutationLabel,referralLabel);
        row6.getChildren().addAll(emailTextField,salutationTextField,referralTextField);
        row7.getChildren().addAll(phoneLabel,lionsMemberLabel);
        row8.getChildren().addAll(phoneTextField,lionsMemberTextField,registrationButton);

        contentPane.add(row1, 0, 0);
        contentPane.add(row2, 0, 1);
        contentPane.add(row3, 0, 2);
        contentPane.add(row4, 0, 3);
        contentPane.add(row5, 0, 4);
        contentPane.add(row6, 0, 5);
        contentPane.add(row7, 0, 6);
        contentPane.add(row8, 0, 7);

        getChildren().addAll(introbox, contentPane);

    }
}
