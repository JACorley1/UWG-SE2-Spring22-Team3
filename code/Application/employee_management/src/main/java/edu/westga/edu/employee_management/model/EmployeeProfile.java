package edu.westga.edu.employee_management.model;

import java.time.LocalDate;
import java.util.HashMap;
import java.util.Map;

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
	private static final String FIRST_NAME_CAN_NOT_BE_EMPTY = "first name can not be empty";
	private static final String LAST_NAME_CAN_NOT_BE_EMPTY = "last name can not be empty";
	private static final String EMAIL_CAN_NOT_BE_EMPTY = "email can not be empty";
	private static final String ID_CAN_NOT_BE_NEGATIVE = "id can not be negative";

	private int id;
	private String firstName;
	private String middleName;
	private String lastName;
	private String email;
	private String phone;
	private boolean hr;
	private Map<LocalDate, TimeSheet> timesheets;

	/**
	 * The Employee Profile constructor
	 * 
	 * @precondition firstName != null && lastName != null && email
	 *               != null && phone != null && !firstName.isEmpty() 
	 *               && !lastName.isEmpty() && !email.isEmpty()
	 * 
	 * @postcondition getTitle() == title && getFirstName() == firstName &&
	 *                getMiddleName == midName && getLastName() == lastName &&
	 *                getEmail() == email && getPhone() == phone
	 * 
	 * @param id 		the employee's id
	 * @param firstName the employee's first name
	 * @param midName   the employee's middle name
	 * @param lastName  the employee's last name
	 * @param email     the employee's email
	 * @param phone     the employee's phone
	 * @param isHR     	is employee an HR member
	 */
	public EmployeeProfile(int id, String firstName, String midName, String lastName, String email,
			String phone, boolean isHR) {
		this.setID(id);
		this.setFirstName(firstName);
		this.setMiddleName(midName);
		this.setLastName(lastName);
		this.setEmail(email);
		this.setPhone(phone);
		this.setHR(isHR);
		this.timesheets = new HashMap<LocalDate, TimeSheet>();
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
	
	/**
	 * Returns Title
	 * 
	 * @precondition none
	 * @postcondition none
	 * 
	 * @return employee's title
	 */
	
	public int getID() {
		return this.id;
	}
	
	/**
	 * Sets Employee's id
	 * 
	 * @precondition none
	 * @postcondition none
	 * 
	 * @param id employee's id
	 */
	public void setID(int id) {
		if (id < 0) {
			throw new IllegalArgumentException(EmployeeProfile.ID_CAN_NOT_BE_NEGATIVE);
		}
		this.id = id;
	}

	/**
	 * Returns HR status
	 * 
	 * @precondition none
	 * @postcondition none
	 * 
	 * @return employee hr status
	 */
	public boolean isHR() {
		return this.hr;
	}

	/**
	 * Sets HR status
	 * 
	 * @precondition none
	 * @postcondition none
	 * 
	 * @param hr the hr status
	 */
	public void setHR(boolean hr) {
		this.hr = hr;
	}

	/**
	 * Gets the time sheet for the given date
	 * 
	 * Preconditions: none
	 * Postconditions: none
	 *
	 * @param date
	 * @return the time sheet if it exists
	 */
	public TimeSheet getTimeSheet(LocalDate date) {
		LocalDate currentPeriod = PayPeriod.getStartDate(date);

		if (this.timesheets.containsKey(currentPeriod)) {
			return this.timesheets.get(currentPeriod);
		} else {
			return this.createTimesheet(currentPeriod);
		}
	}

	private TimeSheet createTimesheet(LocalDate currentPeriod) {
		TimeSheet timeSheet = new TimeSheet(currentPeriod);
		this.timesheets.put(currentPeriod, timeSheet);
		return timeSheet;
	}

}
