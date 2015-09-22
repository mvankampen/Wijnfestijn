package models;

/**
 * Created by Sander de Jong on 21-9-2015.
 */
public class Customer {
    private String lastname;
    private String firstname;
    private String insertion;
    private String streetname;
    private int streetnr;
    private String zipcode;
    private String email;
    private String salutation;
    private String referral;
    private String phone;
    private String lionsMember;


    public Customer(String lastname, String firstname, String insertion, String streetname,
                    int streetnr, String zipcode, String email, String salutation,
                    String referral, String phone, String lionsMember) {
        this.lastname = lastname;
        this.firstname = firstname;
        this.insertion = insertion;
        this.streetname = streetname;
        this.streetnr = streetnr;
        this.zipcode = zipcode;
        this.email = email;
        this.salutation = salutation;
        this.referral = referral;
        this.phone = phone;
        this.lionsMember = lionsMember;
    }

    public String getLastname() {
        return lastname;
    }

    public String getFirstname() {
        return firstname;
    }

    public String getInsertion() {
        return insertion;
    }

    public String getStreetname() {
        return streetname;
    }

    public int getStreetnr() {
        return streetnr;
    }

    public String getZipcode() {
        return zipcode;
    }

    public String getEmail() {
        return email;
    }

    public String getSalutation() {
        return salutation;
    }

    public String getReferral() {
        return referral;
    }

    public String getPhone() {
        return phone;
    }

    public String getLionsMember() {
        return lionsMember;
    }

    public void setLastname(String lastname) {
        this.lastname = lastname;
    }

    public void setFirstname(String firstname) {
        this.firstname = firstname;
    }

    public void setInsertion(String insertion) {
        this.insertion = insertion;
    }

    public void setStreetname(String streetname) {
        this.streetname = streetname;
    }

    public void setStreetnr(int streetnr) {
        this.streetnr = streetnr;
    }

    public void setZipcode(String zipcode) {
        this.zipcode = zipcode;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setSalutation(String salutation) {
        this.salutation = salutation;
    }

    public void setReferral(String referral) {
        this.referral = referral;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public void setLionsMember(String lionsMember) {
        this.lionsMember = lionsMember;
    }
}
