package edu.ntu.scse.entity;

/**
 * 
 * @author suhuangyuan
 *
 */
public class Person {

	/**
	 * first name for a person
	 */
	private String firstName;
	/**
	 * last name for a person
	 */
	private String lastName;
	/**
	 * email of a person
	 */
    protected String email;
	/**
	 * phone number of a person
	 */
	private String phoneNo;

	/**
	 * Default constructor
	 */
	public Person() {
		super();
	}

	/**
	 * Constructor for Person using all attributes
	 * 
	 * @param firstName
	 * @param lastName
	 * @param email
	 * @param phoneNo
	 */
	public Person(String firstName, String lastName, String email, String phoneNo) {
		super();
		this.firstName = firstName;
		this.lastName = lastName;
		this.email = email;
		this.phoneNo = phoneNo;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPhoneNo() {
		return phoneNo;
	}

	public void setPhoneNo(String phoneNo) {
		this.phoneNo = phoneNo;
	}

	@Override
	public boolean equals(Object o) {
		if (this == o) return true;
		if (!(o instanceof Person)) return false;
		Person person = (Person) o;
		return getFirstName().equals(person.getFirstName()) &&
				getLastName().equals(person.getLastName()) &&
				getEmail().equals(person.getEmail()) &&
				getPhoneNo().equals(person.getPhoneNo());
	}
}
