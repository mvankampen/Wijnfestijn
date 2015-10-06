package controllers;

import DAO.GuestDAO;
import models.Guest;
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
    	this.guest = new Guest(registrationView.getSurname(),
    			registrationView.getInfix(), registrationView.getFirstname(),
    			registrationView.getSalutation(), registrationView.getStreetname(), 
    			registrationView.getStreetnr(), registrationView.getZipcode(), 
    			registrationView.getCity(), registrationView.getEmail(), 
    			registrationView.getPhone(), registrationView.getReferral());
    	this.guestDAO.addGuest(guest);
    }
}
