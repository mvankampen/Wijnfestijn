package models;

/**
 * Created by Sander de Jong on 21-9-2015.
 */
public class Guest {
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
    private String referral;
    private Boolean no_show;

    public Guest() {

    }

    public String getLastname() {
        return lastname;
    }

    public void setLastname(String lastname) {
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

    public String getStreet() {
        return street;
    }

    public void setStreet(String street) {
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

    public String getReferral() {
        return referral;
    }

    public void setReferral(String referral) {
        this.referral = referral;
    }

    public Boolean getNo_show() {
        return no_show;
    }

    public void setNo_show(Boolean no_show) {
        this.no_show = no_show;
    }
}
