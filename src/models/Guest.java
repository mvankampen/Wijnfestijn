package models;

/**
 * Created by Sander de Jong on 21-9-2015.
 */
public class Guest {
    private String surname;
    private String infix;
    private String firstname;
    private String salutation;
    private String streetname;
    private String streetnr;
    private String zipcode;
    private String city;
    private String email;
    private String phone;
    private String comment;
    private String referral;
    private Boolean no_show;

    public Guest() {

    }

    public Guest(String surname, String infix, String firstname, String salutation, String streetname,
        String streetnr, String zipcode, String city, String email,String phone, String referral) {
        this.surname = surname;
        this.infix = infix;
        this.firstname = firstname;
        this.salutation = salutation;
        this.streetname = streetname;
        this.streetnr = streetnr;
        this.zipcode = zipcode;
        this.city = city;
        this.email = email;
        this.phone = phone;
        this.referral = referral;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
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
        return streetname;
    }

    public void setStreetname(String streetname) {
        this.streetname = streetname;
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
        return referral;
    }

    public void setReferal(String referal) {
        this.referral = referal;
    }

    public Boolean getNo_show() {
        return no_show;
    }

    public void setNo_show(Boolean no_show) {
        this.no_show = no_show;
    }
}
