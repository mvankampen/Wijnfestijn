package controllers;

import DAO.GuestDAO;
import models.Guest;
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
    private String surname, infix, firstname, salutation, streetname, streetnr, zipcode, city, email, phone, referral;
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
    	streetnr = registrationView.getStreetname();
    	zipcode =registrationView.getZipcode();
		city = registrationView.getCity();
		email = registrationView.getEmail();
		phone = registrationView.getPhone();
		referral = registrationView.getReferral();
		validateData();
		
    }
    
    public void validateData() {
    	EmailValidator emailValidator = new EmailValidator();
    	TextValidator textValidator = new TextValidator();
    	ZipcodeValidator zipcodeValidator = new ZipcodeValidator();
    	if(!textValidator.validate(surname.trim())) {
    		 System.out.print("Achternaam moet worden ingevuld \n");
   	        /*
   	           Action that you want to take. For ex. make email id field red
   	           or give message box saying invalid email id.
   	        */
    	}
    	if(!textValidator.validate(firstname.trim())) {
  	   		System.out.print("Je moet een naam invullen \n");
  	   	}
    	
  	   	if(!emailValidator.validate(email.trim())) {
  	        System.out.print("Invalid Email ID \n");
  	        /*
  	           Action that you want to take. For ex. make email id field red
  	           or give message box saying invalid email id.
  	        */
  	    }
  	   	if(!zipcodeValidator.validate(zipcode.trim())) {
  	   		System.out.print("Je zip moet bestaan uit 4 cijfers, 2 letters \n");
  	   	}
  	   	
    }
    

    public void sendRegistration() {
    	
    			this.guest = new Guest(surname, infix, firstname,
    			salutation, streetname, streetnr, zipcode, city, email, 
    			phone, referral);
    	   		this.guestDAO.addGuest(guest);
    }
}



