package views;

import controllers.ScreensController;
import interfaces.ControlledScreen;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;

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
        this.introLabel = new Label(
            "Registreer jezelf voor het wijnproef evenement doormiddel van het invullen van alle onderstaande data. Nadat je op de knop hebt gedrukt kun je jouw bestellijst ophalen bij de balie zodat je wijnen kunt gaan proeven.");
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
        this.emailLabel = new Label();
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
        this.registrationButton.setOnAction(e -> {
            System.out.println("Test fase");
        });

        getChildren().addAll(this.introLabel, this.surnameLabel, this.surnameTextField, this.insertionLabel,
                this.insertionTextField, this.firstnameLabel, this.firstnameTextField,
                this.streetnameLabel, this.streetnameTextField, this.streetnrLabel,
                this.streetnrTextField, this.zipcodeLabel, this.zipcodeTextField, this.emailLabel,
                this.emailTextField, this.salutationLabel, this.salutationTextField,
                this.referralLabel, this.referralTextField, this.phoneLabel, this.phoneTextField,
                this.lionsMemberLabel, this.lionsMemberTextField, this.registrationButton);

    }
}
