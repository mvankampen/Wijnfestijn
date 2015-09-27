package views;

import controllers.ScreensController;
import interfaces.ControlledScreen;
import javafx.fxml.FXML;
import javafx.geometry.HPos;
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
        GridPane registrationPane = new GridPane();
        registrationPane.setLayoutY(275);
        registrationPane.setLayoutX(100);
        registrationPane.setHgap(75);
        //registrationPane.setVgap(25);
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

        registrationPane.setHalignment(surnameLabel, HPos.LEFT);
        registrationPane.add(surnameLabel, 0, 0);
        registrationPane.add(surnameTextField, 0, 1);
        registrationPane.setHalignment(insertionLabel, HPos.LEFT);
        registrationPane.add(insertionLabel, 1, 0);
        registrationPane.add(insertionTextField, 1, 1);
        registrationPane.setHalignment(firstnameLabel, HPos.LEFT);
        registrationPane.add(firstnameLabel, 2, 0);
        registrationPane.add(firstnameTextField, 2, 1);
        registrationPane.setHalignment(streetnameLabel, HPos.LEFT);
        registrationPane.add(streetnameLabel, 0, 3);
        registrationPane.add(streetnameTextField, 0, 4);
        registrationPane.setHalignment(streetnrLabel, HPos.LEFT);
        registrationPane.add(streetnrLabel, 1, 3);
        registrationPane.add(streetnrTextField, 1, 4);
        registrationPane.setHalignment(zipcodeLabel, HPos.LEFT);
        registrationPane.add(zipcodeLabel, 2, 3);
        registrationPane.add(zipcodeTextField, 2, 4);
        registrationPane.setHalignment(emailLabel, HPos.LEFT);
        registrationPane.add(emailLabel, 0, 5);
        registrationPane.add(emailTextField, 0, 6);
        registrationPane.setHalignment(salutationLabel, HPos.LEFT);
        registrationPane.add(salutationLabel, 1, 5);
        registrationPane.add(salutationTextField, 1, 6);
        registrationPane.setHalignment(referralLabel, HPos.LEFT);
        registrationPane.add(referralLabel, 2, 5);
        registrationPane.add(referralTextField, 2, 6);
        registrationPane.setHalignment(phoneLabel, HPos.LEFT);
        registrationPane.add(phoneLabel, 0, 7);
        registrationPane.add(phoneTextField, 0, 8);
        registrationPane.setHalignment(lionsMemberLabel, HPos.LEFT);
        registrationPane.add(lionsMemberLabel, 1, 7);
        registrationPane.add(lionsMemberTextField, 1, 8);
        registrationPane.add(registrationButton, 2, 8);


        getChildren().addAll(introbox, registrationPane);

    }
}
