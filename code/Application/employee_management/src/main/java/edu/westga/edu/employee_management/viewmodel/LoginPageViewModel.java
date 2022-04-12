package edu.westga.edu.employee_management.viewmodel;

import edu.westga.edu.employee_management.model.EmployeeManager;
import edu.westga.edu.employee_management.model.UserLogin;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

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

		EmployeeManager manager = EmployeeManager.getInstance();

		manager.addNewEmployee(1213, "Destiny", "A", "Harper", "gomitagodoz666@hotmail.com", "7778542369", true,
				"destiny", "harper");
		manager.addNewEmployee(1312, "Brianna", "S", "Irie", "CarjotXX777@hotmail.com", "6678954563", true, "brianna",
				"irie");
		manager.addNewEmployee(1112, "Fernando", "J", "Dominguez", "elverGaXXX89@hotmail.com", "8975462147", true,
				"fernando", "dominguez");
		manager.addNewEmployee(1115, "Miguel", "A", "Campos", "elverGaXXX89@hotmail.com", "8975462147", false, "miguel",
				"campos");
	}

	/**
	 * Verifies login credentials
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
