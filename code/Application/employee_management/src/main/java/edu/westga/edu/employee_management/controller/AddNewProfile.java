package edu.westga.edu.employee_management.controller;

import edu.westga.edu.employee_management.model.EmployeeManager;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleGroup;

public class AddNewProfile {
	@FXML
	private TextField idTextField;

	@FXML
	private TextField firstNameTextField;

	@FXML
	private TextField middleNameTextField;

	@FXML
	private TextField lastNameTextField;

	@FXML
	private TextField emaiilTextField;

	@FXML
	private TextField phoneTextField;

	@FXML
	private TextField usernameTextField;

	@FXML
	private TextField passwordTextField;

	@FXML
	private RadioButton hrEmployeeRadioBtn;

	@FXML
	private ToggleGroup Type;

	@FXML
	private RadioButton normalEmployeeRadioBtn;

	@FXML
	private Button saveBtn;
	
	private EmployeeManager manager;
	
	@FXML
	void handleSaveBtn(ActionEvent event) {
		int idValue = Integer.valueOf(this.idTextField.getText());
		String positiveMessage = "The profile was added to the system!!, You can add another profile or close this window";
		String negativeMessage = "The profile was not added to the system, Check the information entered";		
		boolean result;
		
		try {
			result = manager.addNewEmployee(idValue, this.firstNameTextField.getText(),
					this.middleNameTextField.getText(), this.lastNameTextField.getText(),
					this.emaiilTextField.getText(), this.phoneTextField.getText(), this.radioButtonChanged(),
					this.usernameTextField.getText(), this.passwordTextField.getText());
		} catch (Exception e) {
			result = false;
		}

		this.getAlert(result, positiveMessage, negativeMessage, event);
	}
	
	@FXML
	void initialize() {
		this.manager = EmployeeManager.getInstance();
		this.idTextField.setText("");
		this.firstNameTextField.setText("");
		this.middleNameTextField.setText("");
		this.lastNameTextField.setText("");
		this.emaiilTextField.setText("");
		this.phoneTextField.setText("");
		this.idTextField.setText("");
		this.usernameTextField.setText("");
		this.passwordTextField.setText("");
	}

	private boolean radioButtonChanged() {
		boolean result = false;

		if (this.Type.getSelectedToggle().equals(this.hrEmployeeRadioBtn)) {
			result = true;
		}

		return result;	
	}

	private void getAlert(boolean result, String positiveMessage, String negativeMessage, ActionEvent event) {
		if (result) {
			Alert alert = new Alert(AlertType.INFORMATION);
			alert.setTitle("Success!");
			alert.setHeaderText(null);
			alert.setContentText(positiveMessage);

			alert.showAndWait();
			this.setAllFieldsEmpty();
		} else {
			Alert alert = new Alert(AlertType.ERROR);
			alert.setTitle("FATAL!");
			alert.setHeaderText(null);
			alert.setContentText(negativeMessage);

			alert.showAndWait();
		}
	}
	
	private void setAllFieldsEmpty() {
		this.idTextField.setText("");
		this.firstNameTextField.setText("");
		this.middleNameTextField.setText("");
		this.lastNameTextField.setText("");
		this.emaiilTextField.setText("");
		this.phoneTextField.setText("");
		this.idTextField.setText("");
		this.passwordTextField.setText("");
	}
}
