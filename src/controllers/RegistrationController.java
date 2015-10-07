package controllers;

import DAO.GuestDAO;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import models.Guest;
import splashscreens.*;
import validators.EmailValidator;
import validators.TextValidator;
import validators.ZipcodeValidator;
import views.RegistrationView;

/**
 * Created by sander on 28-9-2015.
 */
public class RegistrationController {
    private RegistrationView registrationView;
    private GuestDAO guestDAO;
    private Guest guest;
    private int i;
    private String context, surname, infix, firstname, salutation, 
    streetname, streetnr, zipcode, city, email, phone, referral, 
    comment;
    public RegistrationController(RegistrationView registrationView, GuestDAO guestDAO) {
        this.registrationView = registrationView;
        this.guestDAO = guestDAO;
        this.registrationView.getRegistrationButton().setOnAction(e -> readData());
        
    }
    public void readData() {
    	surname = registrationView.getSurname();
    	infix = registrationView.getInfix();
    	firstname = registrationView.getFirstname();
    	salutation = registrationView.getSalutation();
    	streetname = registrationView.getStreetname();
    	streetnr = registrationView.getStreetnr();
    	zipcode =registrationView.getZipcode();
		city = registrationView.getCity();
		email = registrationView.getEmail();
		phone = registrationView.getPhone();
		referral = registrationView.getReferral();
		comment = "hi";
		validateData();
		if(i > 0) {
			Alert();
		}
		else {
			sendRegistration();
		}
    }
    
    public void Alert() {
    	Alert alert = new Alert(AlertType.INFORMATION);
		alert.setTitle("Alertbox voor");
		alert.setHeaderText("Foutieve data");
		alert.setContentText(context);
		alert.showAndWait();

    }
    
    public void validateData() {
    	context = "";
    	i = 0;
    	EmailValidator emailValidator = new EmailValidator();
    	TextValidator textValidator = new TextValidator();
    	ZipcodeValidator zipcodeValidator = new ZipcodeValidator();
    	SplashDefault registrationSplash = new RegistrationSplash();
    	if(!textValidator.validate(surname.trim())) {
			registrationSplash = new RegistrationSurnameMessage(registrationSplash);
			i++;
    	}
    	if(!textValidator.validate(firstname.trim())) {
  	   		registrationSplash = new RegistrationFirstnameMessage(registrationSplash);
  	   		i++;
  	   	}
    	if(!textValidator.validate(streetname.trim())) {
    		registrationSplash = new RegistrationStreetnameMessage(registrationSplash);
    		i++;
    	}
    	if(streetnr.trim().equals( "" )) {
    		registrationSplash = new RegistrationStreetnrMessage(registrationSplash);
    	}
    	if(!zipcodeValidator.validate(zipcode.trim())) {
  	   		registrationSplash = new RegistrationZipcodeMessage(registrationSplash);
  	   		i++;
  	   	}
    	if(!textValidator.validate(city.trim())) {
    		registrationSplash = new RegistrationCityMessage(registrationSplash);
    		i++;
    	}
    	if(!emailValidator.validate(email.trim())) {
  	        registrationSplash = new RegistrationEmailMessage(registrationSplash);
  	        i++;
  	    }
    	if(salutation == null) {
    		registrationSplash = new RegistrationSalutationMessage(registrationSplash);
    		i++;
    	}
    	if(referral == null) {
    		registrationSplash = new RegistrationReferralMessage(registrationSplash);
    	}
    	context = registrationSplash.getContextText();
    }
    

    public void sendRegistration() {
    			this.guest = new Guest(surname, infix, firstname,
    			salutation, streetname, streetnr, zipcode, city, email, 
    			phone, referral, comment);
    	   		this.guestDAO.addGuest(guest);
    }
}



