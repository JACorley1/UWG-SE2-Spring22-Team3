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
	private List<EmployeeProfile> profiles;

	/**
	 * Create a new instance of EmployeeManager
	 * 
	 * @precondition none
	 * @postcondition this.profiles != null
	 * 
	 */
	public EmployeeManager() {
		this.profiles = new ArrayList<EmployeeProfile>();
		this.addNewEmployee(1213, "Destiny", "A", "Harper", "gomitagodoz666@hotmail.com", "7778542369", true, "destiny",
				"harper");
		this.addNewEmployee(1312, "Brianna", "S", "Irie", "CarjotXX777@hotmail.com", "6678954563", true, "brianna",
				"irie");
		this.addNewEmployee(1112, "Fernando", "J", "Dominguez", "elverGaXXX89@hotmail.com", "8975462147", true,
				"fernando", "dominguez");
		this.addNewEmployee(1115, "Miguel", "A", "Campos", "elverGaXXX89@hotmail.com", "8975462147", false, "miguel",
				"campos");
	}

	/**
	 * Get the profile
	 * 
	 * @return the profiles on the list
	 */
	public List<EmployeeProfile> getProfiles() {
		return this.profiles;
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
	 * @param title     the employee's title
	 * @param firstName the employee's first name
	 * @param midName   the employee's middle name
	 * @param lastName  the employee's last name
	 * @param email     the employee's email
	 * @param phone     the employee's phone
	 * 
	 * @return true if employee added successfully false if employee not added
	 *         successfully
	 */
	public boolean addNewEmployee(int id, String firstName, String midName, String lastName, String email, String phone,
			boolean hrEmployee, String userName, String password) {
		if (id < 0) {
			throw new IllegalArgumentException("cannot be null or empty");
		}
		if (firstName == null || firstName.isEmpty()) {
			throw new IllegalArgumentException("cannot be null or empty");
		}
		if (midName == null || midName.isEmpty()) {
			throw new IllegalArgumentException("cannot be null or empty");
		}
		if (lastName == null || lastName.isEmpty()) {
			throw new IllegalArgumentException("cannot be null or empty");
		}
		if (email == null || email.isEmpty()) {
			throw new IllegalArgumentException("cannot be null or empty");
		}
		if (phone == null || phone.isEmpty()) {
			throw new IllegalArgumentException("cannot be null or empty");
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
	 * @param title     the employee's title
	 * @param firstName the employee's first name
	 * @param midName   the employee's middle name
	 * @param lastName  the employee's last name
	 * @param email     the employee's email
	 * @param phone     the employee's phone
	 * 
	 * @return true if profile updated successfully false if profile not updated
	 *         successfully
	 */
	public boolean updateProfile(int id, String firstName, String midName, String lastName, String email, String phone,
			boolean hrEmployee, String userName, String password) {
		if (id < 0) {
			throw new IllegalArgumentException("cannot be null or empty");
		}
		if (firstName == null || firstName.isEmpty()) {
			throw new IllegalArgumentException("cannot be null or empty");
		}
		if (midName == null || midName.isEmpty()) {
			throw new IllegalArgumentException("cannot be null or empty");
		}
		if (lastName == null || lastName.isEmpty()) {
			throw new IllegalArgumentException("cannot be null or empty");
		}
		if (email == null || email.isEmpty()) {
			throw new IllegalArgumentException("cannot be null or empty");
		}
		if (phone == null || phone.isEmpty()) {
			throw new IllegalArgumentException("cannot be null or empty");
		}
		EmployeeProfile newProfile = this.getProfile(id);

		if (newProfile == null) {
			throw new IllegalStateException("That profile does not exist");
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
	 * 
	 * @param firstName the employee's first name
	 * @param lastName  the employee's last name
	 * 
	 * @return true if profile removed successfully false if profile not removed
	 *         successfully
	 */
	public boolean removeProfile(int id, String lastName) {
		if (id < 0) {
			throw new IllegalArgumentException("cannot be null or empty");
		}
		if (lastName == null || lastName.isEmpty()) {
			throw new IllegalArgumentException("cannot be null or empty");
		}

		EmployeeProfile newProfile = this.getProfile(id);
		if (newProfile == null) {
			throw new IllegalStateException("That profile does not exist");
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