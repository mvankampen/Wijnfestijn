package views;


import controllers.ScreensController;
import interfaces.ControlledScreen;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.*;

/**
 * Created by michael on 22-09-15.
 */
public class RegistrationView extends AnchorPane implements ControlledScreen {
    private ScreensController screensController;
    @FXML Label introLabel, surnameLabel, insertionLabel, firstnameLabel, streetnameLabel,
        streetnrLabel, zipcodeLabel, emailLabel, salutationLabel, referralLabel, phoneLabel,
        lionsMemberLabel, cityLabel, starLabel1, starLabel2, starLabel3, starLabel4, starLabel5, starLabel6,
        starLabel7, starLabel8, starLabel9;
    @FXML TextField surnameTextField, insertionTextField, firstnameTextField, streetnameTextField,
        streetnrTextField, zipcodeTextField, emailTextField, phoneTextField, lionsMemberTextField, cityTextField;
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
            "Registreer jezelf voor het wijnproef evenement door middel van het invullen van alle onderstaande data.\nNadat je op de knop hebt gedrukt kun je jouw bestellijst ophalen bij de balie, zodat je wijnen kunt gaan proeven.\n\nAlle invoer met een rood sterretje is verplicht.";
        this.introLabel = new Label(introText);
        this.introLabel.layoutBoundsProperty();
        introbox.getChildren().add(introLabel);
        
        this.starLabel1 = new Label ("*");
        this.starLabel1.getStyleClass().add("redStar");
        this.starLabel2 = new Label ("*");
        this.starLabel2.getStyleClass().add("redStar");
        this.starLabel3 = new Label ("*");
        this.starLabel3.getStyleClass().add("redStar");
        this.starLabel4= new Label ("*");
        this.starLabel4.getStyleClass().add("redStar");
        this.starLabel5 = new Label ("*");
        this.starLabel5.getStyleClass().add("redStar");
        this.starLabel6 = new Label ("*");
        this.starLabel6.getStyleClass().add("redStar");
        this.starLabel7 = new Label ("*");
        this.starLabel7.getStyleClass().add("redStar");
        this.starLabel8 = new Label ("*");
        this.starLabel8.getStyleClass().add("redStar");
        this.starLabel9 = new Label ("*");
        this.starLabel9.getStyleClass().add("redStar");
        this.surnameLabel = new Label("Achternaam:");
        this.surnameTextField = new TextField();
        this.insertionLabel = new Label("Tussenvoegsel:");
        this.insertionTextField = new TextField();
        this.firstnameLabel = new Label("Voornaam:");
        this.firstnameTextField = new TextField();
        this.streetnameLabel = new Label("Straat:");
        this.streetnameTextField = new TextField();
        this.streetnrLabel = new Label("Huisnummer:");
        this.streetnrTextField = new TextField();
        this.zipcodeLabel = new Label("Postcode:");
        this.zipcodeTextField = new TextField();
        this.emailLabel = new Label("E-mail:");
        this.emailTextField = new TextField();
        this.salutationLabel = new Label("Aanspreektitel:");
        ComboBox<String> salutationComboBox = new ComboBox<String>();
        salutationComboBox.getItems().addAll(
            "De heer",
            "Mevrouw",
            "De heer en mevrouw"
        );
        this.referralLabel = new Label("Geattendeerd door:");
        ComboBox<String> referralComboBox = new ComboBox<String>();
        referralComboBox.getItems().addAll(
            "Lions lid persoonlijk",
            "Lions mailing",
            "Affiche",
            "Oegeester Courant",
            "PVOO",
            "Anders"
        );
        this.phoneLabel = new Label("Telefoon nummer:");
        this.phoneTextField = new TextField();
        this.lionsMemberLabel = new Label("Vul naam in van Lions lid:");
        this.lionsMemberTextField = new TextField();
        this.cityLabel = new Label("Stad:");
        this.cityTextField = new TextField();
        this.registrationButton = new Button("Registreer");
        this.registrationButton.getStyleClass().add("form_buttons");

        HBox group1_Ster = new HBox(surnameLabel, starLabel1);
        VBox group1 = new VBox(10);
        group1.getChildren().addAll(group1_Ster, surnameTextField);
        HBox group2_Ster = new HBox(streetnameLabel, starLabel2);
        VBox group2 = new VBox(10, group2_Ster, streetnameTextField);
        HBox group3_Ster = new HBox(cityLabel, starLabel3);
        VBox group3 = new VBox(10, group3_Ster, cityTextField);
        HBox group4_Ster = new HBox(referralLabel, starLabel4);
        VBox group4 = new VBox(10, group4_Ster, referralComboBox);
        VBox group5 = new VBox(10, insertionLabel, insertionTextField);
        HBox group6_Ster = new HBox(streetnrLabel, starLabel5);
        VBox group6 = new VBox(10, group6_Ster, streetnrTextField);
        HBox group7_Ster = new HBox(emailLabel, starLabel6);
        VBox group7 = new VBox(10, group7_Ster, emailTextField);
        VBox group8 = new VBox(10, phoneLabel, phoneTextField);
        HBox group9_Ster = new HBox(firstnameLabel, starLabel7);
        VBox group9 = new VBox(10, group9_Ster, firstnameTextField);
        HBox group10_Ster = new HBox(zipcodeLabel, starLabel8);
        VBox group10 = new VBox(10, group10_Ster, zipcodeTextField);
        HBox group11_Ster = new HBox(salutationLabel, starLabel9);
        VBox group11 = new VBox(10, group11_Ster, salutationComboBox);
        VBox group12 = new VBox(10, lionsMemberLabel, lionsMemberTextField);
        VBox group13 = new VBox(registrationButton);

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
        contentPane.add(group13, 0, 4);

        //contentPane.add(group4, 3, 4);

        getChildren().addAll(introbox, contentPane);

    }
}

