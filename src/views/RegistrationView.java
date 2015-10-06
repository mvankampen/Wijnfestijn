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
import javafx.scene.layout.VBox;

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
        contentPane.setHgap(50);
        contentPane.setVgap(25);
        String introText =
            "Registreer jezelf voor het wijnproef evenement doormiddel van het invullen van alle onderstaande data.\nNadat je op de knop hebt gedrukt kun je jouw bestellijst ophalen bij de balie zodat je wijnen kunt gaan proeven.";
        this.introLabel = new Label(introText);
        this.introLabel.layoutBoundsProperty();
        introbox.getChildren().add(introLabel);

        this.surnameLabel = new Label("Achternaam :");
        this.surnameTextField = new TextField();
        this.insertionLabel = new Label("Tussenvoegsel :");
        this.insertionTextField = new TextField();
        this.firstnameLabel = new Label("Voornaam :");
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


        VBox group1 = new VBox();
        group1.getChildren().addAll(surnameLabel, surnameTextField);
        VBox group2 = new VBox(streetnameLabel, streetnameTextField);
        VBox group3 = new VBox(emailLabel, emailTextField);
        VBox group4 = new VBox(phoneLabel, phoneTextField);
        VBox group5 = new VBox(insertionLabel, insertionTextField);
        VBox group6 = new VBox(streetnrLabel, streetnrTextField);
        VBox group7 = new VBox(salutationLabel, salutationTextField);
        VBox group8 = new VBox(lionsMemberLabel, lionsMemberTextField);
        VBox group9 = new VBox(firstnameLabel, firstnameTextField);
        VBox group10 = new VBox(zipcodeLabel, zipcodeTextField);
        VBox group11 = new VBox(referralLabel, referralTextField);
        VBox group12 = new VBox(registrationButton);

        contentPane.add(group1, 0, 0);
        contentPane.add(group2, 0, 1);
        contentPane.add(group3, 0, 2);
        contentPane.add(group4, 0, 3);
        contentPane.add(group5, 1, 0);
        contentPane.add(group6, 1, 1);
        contentPane.add(group7, 1, 2);
        contentPane.add(group8, 1, 3);
        contentPane.add(group9, 2, 0);
        contentPane.add(group10, 2, 1);
        contentPane.add(group11, 2, 2);
        contentPane.add(group12, 2, 3);

        //contentPane.add(group4, 3, 4);

        getChildren().addAll(introbox, contentPane);

    }
}
