package models;

/**
 * Created by Sander de Jong on 21-9-2015.
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

	// Assembles a guest object, so it can be used easily
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
	//used for making a order, where the ID is needed
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

	// getters
	public int getId() {
		return id;
	}

	public String getSurname() {
		return surname;
	}

	public String getInfix() {
		return infix;
	}

	public String getFirstname() {
		return firstname;
	}

	public String getSalutation() {
		return salutation;
	}

	public String getStreet() {
		return street;
	}

	public String getStreetnr() {
		return streetnr;
	}

	public String getZipcode() {
		return zipcode;
	}

	public String getCity() {
		return city;
	}

	public String getEmail() {
		return email;
	}

	public String getPhone() {
		return phone;
	}

	public String getComment() {
		return comment;
	}

	public String getReferal() {
		return referal;
	}

	public Boolean getNo_show() {
		return no_show;
	}
	//setters
	public void setInfix(String infix) {
		this.infix = infix;
	}

	public void setSurname(String lastname) {
		this.surname = lastname;
	}

	public void setFirstname(String firstname) {
		this.firstname = firstname;
	}

	public void setSalutation(String salutation) {
		this.salutation = salutation;
	}

	public void setStreet(String street) {
		this.street = street;
	}

	public void setStreetnr(String streetnr) {
		this.streetnr = streetnr;
	}

	public void setZipcode(String zipcode) {
		this.zipcode = zipcode;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public void setComment(String comment) {
		this.comment = comment;
	}

	public void setReferal(String referal) {
		this.referal = referal;
	}

	public void setNo_show(Boolean no_show) {
		this.no_show = no_show;
	}

}
