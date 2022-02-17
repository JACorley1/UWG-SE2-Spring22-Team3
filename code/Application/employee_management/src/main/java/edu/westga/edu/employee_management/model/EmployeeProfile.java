package edu.westga.edu.employee_management.model;

/**
 * Employee Profile Class
 * 
 * @author Fernando Dominguez
 * @version 2/16/22
 */
public class EmployeeProfile {
	
	private static final String FIRST_NAME_CAN_NOT_BE_NULL = "first name can not be null";
	private static final String MIDDLE_NAME_CAN_NOT_BE_NULL = "middle name can not be null";
	private static final String LAST_NAME_CAN_NOT_BE_NULL = "last name can not be null";
	private static final String EMAIL_CAN_NOT_BE_NULL = "email  can not be null";
	private static final String PHONE_CAN_NOT_BE_NULL = "phone number can not be null";
	private static final String TITLE_CAN_NOT_BE_NULL = "title can not be null";
	private static final String TITLE_CAN_NOT_BE_EMPTY = "title can not be empty";
	private static final String FIRST_NAME_CAN_NOT_BE_EMPTY = "first name can not be empty";
	private static final String LAST_NAME_CAN_NOT_BE_EMPTY = "last name can not be empty";
	private static final String EMAIL_CAN_NOT_BE_EMPTY = "email can not be empty";
	
	
	private String title;
	private String firstName;
	private String middleName;
	private String lastName;
	private String email;
	private String phone;
	
	/**
	 * The Employee Profile constructor
	 * 
	 * @precondition title != null && firstName != null && lastName != null && 
	 * 		email != null && phone != null && !title.isEmpty() && !firstName.isEmpty() &&
	 * 		!lastName.isEmpty() && !email.isEmpty()
	 * 
	 * @postcondition getTitle() == title && getFirstName() == firstName && getMiddleName == midName &&
	 * 		getLastName() == lastName && getEmail() == email && getPhone() == phone
	 * 
	 * @param title the employee's title
	 * @param firstName the employee's first name
	 * @param midName the employee's middle name
	 * @param lastName the employee's last name
	 * @param email the employee's email
	 * @param phone the employee's phone
	 */
	public EmployeeProfile(String title, String firstName, String midName, String lastName, String email, String phone) {
		this.setTitle(title);
		this.setFirstName(firstName);
		this.setMiddleName(midName);
		this.setLastName(lastName);
		this.setEmail(email);
		this.setPhone(phone);
	}
	
	/**
	 * Returns Title
	 * 
	 * @precondition none
	 * @postcondition none
	 * 
	 * @return employee's title
	 */
	
	public String getTitle() {
		return this.title;
	}
	
	/**
	 * Sets Employee's title
	 * 
	 * @precondition title != null && !title.isEmpty()
	 * @postcondition getTitle() == title
	 * 
	 * @param title the Employee's title
	 */
	public void setTitle(String title) {
		if (title == null) {
			throw new NullPointerException(EmployeeProfile.TITLE_CAN_NOT_BE_NULL);
		}
		if (title.isEmpty()) {
			throw new IllegalArgumentException(EmployeeProfile.TITLE_CAN_NOT_BE_EMPTY);
		}
		this.title = title;
	}
	
	
	/**
	 * Returns First Name
	 * 
	 * @precondition none
	 * @postcondition none
	 * 
	 * @return employee's First Name
	 */
	
	public String getFirstName() {
		return this.firstName;
	}
	
	/**
	 * Sets Employee's first Name
	 * 
	 * @precondition firstName != null && !firstName.isEmpty()
	 * @postcondition getFirstName() == firsName
	 * 
	 * @param firstName the Employee's first Name
	 */
	public void setFirstName(String firstName) {
		if (firstName == null) {
			throw new NullPointerException(EmployeeProfile.FIRST_NAME_CAN_NOT_BE_NULL);
		}
		if (firstName.isEmpty()) {
			throw new IllegalArgumentException(EmployeeProfile.FIRST_NAME_CAN_NOT_BE_EMPTY);
		}
		this.firstName = firstName;
	}
	
	/**
	 * Returns Middle Name
	 * 
	 * @precondition none
	 * @postcondition none
	 * 
	 * @return employee's Middle Name
	 */
	public String getMiddleName() {
		return this.middleName;
	}
	
	/**
	 * Sets Employee's middle name
	 * 
	 * @precondition middleName != null
	 * @postcondition getMiddleName() == middleName
	 * 
	 * @param middleName the Employee's middle name
	 */
	public void setMiddleName(String middleName) {
		if (middleName == null) {
			throw new NullPointerException(EmployeeProfile.MIDDLE_NAME_CAN_NOT_BE_NULL);
		}
		this.middleName = middleName;
	}
	
	/**
	 * Returns Last Name
	 * 
	 * @precondition none
	 * @postcondition none
	 * 
	 * @return employee's Last Name
	 */
	public String getLastName() {
		return this.lastName;
	}
	
	/**
	 * Sets Employee's last name
	 * 
	 * @precondition lastName != null && !lastName.isEmpty()
	 * @postcondition getLastName() == lastName
	 * 
	 * @param lastName the Employee's last name
	 */
	public void setLastName(String lastName) {
		if (lastName == null) {
			throw new NullPointerException(EmployeeProfile.LAST_NAME_CAN_NOT_BE_NULL);
		}
		if (lastName.isEmpty()) {
			throw new IllegalArgumentException(EmployeeProfile.LAST_NAME_CAN_NOT_BE_EMPTY);
		}
		this.lastName = lastName;
	}
	
	/**
	 * Returns Email
	 * 
	 * @precondition none
	 * @postcondition none
	 * 
	 * @return employee's Email
	 */
	public String getEmail() {
		return this.email;
	}
	
	/**
	 * Sets Employee's email
	 * 
	 * @precondition email != null && !email.isEmpty()
	 * @postcondition getEmail() == email
	 * 
	 * @param email the Employee's email
	 */
	public void setEmail(String email) {
		if (email == null) {
			throw new NullPointerException(EmployeeProfile.EMAIL_CAN_NOT_BE_NULL);
		}
		if (email.isEmpty()) {
			throw new IllegalArgumentException(EmployeeProfile.EMAIL_CAN_NOT_BE_EMPTY);
		}
		this.email = email;
	}
	
	/**
	 * Returns Phone
	 * 
	 * @precondition none
	 * @postcondition none
	 * 
	 * @return employee's Phone
	 */
	public String getPhone() {
		return this.phone;
	}
	
	/**
	 * Sets Employee's phone
	 * 
	 * @precondition phone != null
	 * @postcondition getPhone() == phone
	 * 
	 * @param phone the employee's phone
	 */
	public void setPhone(String phone) {
		if (phone == null) {
			throw new NullPointerException(EmployeeProfile.PHONE_CAN_NOT_BE_NULL);
		}
		this.phone = phone;
	}
}