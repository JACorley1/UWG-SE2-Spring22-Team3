package edu.westga.edu.employee_management.controller;

import edu.westga.edu.employee_management.model.EmployeeManager;
import edu.westga.edu.employee_management.model.EmployeeProfile;
import edu.westga.edu.employee_management.model.EmployeeRequest;
import edu.westga.edu.employee_management.model.EmployeeRequestManager;
import edu.westga.edu.employee_management.model.RequestManager;
import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleGroup;
import javafx.scene.text.Text;
import javafx.scene.control.Alert.AlertType;

public class HrRequestsPageController {
	@FXML
	private ListView<EmployeeRequest> pendingRequestsListView;

	@FXML
	private Text pendingRequestsText;

	@FXML
	private ListView<EmployeeRequest> confirmedRequestsListView;

	@FXML
	private Text confirmedRequestsText;

	@FXML
	private TextField requestTypeField;

	@FXML
	private TextField startDateField;

	@FXML
	private TextField endDateField;

	@FXML
	private TextField statusField;

	@FXML
	private RadioButton approvedRadioBtn;

	@FXML
	private ToggleGroup status;

	@FXML
	private RadioButton deniedRadioBtn;

	@FXML
	private Button submitBtn;

	private EmployeeRequestManager requestManager;
	private EmployeeManager employeeManager;

	@FXML
	void handleSubmitBtn(ActionEvent event) {
		if (this.pendingRequestsListView.getSelectionModel().getSelectedItem() != null) {
			this.pendingRequestsListView.getSelectionModel().getSelectedItem().setStatus(this.setStatus());
			this.requestManager.updateRequestsLists(this.pendingRequestsListView.getSelectionModel().getSelectedItem());
			
			RequestManager.updateUser(this.pendingRequestsListView.getSelectionModel().getSelectedItem().getEmployee());
		}
		else if (this.confirmedRequestsListView.getSelectionModel().getSelectedItem() != null) {
			this.confirmedRequestsListView.getSelectionModel().getSelectedItem().setStatus(this.setStatus());
			this.requestManager
					.updateRequestsLists(this.confirmedRequestsListView.getSelectionModel().getSelectedItem());
			
			RequestManager.updateUser(this.confirmedRequestsListView.getSelectionModel().getSelectedItem().getEmployee());
		}

		this.confirmedRequestsListView.setItems(FXCollections.observableList(requestManager.getAllEmployeesConfirmedRequests()));
		this.pendingRequestsListView.setItems(FXCollections.observableList(requestManager.getAllEmployeesPendingRequests()));
		
		
	}

	@FXML
	public void initialize() {
		this.employeeManager = EmployeeManager.getInstance();
		this.requestManager = EmployeeRequestManager.getInstance();
		
		this.requestManager.getAllEmployeesConfirmedRequests().clear();
		this.requestManager.getAllEmployeesPendingRequests().clear();
		
		for (EmployeeProfile employee : this.employeeManager.getProfiles()) {
			for (EmployeeRequest request : employee.getWorkRequests()) {
				if (request.getStatus().equals("PENDING")) {
					this.requestManager.getAllEmployeesPendingRequests().add(request);
				} else {
					this.requestManager.getAllEmployeesConfirmedRequests().add(request);
				}
			}
		}
		
		this.confirmedRequestsListView.setItems(FXCollections.observableList(requestManager.getAllEmployeesConfirmedRequests()));
		this.pendingRequestsListView.setItems(FXCollections.observableList(requestManager.getAllEmployeesPendingRequests()));

		this.confirmedRequestsListView.getSelectionModel().selectedItemProperty()
				.addListener((observable, oldValue, newValue) -> {
					if (newValue != null) {
						this.requestTypeField.setText(newValue.getType());
						this.startDateField.setText(newValue.getStartDate());
						this.endDateField.setText(newValue.getEndDate());
						this.statusField.setText(newValue.getStatus());
						if (newValue.getStatus() == "APPROVED")
							this.status.selectToggle(this.approvedRadioBtn);
						else if (newValue.getStatus() == "DENIED") {
							this.status.selectToggle(this.deniedRadioBtn);
						} else {
							this.approvedRadioBtn.setSelected(false);
							this.deniedRadioBtn.setSelected(false);
						}
					}
				});

		this.pendingRequestsListView.getSelectionModel().selectedItemProperty()
				.addListener((observable, oldValue, newValue) -> {
					if (newValue != null) {
						this.requestTypeField.setText(newValue.getType());
						this.startDateField.setText(newValue.getStartDate());
						this.endDateField.setText(newValue.getEndDate());
						this.statusField.setText(newValue.getStatus());
						if (newValue.getStatus() == "APPROVED")
							this.status.selectToggle(this.approvedRadioBtn);
						else if (newValue.getStatus() == "DENIED") {
							this.status.selectToggle(this.deniedRadioBtn);
						} else {
							this.approvedRadioBtn.setSelected(false);
							this.deniedRadioBtn.setSelected(false);
						}
					}
				});
		
	}

	private String setStatus() {

		String status = "";

		if (this.status.getSelectedToggle().equals(this.approvedRadioBtn)) {
			status = "APPROVED";
			Alert alert = new Alert(AlertType.INFORMATION);
			alert.setTitle("Success!");
			alert.setHeaderText(null);
			alert.setContentText("The request was updated!!");

			alert.showAndWait();

		} else if (this.status.getSelectedToggle().equals(this.deniedRadioBtn)) {
			status = "DENIED";
			Alert alert = new Alert(AlertType.INFORMATION);
			alert.setTitle("Success!");
			alert.setHeaderText(null);
			alert.setContentText("The request was updated!!");

			alert.showAndWait();
		} else {
			Alert alert = new Alert(AlertType.ERROR);
			alert.setTitle("FATAL!");
			alert.setHeaderText(null);
			alert.setContentText("The request was not updated, Try again");

			alert.showAndWait();
		}

		return status;

	}

}
