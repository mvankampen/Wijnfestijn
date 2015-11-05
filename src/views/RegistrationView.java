package views;


import controllers.ScreensController;
import interfaces.ControlledScreen;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;

/**
 * @author Alex van der Wal
 * @author Michael van Kampen
 * @version 0.1, november 2015
 *          Description:
 *          Registration view generates an output presentation to the user based on changes in the model.
 */

public class RegistrationView extends AnchorPane implements ControlledScreen {
    private ScreensController screensController;
    @FXML Label introLabel, surnameLabel, infixLabel, firstnameLabel, streetnameLabel,
        streetnrLabel, zipcodeLabel, emailLabel, salutationLabel, referralLabel, phoneLabel,
        cityLabel, starLabel1, starLabel2, starLabel3, starLabel4, starLabel5, starLabel6,
        starLabel7, starLabel8, starLabel9;
    @FXML public Label lionsMemberLabel;
    @FXML TextField surnameTextField, infixTextField, firstnameTextField, streetnameTextField,
        streetnrTextField, zipcodeTextField, emailTextField, phoneTextField;
    @FXML public TextField lionsMemberTextField;
    @FXML TextField cityTextField;
    @FXML Button registrationButton;
    @FXML public ComboBox<String> salutationComboBox, referralComboBox;


    @Override public void setScreenController(ScreensController screensController) {
        this.screensController = screensController;
    }

    /**
     * <P>Default Constructor</P>
     */
    public RegistrationView() {
        createView();
    }

    /**
     * <P>Setting up the display</P>
     */
    private void createView() {
        getStyleClass().addAll("background");
        setMinSize(1200, 800);
        setupContentPane();
    }

    /**
     * <P>Setting up the Content Pane</P>
     */
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

        this.starLabel1 = new Label("*");
        this.starLabel1.getStyleClass().add("redStar");
        this.starLabel2 = new Label("*");
        this.starLabel2.getStyleClass().add("redStar");
        this.starLabel3 = new Label("*");
        this.starLabel3.getStyleClass().add("redStar");
        this.starLabel4 = new Label("*");
        this.starLabel4.getStyleClass().add("redStar");
        this.starLabel5 = new Label("*");
        this.starLabel5.getStyleClass().add("redStar");
        this.starLabel6 = new Label("*");
        this.starLabel6.getStyleClass().add("redStar");
        this.starLabel7 = new Label("*");
        this.starLabel7.getStyleClass().add("redStar");
        this.starLabel8 = new Label("*");
        this.starLabel8.getStyleClass().add("redStar");
        this.starLabel9 = new Label("*");
        this.starLabel9.getStyleClass().add("redStar");
        this.surnameLabel = new Label("Achternaam:");
        this.surnameTextField = new TextField();
        this.infixLabel = new Label("Tussenvoegsel:");
        this.infixTextField = new TextField();
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
        salutationComboBox = new ComboBox<String>();
        salutationComboBox.getItems().addAll("De heer", "Mevrouw", "De heer en mevrouw");
        this.referralLabel = new Label("Geattendeerd door:");
        referralComboBox = new ComboBox<String>();
        referralComboBox.getItems()
            .addAll("Lions lid persoonlijk", "Lions mailing", "Affiche", "Oegeester Courant",
                "PVOO", "Anders");
        this.phoneLabel = new Label("Telefoon nummer:");
        this.phoneTextField = new TextField();
        this.lionsMemberLabel = new Label();
        lionsMemberLabel.setVisible(false);
        this.lionsMemberTextField = new TextField();
        lionsMemberTextField.setVisible(false);
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
        VBox group5 = new VBox(10, infixLabel, infixTextField);
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

    /**
     * @return surname of guest
     */
    public String getSurname() {
        return this.surnameTextField.getText();
    }

    /**
     * @return streetname of guest
     */
    public String getStreetname() {
        return this.streetnameTextField.getText();
    }

    /**
     * @return email of guest
     */
    public String getEmail() {
        return this.emailTextField.getText();
    }

    /**
     * @return phone of guest
     */
    public String getPhone() {
        return this.phoneTextField.getText();
    }

    /**
     * @return infix of guest
     */
    public String getInfix() {
        return this.infixTextField.getText();
    }

    /**
     * @return street number of guest
     */
    public String getStreetnr() {
        return this.streetnrTextField.getText();
    }

    /**
     * @return salutation of guest
     */
    public String getSalutation() {
        return this.salutationComboBox.getValue();
    }

    /**
     * @return Lions member
     */
    public String getlionsMember() {
        return this.lionsMemberTextField.getText();
    }

    /**
     * @return Firstname of guest
     */
    public String getFirstname() {
        return this.firstnameTextField.getText();
    }

    /**
     * @return zipcode of guest
     */
    public String getZipcode() {
        return this.zipcodeTextField.getText();
    }

    /**
     * @return City of guest
     */
    public String getCity() {
        return this.cityTextField.getText();
    }

    /**
     * @return Referral of of guest
     */
    public String getReferral() {
        return this.referralComboBox.getValue();
    }

    /**
     * @return Button object registration button
     */
    public Button getRegistrationButton() {
        return this.registrationButton;
    }

}

