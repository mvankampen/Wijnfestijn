package models;

import javafx.collections.ObservableList;

/**
 * Created by Sander de Jong on 21-9-2015.
 */
public class Guest{
    private int id;
    private String lastname;
    private String infix;
    private String firstname;
    private String salutation;
    private String street;
    private String streetnr;
    private String zipcode;
    private String city;
    private String email;
    private String phone;
    private String comment;
    private String referal;
    private Boolean no_show = false;

    public Guest(String lastname, String infix, String firstname, String salutation, String street,
        String streetnr, String zipcode, String city, String email, String phone, String referal,
        String comment) {
        this.lastname = lastname;
        this.infix = infix;
        this.firstname = firstname;
        this.salutation = salutation;
        this.street = street;
        this.streetnr = streetnr;
        this.zipcode = zipcode;
        this.city = city;
        this.email = email;
        this.phone = phone;
        this.comment = comment;
        this.referal = referal;
    }

    public Guest(int id, String lastname, String infix, String firstname, String salutation,
        String street, String streetnr, String zipcode, String city, String email, String phone,
        String referal, String comment, boolean no_show) {
        this.id = id;
        this.lastname = lastname;
        this.infix = infix;
        this.firstname = firstname;
        this.salutation = salutation;
        this.street = street;
        this.streetnr = streetnr;
        this.zipcode = zipcode;
        this.city = city;
        this.email = email;
        this.phone = phone;
        this.comment = comment;
        this.referal = referal;
        this.no_show = no_show;
    }

    public int getId() {
        return id;
    }

    public String getSurname() {
        return lastname;
    }

    public void setSurname(String lastname) {
        this.lastname = lastname;
    }

    public String getInfix() {
        return infix;
    }

    public void setInfix(String infix) {
        this.infix = infix;
    }

    public String getFirstname() {
        return firstname;
    }

    public void setFirstname(String firstname) {
        this.firstname = firstname;
    }

    public String getSalutation() {
        return salutation;
    }

    public void setSalutation(String salutation) {
        this.salutation = salutation;
    }

    public String getStreetname() {
        return street;
    }

    public void setStreetname(String street) {
        this.street = street;
    }

    public String getStreetnr() {
        return streetnr;
    }

    public void setStreetnr(String streetnr) {
        this.streetnr = streetnr;
    }

    public String getZipcode() {
        return zipcode;
    }

    public void setZipcode(String zipcode) {
        this.zipcode = zipcode;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    public String getReferal() {
        return referal;
    }

    public void setReferal(String referal) {
        this.referal = referal;
    }

    public Boolean getNo_show() {
        return no_show;
    }

    public void setNo_show(Boolean no_show) {
        this.no_show = no_show;
    }
}
