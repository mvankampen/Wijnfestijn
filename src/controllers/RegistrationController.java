package controllers;

import DAO.GuestDAO;
import models.Guest;
import validators.EmailValidator;
import views.RegistrationView;

/**
 * Created by sande on 28-9-2015.
 */
public class RegistrationController {
    private RegistrationView registrationView;
    private GuestDAO guestDAO;
    private Guest guest;
    public RegistrationController(RegistrationView registrationView, GuestDAO guestDAO) {
        this.registrationView = registrationView;
        this.guestDAO = guestDAO;
        this.registrationView.getRegistrationButton().setOnAction(e -> sendRegistration());
        
    }
    
    public void sendRegistration() {
    	 EmailValidator emailValidator = new EmailValidator();
    	   if(!emailValidator.validate(registrationView.getEmail().trim())) {
    	        System.out.print("Invalid Email ID");
    	        /*
    	           Action that you want to take. For ex. make email id field red
    	           or give message box saying invalid email id.
    	        */
    	   }
    	   else { this.guest = new Guest(registrationView.getSurname(),
    			registrationView.getInfix(), registrationView.getFirstname(),
    			registrationView.getSalutation(), registrationView.getStreetname(), 
    			registrationView.getStreetnr(), registrationView.getZipcode(), 
    			registrationView.getCity(), registrationView.getEmail(), 
    			registrationView.getPhone(), registrationView.getReferral());
    	   		this.guestDAO.addGuest(guest);}
    }
}
