package edu.westga.edu.employee_management.controller;

import edu.westga.edu.employee_management.model.EmployeeRequest;
import edu.westga.edu.employee_management.model.EmployeeRequestManager;
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

	@FXML
	void handleSubmitBtn(ActionEvent event) {
		if (this.pendingRequestsListView.getSelectionModel().getSelectedItem() != null) {
			this.pendingRequestsListView.getSelectionModel().getSelectedItem().setStatus(this.setStatus());
			this.requestManager.updateRequestsLists(this.pendingRequestsListView.getSelectionModel().getSelectedItem());
		}
		if (this.confirmedRequestsListView.getSelectionModel().getSelectedItem() != null) {
			this.confirmedRequestsListView.getSelectionModel().getSelectedItem().setStatus(this.setStatus());
			this.requestManager
					.updateRequestsLists(this.confirmedRequestsListView.getSelectionModel().getSelectedItem());
		}

		this.confirmedRequestsListView.setItems(FXCollections.observableList(requestManager.getConfirmedRequests()));
		this.pendingRequestsListView.setItems(FXCollections.observableList(requestManager.getPendingRequests()));
	}

	@FXML
	public void initialize() {
		this.requestManager = EmployeeRequestManager.getInstance();
		this.confirmedRequestsListView.setItems(FXCollections.observableList(requestManager.getConfirmedRequests()));
		this.pendingRequestsListView.setItems(FXCollections.observableList(requestManager.getPendingRequests()));

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
