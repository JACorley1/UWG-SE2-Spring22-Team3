package edu.westga.edu.employee_management.model;

import java.util.ArrayList;
import java.util.List;

/**
 * Employee manager class
 * 
 * @author Miguel Campos
 * @version 2/17/22
 */
public class EmployeeManager {

	private static EmployeeManager single_instance = null;

	private static final String CANNOT_BE_NULL_OR_EMPTY = "cannot be null or empty";
	private static final String THAT_PROFILE_DOES_NOT_EXIST = "That profile does not exist";

	private List<EmployeeProfile> profiles;

	/**
	 * Create a new instance of EmployeeManager
	 * 
	 * @precondition none
	 * @postcondition this.profiles != null
	 * 
	 */
	private EmployeeManager() {
		this.profiles = new ArrayList<EmployeeProfile>();
	}
	
	/**
	 * Creates an instance of the EmployeeManager
	 * 
	 * @return an instance of the EmployeeManager
	 */
	public static EmployeeManager getInstance() {
		if (single_instance == null) {
			single_instance = new EmployeeManager();
		}
		 return single_instance;
	}

	/**
	 * Get the profile
	 * 
	 * @return the profiles on the list
	 */
	public List<EmployeeProfile> getProfiles() {
		return getInstance().profiles;
	}

	/**
	 * Add a new system with the specified credentials to the system
	 * 
	 * @precondition title != null && !title.isEmpty() && firstName != null &&
	 *               !firstName.isEmpty() && midName != null && !midName.isEmpty()
	 *               && lasName != null && !lasName.isEmpty() && email != null &&
	 *               !email.isEmpty() && phone != null && !phone.isEmpty()
	 * @postcondition getSystemNames().contains(firstName + " " + lasName)
	 * 
	 * @param id         the employee's id
	 * @param firstName  the employee's first name
	 * @param midName    the employee's middle name
	 * @param lastName   the employee's last name
	 * @param email      the employee's email
	 * @param password   the employee's password
	 * @param phone      the employee's phone
	 * @param userName   the employee's username
	 * @param hrEmployee if the employee is an hr user
	 * 
	 * @return true if employee added successfully false if employee not added
	 *         successfully
	 */
	public boolean addNewEmployee(int id, String firstName, String midName, String lastName, String email, String phone,
			boolean hrEmployee, String userName, String password) {
		if (id < 0) {
			throw new IllegalArgumentException(CANNOT_BE_NULL_OR_EMPTY);
		}
		if (firstName == null || firstName.isEmpty()) {
			throw new IllegalArgumentException(CANNOT_BE_NULL_OR_EMPTY);
		}
		if (midName == null || midName.isEmpty()) {
			throw new IllegalArgumentException(CANNOT_BE_NULL_OR_EMPTY);
		}
		if (lastName == null || lastName.isEmpty()) {
			throw new IllegalArgumentException(CANNOT_BE_NULL_OR_EMPTY);
		}
		if (email == null || email.isEmpty()) {
			throw new IllegalArgumentException(CANNOT_BE_NULL_OR_EMPTY);
		}
		if (phone == null || phone.isEmpty()) {
			throw new IllegalArgumentException(CANNOT_BE_NULL_OR_EMPTY);
		}
		EmployeeProfile profile = this.getProfile(id);
		if (profile == null) {
			return this.profiles.add(new EmployeeProfile(id, firstName, midName, lastName, email, phone, hrEmployee, userName, password));
		} else {
			throw new IllegalStateException("This profile already exists.");
		}

	}

	/**
	 * Update an existing profile with the specified credentials to the system
	 * 
	 * @precondition title != null && !title.isEmpty() && firstName != null &&
	 *               !firstName.isEmpty() && midName != null && !midName.isEmpty()
	 *               && lasName != null && !lasName.isEmpty() && email != null &&
	 *               !email.isEmpty() && phone != null && !phone.isEmpty() &&
	 *               getEmployeeNames().contains(firstName + " " + lasName)
	 * @postcondition getEmployeeNames().contains(firstName + " " + lasName)
	 * 
	 * @param id         the employee's id
	 * @param firstName  the employee's first name
	 * @param midName    the employee's middle name
	 * @param lastName   the employee's last name
	 * @param email      the employee's email
	 * @param phone      the employee's phone
	 * @param password   the employee's password
	 * @param userName   the employee's username
	 * @param hrEmployee if the employee is an hr user
	 * 
	 * @return true if profile updated successfully false if profile not updated
	 *         successfully
	 */
	public boolean updateProfile(int id, String firstName, String midName, String lastName, String email, String phone,
			boolean hrEmployee, String userName, String password) {
		if (id < 0) {
			throw new IllegalArgumentException(CANNOT_BE_NULL_OR_EMPTY);
		}
		if (firstName == null || firstName.isEmpty()) {
			throw new IllegalArgumentException(CANNOT_BE_NULL_OR_EMPTY);
		}
		if (midName == null || midName.isEmpty()) {
			throw new IllegalArgumentException(CANNOT_BE_NULL_OR_EMPTY);
		}
		if (lastName == null || lastName.isEmpty()) {
			throw new IllegalArgumentException(CANNOT_BE_NULL_OR_EMPTY);
		}
		if (email == null || email.isEmpty()) {
			throw new IllegalArgumentException(CANNOT_BE_NULL_OR_EMPTY);
		}
		if (phone == null || phone.isEmpty()) {
			throw new IllegalArgumentException(CANNOT_BE_NULL_OR_EMPTY);
		}
		EmployeeProfile newProfile = this.getProfile(id);

		if (newProfile == null) {
			throw new IllegalStateException(THAT_PROFILE_DOES_NOT_EXIST);
		} else if (!this.profiles.remove(newProfile)) {
			return false;
		} else {
			return this.profiles.add(new EmployeeProfile(id, firstName, midName, lastName, email, phone, hrEmployee,
					userName, password));
		}
	}

	/**
	 * Remove a profile with the specified name
	 * 
	 * @precondition firstName != null && !firstName.isEmpty() && lasName != null &&
	 *               !lasName.isEmpty()
	 * @postcondition !getEmployeeNames().contains(firstName + " " + lasName)
	 * 
	 * @param id        the employee's id
	 * @param lastName  the employee's last name
	 * 
	 * @return true if profile removed successfully false if profile not removed
	 *         successfully
	 */
	public boolean removeProfile(int id, String lastName) {
		if (id < 0) {
			throw new IllegalArgumentException(CANNOT_BE_NULL_OR_EMPTY);
		}
		if (lastName == null || lastName.isEmpty()) {
			throw new IllegalArgumentException(CANNOT_BE_NULL_OR_EMPTY);
		}

		EmployeeProfile newProfile = this.getProfile(id);
		if (newProfile == null) {
			throw new IllegalStateException(THAT_PROFILE_DOES_NOT_EXIST);
		} else {
			return this.profiles.remove(newProfile);
		}
	}

	private EmployeeProfile getProfile(int id) {
		EmployeeProfile profile = null;

		for (EmployeeProfile current : this.profiles) {
			if (current.getID() == id) {
				profile = current;
			}
		}
		return profile;
	}
}