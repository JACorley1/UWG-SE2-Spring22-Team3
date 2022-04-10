package edu.westga.edu.employee_management.viewmodel;

import edu.westga.edu.employee_management.model.UserLogin;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

public class LoginPageViewModel {

	public StringProperty incorrectCredentialsMessageProperty;
	public StringProperty passwordProperty;
	public StringProperty usernameProperty;

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
	 * Verifise login credentials
	 * 
	 * Preconditions: none
	 * Postconditions: none
	 *
	 * @return true if valid and false otherwise
	 */
	public boolean verifyLoginInfo() {
		String username = this.usernameProperty.getValue();
		String password = this.passwordProperty.getValue();
		this.newLogin = new UserLogin(username, password);

		if (username.equals("") || password.equals("")) {
			this.incorrectCredentialsMessageProperty.setValue("Please Input All Credentials And Try Again");
			return false;
		} else if (this.newLogin.verifyLoginCredentials()) {
			return true;
		} else {
			this.incorrectCredentialsMessageProperty.setValue("Incorrect Credentials. Please Try Again");
			return false;
		}
	}
}
