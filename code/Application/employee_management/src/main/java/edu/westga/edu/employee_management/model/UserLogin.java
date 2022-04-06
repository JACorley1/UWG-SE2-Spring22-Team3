package edu.westga.edu.employee_management.model;

import java.util.HashMap;

public class UserLogin {
	private static final String PASSWORD_CANNOT_BE_NULL = "Password cannot be null.";
	private static final String USERNAME_CANNOT_BE_NULL = "Username cannot be null.";
	private String username;
	private String password;
	private HashMap<String, String> employeeCredentials;
	
	/**
	* Manages the data of a User Login object
	*
	* @author Destiny Harper
	* @version Spring 2022
	*/
	public UserLogin() {
		this.username = "";
		this.password = "";
		
		this.employeeCredentials = new HashMap<String, String>();
		this.employeeCredentials.put("destiny", "harper");
        this.employeeCredentials.put("brianna", "irie");
        this.employeeCredentials.put("miguel", "campos");
        this.employeeCredentials.put("fernando", "dominguez");
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
		
		this.employeeCredentials = new HashMap<String, String>();
		this.employeeCredentials.put("destiny", "harper");
        this.employeeCredentials.put("brianna", "irie");
        this.employeeCredentials.put("miguel", "campos");
        this.employeeCredentials.put("fernando", "dominguez");
	}
	
	/**
	 *
	 * Checks if the credentials entered at login are valid
	 *
	 * Preconditions: none
	 * Postconditions: none
	 *
	 * @return true if credentials are valid, false otherwise
	 */
	public boolean verifyLoginCredentials() {
		
		if (this.employeeCredentials.containsKey(this.username)) {
			String correctPassword = this.employeeCredentials.get(this.username);
			if (correctPassword.equalsIgnoreCase(this.password)) {
				return true;
			}
		}
		
		return false;
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

	/**
	 *
	 * Gets the collection of employee credentials
	 *
	 * Preconditions: none
	 * Postconditions: none
	 *
	 * @return the collection of employee credentials
	 */
	public HashMap<String, String> getEmployeeCredentials() {
		return this.employeeCredentials;
	}

}