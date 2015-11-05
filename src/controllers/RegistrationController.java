package controllers;

import DAO.GuestDAO;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import models.Guest;
import splashscreens.*;
import validators.EmailValidator;
import validators.TextValidator;
import validators.ZipcodeValidator;
import views.RegistrationView;
import views.SplashscreenView;

/**
 * <p>This class can register new {@link Guest} objects and inserts them into the database.</p>
 *
 * @author Alex van der Wal
 * @version 0.1, november 2015
 */
public class RegistrationController {
    private RegistrationView registrationView;
    private SplashscreenView splashscreenView;
    private GuestDAO guestDAO;
    private Guest guest;
    private int errorCounter;
    private boolean fieldActive;
    private ScreensController screensController;
    private String context, header, title, surname, infix, firstname, salutation, streetname,
        streetnr, zipcode, city, email, phone, referral, comment;

    /**
     * @param registrationView  The view where the registration is done.
     * @param guestDAO          The Data Access Object that will be used to transfer the newly created {@link Guest} to the
     *                          database.
     * @param screensController Used to direct the different windows within the application.
     */
    public RegistrationController(RegistrationView registrationView, GuestDAO guestDAO,
        ScreensController screensController) {
        this.registrationView = registrationView;
        this.screensController = screensController;
        this.guestDAO = guestDAO;
        generateHandlers();
    }

    /**
     * <p>Generates different event handlers.</p>
     */
    public void generateHandlers() {
        this.registrationView.getRegistrationButton().setOnAction(e -> readData());
        registrationView.referralComboBox.getSelectionModel().selectedItemProperty()
            .addListener(new ChangeListener<String>() {
                public void changed(ObservableValue<? extends String> observable, String o,
                    String newValue) {
                    if (newValue.equals("Lions lid persoonlijk")) {
                        registrationView.lionsMemberTextField.setVisible(true);
                        registrationView.lionsMemberLabel.setVisible(true);
                        registrationView.lionsMemberLabel
                            .setText("Vul de naam van desbetreffende in");
                        fieldActive = true;
                    } else if (newValue.equals("Anders")) {
                        registrationView.lionsMemberTextField.setVisible(true);
                        registrationView.lionsMemberLabel.setVisible(true);
                        registrationView.lionsMemberLabel.setText("Vul in hoe");
                        fieldActive = true;
                    } else {
                        registrationView.lionsMemberTextField.setVisible(false);
                        registrationView.lionsMemberLabel.setVisible(false);
                        fieldActive = false;
                    }
                    ;

                }
            });
    }

    /**
     * <p>Reads the data from the textboxes and puts them into variables.</p>
     */
    public void readData() {
        surname = registrationView.getSurname();
        infix = registrationView.getInfix();
        firstname = registrationView.getFirstname();
        salutation = registrationView.getSalutation();
        streetname = registrationView.getStreetname();
        streetnr = registrationView.getStreetnr();
        zipcode = registrationView.getZipcode();
        city = registrationView.getCity();
        email = registrationView.getEmail();
        phone = registrationView.getPhone();
        if (fieldActive != true) {
            referral = registrationView.getReferral();
        } else if (fieldActive == true) {
            referral = registrationView.getlionsMember();
        }
        comment = "hi";
        validateData();
        if (errorCounter > 0) {
            splashscreenView = new SplashscreenView(title, header, context);
        } else {
            sendRegistration();
        }
    }


    /**
     * <p>Validates the different datafields and according to the result a splash screen is shown.</p>
     */
    public void validateData() {
        context = "";
        errorCounter = 0;
        EmailValidator emailValidator = new EmailValidator();
        TextValidator textValidator = new TextValidator();
        ZipcodeValidator zipcodeValidator = new ZipcodeValidator();
        SplashDefault registrationSplash = new GeneralSplash();
        if (!textValidator.validate(surname.trim())) {
            registrationSplash = new SplashSurnameMessage(registrationSplash);
            errorCounter++;
        }
        if (!textValidator.validate(firstname.trim())) {
            registrationSplash = new SplashFirstnameMessage(registrationSplash);
            errorCounter++;
        }
        if (!textValidator.validate(streetname.trim())) {
            registrationSplash = new SplashStreetnameMessage(registrationSplash);
            errorCounter++;
        }
        if (streetnr.trim().equals("")) {
            registrationSplash = new SplashStreetnrMessage(registrationSplash);
        }
        if (!zipcodeValidator.validate(zipcode.trim())) {
            registrationSplash = new SplashZipcodeMessage(registrationSplash);
            errorCounter++;
        }
        if (!textValidator.validate(city.trim())) {
            registrationSplash = new SplashCityMessage(registrationSplash);
            errorCounter++;
        }
        if (!emailValidator.validate(email.trim())) {
            registrationSplash = new SplashEmailMessage(registrationSplash);
            errorCounter++;
        }
        if (salutation == null) {
            registrationSplash = new SplashSalutationMessage(registrationSplash);
            errorCounter++;
        }
        if (referral == null) {
            registrationSplash = new SplashReferralMessage(registrationSplash);
        }
        registrationSplash = new RegistrationSetHead(registrationSplash);
        title = registrationSplash.getTitleText();
        header = registrationSplash.getHeaderText();
        context = registrationSplash.getContextText();
    }

    /**
     * <p>Inserts the new guest into the database.</p>
     */
    public void sendRegistration() {
        SplashDefault registrationSplash = new GeneralSplash();
        registrationSplash = new RegistrationCompleteMessage(registrationSplash);
        title = registrationSplash.getTitleText();
        header = registrationSplash.getHeaderText();
        context = registrationSplash.getContextText();
        this.guest =
            new Guest(surname, infix, firstname, salutation, streetname, streetnr, zipcode, city,
                email, phone, referral, comment);
        this.guestDAO.addGuest(guest);
        splashscreenView = new SplashscreenView(title, header, context);
        resetFields();
    }

    /**
     * <p>Resets all the fields so that they are empty again.</p>
     */
    public void resetFields() {
        screensController.screenRemove(ControllersController.getREGISTRATIONID());
        this.registrationView = new RegistrationView();
        screensController
            .screenLoadSet(ControllersController.getREGISTRATIONID(), registrationView);
        generateHandlers();
    }
}
