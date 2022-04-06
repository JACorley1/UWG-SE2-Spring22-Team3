package edu.westga.edu.employee_management.controller;

import java.io.IOException;

import edu.westga.edu.employee_management.SceneController;
import edu.westga.edu.employee_management.Scenes;
import edu.westga.edu.employee_management.model.EmployeeRequest;
import edu.westga.edu.employee_management.model.EmployeeRequestManager;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.scene.text.Text;

public class EmployeeRequestsPageController {

    @FXML
    private ListView<EmployeeRequest> currentRequestsListview;

    @FXML
    private ComboBox<String> requestTypeCombobox;


    @FXML
    private ComboBox<String> statusCombobox;

    @FXML
    private Button requestCreationButton;

    @FXML
    private Text currentRequestsText;

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
    
    private EmployeeRequestManager requestManager;
    
    public EmployeeRequestsPageController() {
    	this.requestManager = new EmployeeRequestManager();
    }
    
    @FXML
    public void initialize() {
    	ObservableList<String> requestTypeOptions = FXCollections.observableArrayList("Vacation", "Sick Leave", "Personal Time");
    	this.requestTypeCombobox.getItems().addAll(requestTypeOptions);
    	
    	ObservableList<String> requestStatusOptions = FXCollections.observableArrayList("APPROVED", "DENIED", "PENDING");
    	this.statusCombobox.getItems().addAll(requestStatusOptions);
    	
    	for (EmployeeRequest currRequest : this.requestManager.getCurrentRequests()) {
    		this.currentRequestsListview.getItems().add(currRequest);
    	}
    	
    	this.currentRequestsListview.getSelectionModel().selectedItemProperty().addListener((obs, oldSelection, newSelection) -> {
            this.startDateTextBox.textProperty().setValue(newSelection.getStartDate());
            this.endDateTextBox.textProperty().setValue(newSelection.getEndDate());
            this.requestTypeCombobox.setValue(newSelection.getType());
            this.statusCombobox.setValue(newSelection.getStatus());
        });
    }
    
    @FXML
    void onClickRequestCreation(ActionEvent event) {
    	try {
			SceneController.openWindow(Scenes.ADDREQUESTPAGE, "AddRequestPage");
		} catch (IOException e) {
			e.printStackTrace();
		}
    }

}

