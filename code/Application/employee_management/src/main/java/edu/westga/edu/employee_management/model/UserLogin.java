package edu.westga.edu.employee_management.model;

import edu.westga.edu.employee_management.model.manager.RequestManager;

/**
 * Manages data for UserLogin object
 * 
 * @author Destiny Harper
 * @version Sprint 1
 */
public class UserLogin {
	private static final String PASSWORD_CANNOT_BE_NULL = "Password cannot be null.";
	private static final String USERNAME_CANNOT_BE_NULL = "Username cannot be null.";
	private String username;
	private String password;
	
	/**
	* Manages the data of a User Login object
	*
	* @author Destiny Harper
	* @version Spring 2022
	*/
	public UserLogin() {
		this.username = "";
		this.password = "";
	}
	
	/**
	 *
	 * Creates a new instance of UserLogin
	 *
	 * Preconditions: username != null && password != null
	 * Postconditions: none
	 *
	 * @param username The username associated with the user login of an employee
	 * @param password The password associated with the user login of an employee
	 */
	public UserLogin(String username, String password) {
		if (username == null) {
			throw new IllegalArgumentException(USERNAME_CANNOT_BE_NULL);
		}
		if (password == null) {
			throw new IllegalArgumentException(PASSWORD_CANNOT_BE_NULL);
		}
		
		this.username = username;
		this.password = password;
	}
	
	/**
	 *
	 * Checks if the credentials entered at login are valid
	 *
	 * Preconditions: none
	 * Postconditions: none
	 *
	 * @return the employee's profile if credentials are valid, null otherwise
	 */
	public EmployeeProfile verifyLoginCredentials() {
		return RequestManager.verifyPassword(this.username, this.password);
	}

	/**
	 *
	 * Gets the username associated with the employee credentials
	 *
	 * Preconditions: none
	 * Postconditions: none
	 *
	 * @return the username associated with the employee credentials
	 */
	public String getUsername() {
		return this.username;
	}

	/**
	 *
	 * Gets the password associated with the employee credentials
	 *
	 * Preconditions: none
	 * Postconditions: none
	 *
	 * @return the password associated with the employee credentials
	 */
	public String getPassword() {
		return this.password;
	}
}