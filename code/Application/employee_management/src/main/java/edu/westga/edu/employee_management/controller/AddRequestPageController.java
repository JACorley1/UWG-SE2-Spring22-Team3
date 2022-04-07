package edu.westga.edu.employee_management.controller;

import edu.westga.edu.employee_management.model.EmployeeRequest;
import edu.westga.edu.employee_management.model.EmployeeRequestManager;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import javafx.scene.text.Text;
import javafx.stage.Stage;

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
    
    public AddRequestPageController() {
    	this.requestManager = EmployeeRequestManager.getInstance();
    }
    
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
    	
    	EmployeeRequest newRequest = new EmployeeRequest(type, startDate, endDate, "PENDING");
    	
    	this.requestManager.addEmployeeRequest(newRequest);
    	
    	Stage stage = (Stage) this.addRequestBtn.getScene().getWindow();
        stage.close();
    	
    }

}

