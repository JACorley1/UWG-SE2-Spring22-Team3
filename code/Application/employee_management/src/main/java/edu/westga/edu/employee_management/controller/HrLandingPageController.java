package edu.westga.edu.employee_management.controller;

import java.io.IOException;

import edu.westga.edu.employee_management.SceneController;
import edu.westga.edu.employee_management.Scenes;
import edu.westga.edu.employee_management.model.EmployeeProfile;
import edu.westga.edu.employee_management.model.manager.EmployeeManager;
import edu.westga.edu.employee_management.model.manager.RequestManager;
import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleGroup;
import javafx.scene.text.Text;

/**
 * Manages the data for an HrLandingPageController object
 * 
 * @author Team 3
 * @version Sprint 1
 */
public class HrLandingPageController {

	@FXML
	private ListView<EmployeeProfile> listOfEmployeesView;

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
	private ToggleGroup type;

    @FXML
    private RadioButton normalEmployeeRadioBtn;

    @FXML
    private Button newEmployeeBtn;

    @FXML
    private Button removeEmployeeBtn;

    @FXML
    private TextField monStartTimeField;

    @FXML
    private TextField tueStartTimeField;

    @FXML
    private TextField wedStartTimeField;

    @FXML
    private TextField thuStartTimeField;

    @FXML
    private TextField friStartTimeField;

    @FXML
    private TextField satStartTimeField;

    @FXML
    private TextField sunStartTimeField;

    @FXML
    private TextField monEndTimeField;

    @FXML
    private TextField tueEndTimeField;

    @FXML
    private TextField wedEndTimeField;

    @FXML
    private TextField thuEndTimeField;

    @FXML
    private TextField friEndtimeField;

    @FXML
    private TextField satEndTimeField;

    @FXML
    private TextField sunEndTimeField;

    @FXML
    private TextField hrsWorkedTextField;

    @FXML
    private TextField PaymentTextField;

	@FXML
	private TextField paymentTextField;

	@FXML
	private Button editEmployeesInfBtn;

	@FXML
	private Button saveChangesBtn;

    @FXML
    private Text welcomeLabel;
    
    @FXML
    private Button openRequestsBtn;

	private EmployeeManager manager;

	
	private void handleDiisplayList() {
		if (!this.manager.getProfiles().isEmpty()) {
			this.listOfEmployeesView.setItems(FXCollections.observableList(this.manager.getProfiles()));
		} else {
			Alert alert = new Alert(AlertType.INFORMATION);
			alert.setTitle("Information");
			alert.setHeaderText(null);
			alert.setContentText("No employee profile to display");

			alert.showAndWait();
		}
	}
	
	@FXML
	void handleEditEmployeesInfo(ActionEvent event) {
		this.changeEditableState(true);
		this.idTextField.setEditable(false);

	}

	@FXML
	void handleRemoveEmpoyee(ActionEvent event) {
		int idValue = Integer.valueOf(this.idTextField.getText());
		String positiveMessage = "The profile was removed!, refresh the list!";
		String negativeMessage = "The profile was not removed, Try later";
		boolean result;
		try {
			result = this.manager.removeProfile(idValue, this.lastNameTextField.getText());
			RequestManager.removeUser(this.manager.getProfile(idValue).getUserName());
		} catch (Exception e) {
			result = false;
		}

		this.getAlert(result, positiveMessage, negativeMessage);
		this.setAllFieldsEmpty();
		this.refreshSystemNames();
	}

	@FXML
	void handleSaveChanges(ActionEvent event) {
		int idValue = Integer.valueOf(this.idTextField.getText());
		String positiveMessage = "The profile was updated!, refresh the list!";
		String negativeMessage = "The profile was not updated, Try later";
		boolean result;
		try {
			result = this.manager.updateProfile(idValue, this.firstNameTextField.getText(),
					this.middleNameTextField.getText(), this.lastNameTextField.getText(),
					this.emaiilTextField.getText(), this.phoneTextField.getText(), this.radioButtonChanged(),
					this.usernameTextField.getText(), this.passwordTextField.getText());
			RequestManager.updateUser(this.manager.getProfile(idValue));
		} catch (Exception e) {
			result = false;
		}

		this.getAlert(result, positiveMessage, negativeMessage);
	}
	
