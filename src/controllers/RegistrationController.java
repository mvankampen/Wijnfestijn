package controllers;

import DAO.CustomerDAO;
import models.Customer;
import views.RegistrationView;

/**
 * Created by Sander de Jong on 28-9-2015.
 */
public class RegistrationController {
    private RegistrationView registrationView;
    private Customer customer;
    private CustomerDAO customerDAO;

    public RegistrationController(RegistrationView registrationView, Customer customer, CustomerDAO) {
        this.registrationView = registrationView;
        this.customer = customer;
    }

    public void setValues(String lastname, String firstname, String insertion, String streetname,
                          int streetnr, String zipcode, String city, String email, String salutation,
                          String referral, String phone, String lionsMember) {
        this.customer.setLastname(lastname);
        this.customer.setFirstname(firstname);
        this.customer.setInsertion(insertion);
        this.customer.setStreetname(streetname);
        this.customer.setStreetnr(streetnr);
        this.customer.setZipcode(zipcode);
        this.customer.setCity(city);
        this.customer.setEmail(email);
        this.customer.setSalutation(salutation);
        this.customer.setReferral(referral);
        this.customer.setPhone(phone);
        this.customer.setLionsMember(lionsMember);
    }

    public void saveCustomer(Customer customer) {
        this.customerDAO.
    }
}
