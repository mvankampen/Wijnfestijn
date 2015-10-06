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

    public RegistrationController(RegistrationView registrationView, GuestDAO guestDAO, Guest guest) {
        this.registrationView = registrationView;
        this.guestDAO = guestDAO;
        this.guest = guest;
    }
}
