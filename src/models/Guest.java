package models;

/**
 * @author Sander de Jong
 * @version 0.1, november 2015.
 * 		Description:
 * 		Guest holds all the information about the Guests.
 */
public class Guest {
	private int id;
	private String surname;
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

	/**
	 * Constructor
	 * 
	 * @param lastname Sets the last name of the guest
	 * @param infix Sets the infix on the guest
	 * @param firstname Sets the first name of the guest
	 * @param salutation Sets the salutation of the guest
	 * @param street Sets the street of the guest
	 * @param streetnr Sets the street number of the guest
	 * @param zipcode Sets the zipcode of the guest
	 * @param city Sets the city of the guest
	 * @param email Sets the e-mail of the guest
	 * @param phone Sets the phone number of the guest
	 * @param referal Sets the referal of the guest
	 * @param comment Sets the comment of the guest
	 */
	public Guest(String lastname, String infix, String firstname, String salutation, String street, String streetnr,
			String zipcode, String city, String email, String phone, String referal, String comment) {
		this.surname = lastname;
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

	/**
	 * Constructor
	 * 
	 * @param lastname Holds the last name of the guest
	 * @param infix Holds the infix on the guest
	 * @param firstname Holds the first name of the guest
	 * @param salutation Holds the salutation of the guest
	 * @param street Holds the street of the guest
	 * @param streetnr Holds the street number of the guest
	 * @param zipcode Holds the zipcode of the guest
	 * @param city Holds the city of the guest
	 * @param email Holds the e-mail of the guest
	 * @param phone Holds the phone number of the guest
	 * @param referal Holds the referal of the guest
	 * @param comment Holds the comment of the guest
	 * @param no_show Shows if the guest attended the event
	 */
	public Guest(int id, String lastname, String infix, String firstname, String salutation, String street,
			String streetnr, String zipcode, String city, String email, String phone, String referal, String comment,
			boolean no_show) {
		this.id = id;
		this.surname = lastname;
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

	/**
	 * 
	 * @return Returns the guest id
	 */
	public int getId() {
		return id;
	}
	/**
	 * 
	 * @return Returns the surname of guest
	 */
	public String getSurname() {
		return surname;
	}
	/**
	 * 
	 * @return Returns the infix of guest
	 */
	public String getInfix() {
		return infix;
	}
	/**
	 * 
	 * @return Returns the first name of guest
	 */
	public String getFirstname() {
		return firstname;
	}
	/**
	 * 
	 * @return Returns the salutation of guest
	 */
	public String getSalutation() {
		return salutation;
	}
	/**
	 * 
	 * @return Returns the street of guest
	 */
	public String getStreet() {
		return street;
	}
	/**
	 * 
	 * @return Returns the street number of guest
	 */
	public String getStreetnr() {
		return streetnr;
	}
	/**
	 * 
	 * @return Returns the zipcode of guest
	 */
	public String getZipcode() {
		return zipcode;
	}
	/**
	 * 
	 * @return Returns the city of guest
	 */
	public String getCity() {
		return city;
	}
	/**
	 * 
	 * @return Returns the e-mail of guest
	 */
	public String getEmail() {
		return email;
	}
	/**
	 * 
	 * @return Returns the phone number of guest
	 */
	public String getPhone() {
		return phone;
	}
	/**
	 * 
	 * @return Returns the comment of guest
	 */
	public String getComment() {
		return comment;
	}
	/**
	 * 
	 * @return Returns the referral of guest
	 */
	public String getReferal() {
		return referal;
	}
	/**
	 * 
	 * @return Returns if the guest has shown up
	 */
	public Boolean getNo_show() {
		return no_show;
	}
	
	/**
	 * 
	 * @param infix Sets the value of infix
	 */
	public void setInfix(String infix) {
		this.infix = infix;
	}
	/**
	 * 
	 * @param lastname Sets the value of last name
	 */
	public void setSurname(String lastname) {
		this.surname = lastname;
	}
	/**
	 * 
	 * @param firstname Sets the value of first name
	 */
	public void setFirstname(String firstname) {
		this.firstname = firstname;
	}
	/**
	 * 
	 * @param salutation Sets the value of salutation
	 */
	public void setSalutation(String salutation) {
		this.salutation = salutation;
	}
	/**
	 * 
	 * @param street  Sets the value of street
	 */
	public void setStreet(String street) {
		this.street = street;
	}
	/**
	 * 
	 * @param streetnr Sets the value of street number
	 */
	public void setStreetnr(String streetnr) {
		this.streetnr = streetnr;
	}
	/**
	 * 
	 * @param zipcode Sets the value of zipcode
	 */
	public void setZipcode(String zipcode) {
		this.zipcode = zipcode;
	}
	/**
	 * 
	 * @param city Sets the value of city
	 */
	public void setCity(String city) {
		this.city = city;
	}
	/**
	 * 
	 * @param email  Sets the value of e-mail
	 */
	public void setEmail(String email) {
		this.email = email;
	}
	/**
	 * 
	 * @param phone Sets the value of phone
	 */
	public void setPhone(String phone) {
		this.phone = phone;
	}
	/**
	 * 
	 * @param comment Sets the value of comment
	 */
	public void setComment(String comment) {
		this.comment = comment;
	}
	/**
	 * 
	 * @param referal Sets the value of referal
	 */
	public void setReferal(String referal) {
		this.referal = referal;
	}
	/**
	 * 
	 * @param no_show Sets if the guest has shown up
	 */
	public void setNo_show(Boolean no_show) {
		this.no_show = no_show;
	}

}
