package edu.westga.edu.employee_management.controller;

import edu.westga.edu.employee_management.model.EmployeeRequest;
import edu.westga.edu.employee_management.model.manager.EmployeeRequestManager;
import edu.westga.edu.employee_management.model.manager.RequestManager;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import javafx.scene.text.Text;
import javafx.stage.Stage;

/**
 * Manages the data for an AddRequestPageController
 * 
 * @author Team 3
 * @version Sprint 2
 */
public class AddRequestPageController {

    @FXML
    private Text newRequestLabel;

    @FXML
    private Text requestTypeLabel;

    @FXML
    private Text startDateLabel;

    @FXML
    private Text endDateLabel;

    @FXML
    private ComboBox<String> requestTypeCombobox;

    @FXML
    private TextField startDateTextbox;

    @FXML
    private TextField endDateTextbox;

    @FXML
    private Button addRequestBtn;
    
    
    private EmployeeRequestManager requestManager;
    
    /**
     * Creates a new instance of an AddRequestPageController object
     * 
     * @precondition none
     * @postcondition none
     * 
     */
    public AddRequestPageController() {
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
    	ObservableList<String> requestTypeOptions = FXCollections.observableArrayList("Vacation", "Sick Leave", "Personal Time");
    	this.requestTypeCombobox.getItems().addAll(requestTypeOptions);
    }

    @FXML
    void onClickAddRequest(ActionEvent event) {
    	String type = this.requestTypeCombobox.getSelectionModel().getSelectedItem();
    	String startDate = this.startDateTextbox.getText();
    	String endDate = this.endDateTextbox.getText();
    	
    	this.requestTypeCombobox.setValue("");
    	this.startDateTextbox.setText("");
    	this.endDateTextbox.setText("");
    	
    	EmployeeRequest newRequest = new EmployeeRequest(this.requestManager.getActiveEmployee(), type, startDate, endDate, "PENDING");
    	
    	this.requestManager.addEmployeeRequest(newRequest);
    	this.requestManager.addToAllEmployeeRequests(newRequest);
    	this.requestManager.getActiveEmployee().getWorkRequests().add(newRequest);
    	
    	RequestManager.updateUser(this.requestManager.getActiveEmployee());
    	
    	Stage stage = (Stage) this.addRequestBtn.getScene().getWindow();
        stage.close();
    	
    }

}