	@FXML
	void hnadleNewEmployee(ActionEvent event) {
		try {
			SceneController.openMiniWindow(Scenes.ADDNEWPROFILE, "Add Profile Page", event);
			
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		this.refreshSystemNames();
	}
	
    @FXML
    void handleOpenRequests(ActionEvent event) {
    	try {
    		SceneController.openWindow(Scenes.HRREQUESTSPAGE, "HrRequestsPage");
    	} catch(IOException e) {
    		e.printStackTrace();
    	}

    }

	@FXML
	void initialize() {
		this.manager = EmployeeManager.getInstance();
		this.changeEditableState(false);
		this.getInfoFromProfile();

		this.handleDiisplayList();
		this.refreshSystemNames();

	}
	
	private void refreshSystemNames() {
		this.listOfEmployeesView.setItems(FXCollections.observableList(this.manager.getProfiles()));
    }
	
	private void getInfoFromProfile() {
		this.listOfEmployeesView.getSelectionModel().selectedItemProperty()
				.addListener((observable, oldValue, newValue) -> {
					if (newValue != null) {
						this.idTextField.setText(Integer.toString(newValue.getID()));
						this.firstNameTextField.setText(newValue.getFirstName());
						this.middleNameTextField.setText(newValue.getMiddleName());
						this.lastNameTextField.setText(newValue.getLastName());
						this.emaiilTextField.setText(newValue.getEmail());
						this.phoneTextField.setText(newValue.getPhone());
						this.usernameTextField.setText(newValue.getUserName());
						this.passwordTextField.setText(newValue.getPassword());
						if (newValue.isHR()) {
							this.type.selectToggle(this.hrEmployeeRadioBtn);
						} else {
							this.type.selectToggle(this.normalEmployeeRadioBtn);
						}
					}

				});
	}

	private boolean radioButtonChanged() {
		boolean result = false;

		if (this.type.getSelectedToggle().equals(this.hrEmployeeRadioBtn)) {
			result = true;
		}

		return result;
	}

	private void getAlert(boolean result, String positiveMessage, String negativeMessage) {
		if (result) {
			Alert alert = new Alert(AlertType.INFORMATION);
			alert.setTitle("Success!");
			alert.setHeaderText(null);
			alert.setContentText(positiveMessage);

			alert.showAndWait();
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
		this.usernameTextField.setText("");
		this.passwordTextField.setText("");
	}

	private void changeEditableState(boolean state) {
		this.idTextField.setEditable(state);
		this.firstNameTextField.setEditable(state);
		this.middleNameTextField.setEditable(state);
		this.lastNameTextField.setEditable(state);
		this.emaiilTextField.setEditable(state);
		this.phoneTextField.setEditable(state);
		this.idTextField.setEditable(state);
		this.usernameTextField.setEditable(state);
		this.passwordTextField.setEditable(state);
		this.PaymentTextField.setEditable(state);
		this.hrsWorkedTextField.setEditable(state);
		this.monStartTimeField.setEditable(state);
		this.tueStartTimeField.setEditable(state);
		this.wedStartTimeField.setEditable(state);
		this.thuStartTimeField.setEditable(state);
		this.friStartTimeField.setEditable(state);
		this.satStartTimeField.setEditable(state);
		this.sunStartTimeField.setEditable(state);
		this.monEndTimeField.setEditable(state);
		this.tueEndTimeField.setEditable(state);
		this.wedEndTimeField.setEditable(state);
		this.thuEndTimeField.setEditable(state);
		this.friEndtimeField.setEditable(state);
		this.satEndTimeField.setEditable(state);
		this.sunEndTimeField.setEditable(state);
	}
}