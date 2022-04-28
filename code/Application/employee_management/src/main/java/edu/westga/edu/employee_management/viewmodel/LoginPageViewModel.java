package edu.westga.edu.employee_management.viewmodel;

import edu.westga.edu.employee_management.model.EmployeeProfile;
import edu.westga.edu.employee_management.model.UserLogin;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

/**
 * Manages data for LoginPageViewModel object
 * 
 * @author Team 3
 * @version Sprint 1
 */
public class LoginPageViewModel {

	private StringProperty incorrectCredentialsMessageProperty;
	private StringProperty passwordProperty;
	private StringProperty usernameProperty;

	private UserLogin newLogin;

	/**
	 * Creates new instance of LoginPageViewModel
	 * 
	 * Preconditions: none
	 * Postconditions: none
	 *
	 */
	public LoginPageViewModel() {
		this.incorrectCredentialsMessageProperty = new SimpleStringProperty();
		this.passwordProperty = new SimpleStringProperty();
		this.usernameProperty = new SimpleStringProperty();
	}

	/**
	 * Verifies login credentials
	 * 
	 * Preconditions: none
	 * Postconditions: none
	 *
	 * @return true if valid and false otherwise
	 */
	public EmployeeProfile verifyLoginInfo() {
		String username = this.usernameProperty.getValue();
		String password = this.passwordProperty.getValue();
		this.newLogin = new UserLogin(username, password);

		if (username.equals("") || password.equals("")) {
			this.incorrectCredentialsMessageProperty.setValue("Please Input All Credentials And Try Again");
			return null;
		}

		EmployeeProfile user = this.newLogin.verifyLoginCredentials();
		if (user != null) {
			return user;
		} else {
			this.incorrectCredentialsMessageProperty.setValue("Incorrect Credentials. Please Try Again");
			return null;
		}
	}

	/**
	 * Gets the usernameProperty
	 * 
	 * Preconditions: none
	 * Postconditions: none
	 *
	 * @return the usernameProperty
	 */
	public StringProperty getUsernameProperty() {
		return this.usernameProperty;
	}

	/**
	 * Gets the passwordProperty
	 *
	 * Preconditions: none
	 * Postconditions: none
	 *
	 * @return the passwordProperty
	 */
	public StringProperty getPasswordProperty() {
		return this.passwordProperty;
	}

	/**
	 * Gets the incorrectCredentialsMessageProperty
	 *
	 * Preconditions: none
	 * Postconditions: none
	 *
	 * @return the incorrectCredentialsMessageProperty
	 */
	public StringProperty getIncorrectCredentialsMessageProperty() {
		return this.incorrectCredentialsMessageProperty;
	}
}
