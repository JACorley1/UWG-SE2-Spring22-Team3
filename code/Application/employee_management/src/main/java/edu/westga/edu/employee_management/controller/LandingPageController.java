package edu.westga.edu.employee_management.controller;

import java.io.IOException;

import edu.westga.edu.employee_management.SceneController;
import edu.westga.edu.employee_management.Scenes;

import edu.westga.edu.employee_management.model.EmployeeProfile;  

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.scene.text.Text;
import javafx.stage.Stage;

public class LandingPageController {

	@FXML
	private Button clockInButton;

	@FXML
	private Button clockOutButton;

	@FXML
	private Text employeeNameLabel;

	@FXML
	private Button hrViewButton;

	@FXML
	private TextField idField;

	@FXML
	private TextField firstNameField;

	@FXML
	private TextField emailField;

	@FXML
	private TextField phoneField;

	@FXML
	private TextField dateStartedField;

	@FXML
	private TextField middleNameField;

	@FXML
	private TextField lastNameField;

	@FXML
	private Text payPeriodLabel;

	@FXML
	private Button payPeriodBackButton;

	@FXML
	private Button payPeriodForwardButton;

	@FXML
	private GridPane firstWeekGrid;

	@FXML
	private GridPane secondWeekGrid;
	
	public LandingPageController() {
		
		
	}
	
	@FXML
	private void initialize() {
		EmployeeProfile profile = new EmployeeProfile(21333, "f", "m", "l", "example@gmail.com", "123-456-7890", false, "user name", "password");
		this.setUpLandingPageProfileFields(profile);
		
	}
	@FXML
	void payPeriodBack(ActionEvent event) {

	}

	@FXML
	void clockIn(ActionEvent event) {

	}

	@FXML
	void clockOut(ActionEvent event) {

	}

	@FXML
	void openHRView(ActionEvent event) {
		try {
			SceneController.openWindow(Scenes.HRLANDINGPAGE, "HR Landing Page");
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	@FXML
	void payPeriodForward(ActionEvent event) {

	}
	
	private void setUpLandingPageProfileFields(EmployeeProfile profile) {
		if (profile != null) {
			this.idField.setText(String.valueOf(profile.getID()));
			this.firstNameField.setText(profile.getFirstName());
			this.middleNameField.setText(profile.getMiddleName());
			this.lastNameField.setText(profile.getLastName());
			this.emailField.setText(profile.getEmail());
			this.phoneField.setText(profile.getPhone());
		}
	}
}

