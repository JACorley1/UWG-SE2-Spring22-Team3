package edu.westga.edu.employee_management.model;

import java.util.HashMap;

public class UserLogin {
	private String username;
	private String password;
	private HashMap<String, String> employeeCredentials;
	
	public UserLogin() {
		this.username = "";
		this.password = "";
		
		this.employeeCredentials = new HashMap<String, String>();
		this.employeeCredentials.put("destiny", "harper");
        this.employeeCredentials.put("brianna", "irie");
        this.employeeCredentials.put("miguel", "campos");
        this.employeeCredentials.put("fernando", "dominguez");
	}
	
	public UserLogin(String username, String password) {
		if (username == null) {
			throw new IllegalArgumentException("Username cannot be null.");
		}
		if (password == null) {
			throw new IllegalArgumentException("Username cannot be null.");
		}
		
		this.username = username;
		this.password = password;
		
		this.employeeCredentials = new HashMap<String, String>();
		this.employeeCredentials.put("destiny", "harper");
        this.employeeCredentials.put("brianna", "irie");
        this.employeeCredentials.put("miguel", "campos");
        this.employeeCredentials.put("fernando", "dominguez");
	}
	
	public boolean verifyLoginCredentials() {
		
		if (this.employeeCredentials.containsKey(this.username)) {
			String correctPassword = this.employeeCredentials.get(this.username);
			if (correctPassword.equalsIgnoreCase(this.password)) {
				return true;
			}
		}
		
		return false;
	}

	public String getUsername() {
		return this.username;
	}

	public String getPassword() {
		return this.password;
	}

	public HashMap<String, String> getEmployeeCredentials() {
		return this.employeeCredentials;
	}

}