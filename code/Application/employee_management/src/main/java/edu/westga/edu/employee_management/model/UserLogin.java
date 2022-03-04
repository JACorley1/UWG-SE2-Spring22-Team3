package edu.westga.edu.employee_management.model;

import java.util.HashMap;

public class UserLogin {
	private static final String USERNAME_CANNOT_BE_NULL = "Username cannot be null.";
	private String username;
	private String password;
	private HashMap<String, String> employeeCredentials;
	
	/**
	 * Creates new instance of UserLogin
	 * 
	 * Preconditions: none
	 * Postconditions: none
	 *
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
	 * Creates new instance of UserLogin
	 * 
	 * Preconditions: username != null && password != null
	 * Postconditions: getUsername() == username && getPassword() == password
	 *
	 * @param username the user's username
	 * @param password the user's password
	 */
	public UserLogin(String username, String password) {
		if (username == null) {
			throw new IllegalArgumentException(USERNAME_CANNOT_BE_NULL);
		}
		if (password == null) {
			throw new IllegalArgumentException(USERNAME_CANNOT_BE_NULL);
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
	 * Verifies login credentials
	 * 
	 * Preconditions: none
	 * Postconditions: none
	 *
	 * @return true if credentials match false otherwise
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
	 * Gets the username
	 * 
	 * Preconditions: none
	 * Postconditions: none
	 *
	 * @return the username
	 */
	public String getUsername() {
		return this.username;
	}

	/**
	 * Gets the password
	 * 
	 * Preconditions: none
	 * Postconditions: none
	 *
	 * @return the password
	 */
	public String getPassword() {
		return this.password;
	}

	/**
	 * Gets Employee Credentials
	 * 
	 * Preconditions: none
	 * Postconditions: none
	 *
	 * @return the employee's credentials
	 */
	public HashMap<String, String> getEmployeeCredentials() {
		return this.employeeCredentials;
	}

}