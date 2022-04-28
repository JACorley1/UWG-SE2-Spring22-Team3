package edu.westga.edu.employee_management.controller;

import java.io.IOException;

import edu.westga.edu.employee_management.SceneController;
import edu.westga.edu.employee_management.Scenes;
import edu.westga.edu.employee_management.model.EmployeeRequest;
import edu.westga.edu.employee_management.model.manager.EmployeeRequestManager;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.scene.text.Text;

/**
 * Manages the data for an EmployeeRequestsPageController object
 * 
 * @author Team 3
 * @version Sprint 3
 */
public class EmployeeRequestsPageController {

	@FXML
	private ComboBox<String> requestTypeCombobox;

	@FXML
	private ComboBox<String> statusCombobox;

	@FXML
	private Button requestCreationButton;

	@FXML
	private Text requestDetailsText;

	@FXML
	private Text requestTypeText;

	@FXML
	private Text requestStatusText;

	@FXML
	private Text endDateLabel;

	@FXML
	private TextField startDateTextBox;

	@FXML
	private TextField endDateTextBox;

	@FXML
	private ListView<EmployeeRequest> confirmedRequestsListview;

	@FXML
	private Text confirmedRequestsText;

	@FXML
	private Text currentRequestsText;

	@FXML
	private ListView<EmployeeRequest> pendingRequestsListview;

	private EmployeeRequestManager requestManager;

	public EmployeeRequestsPageController() {
		this.requestManager = EmployeeRequestManager.getInstance();
	}

	/**
     * Initializes instance variables for the class
     * 
     * @precondition none
     * @postcondition none
     * 
     */
	@FXML
    public void initialize() {
		this.requestManager.getActiveEmployee().getWorkRequests().clear();
		
    	ObservableList<String> requestTypeOptions = FXCollections.observableArrayList("Vacation", "Sick Leave", "Personal Time");
    	this.requestTypeCombobox.getItems().addAll(requestTypeOptions);
    	
    	ObservableList<String> requestStatusOptions = FXCollections.observableArrayList("APPROVED", "DENIED", "PENDING");
    	this.statusCombobox.getItems().addAll(requestStatusOptions);
    	
    	for (EmployeeRequest request : this.requestManager.getConfirmedRequests()) {
    		if (this.requestManager.getActiveEmployee().equals(request.getEmployee())) {
    			this.requestManager.getActiveEmployee().getWorkRequests().add(request);
    		}
    	}
    	for (EmployeeRequest request : this.requestManager.getPendingRequests()) {
    		if (this.requestManager.getActiveEmployee().equals(request.getEmployee())) {
    			this.requestManager.getActiveEmployee().getWorkRequests().add(request);
    		}
    	}
    	
    	for (EmployeeRequest request : this.requestManager.getActiveEmployee().getWorkRequests()) {
    		if (request.getStatus().equals("PENDING")) {
    			this.pendingRequestsListview.getItems().add(request);
    		} else {
    			this.confirmedRequestsListview.getItems().add(request);
    		}
    	}
    	
    	this.confirmedRequestsListview.getSelectionModel().selectedItemProperty().addListener((obs, oldSelection, newSelection) -> {
            this.startDateTextBox.textProperty().setValue(newSelection.getStartDate());
            this.endDateTextBox.textProperty().setValue(newSelection.getEndDate());
            this.requestTypeCombobox.setValue(newSelection.getType());
            this.statusCombobox.setValue(newSelection.getStatus());
        });
    	
    	this.pendingRequestsListview.getSelectionModel().selectedItemProperty().addListener((obs, oldSelection, newSelection) -> {
            this.startDateTextBox.textProperty().setValue(newSelection.getStartDate());
            this.endDateTextBox.textProperty().setValue(newSelection.getEndDate());
            this.requestTypeCombobox.setValue(newSelection.getType());
            this.statusCombobox.setValue(newSelection.getStatus());
        });
    }

	@FXML
	void onClickRequestCreation(ActionEvent event) {
		try {
			SceneController.openMiniWindow(Scenes.ADDREQUESTPAGE, "AddRequestPage", event);
		} catch (IOException e) {
			e.printStackTrace();
		}

		this.confirmedRequestsListview.setItems(FXCollections.observableList(this.requestManager.getConfirmedRequests()));
		for (EmployeeRequest currRequest : this.requestManager.getConfirmedRequests()) {
			this.confirmedRequestsListview.getSelectionModel().select(currRequest);
		}
		this.pendingRequestsListview.setItems(FXCollections.observableList(this.requestManager.getPendingRequests()));
		for (EmployeeRequest currRequest : this.requestManager.getPendingRequests()) {
			this.pendingRequestsListview.getSelectionModel().select(currRequest);
		}
	}

}
