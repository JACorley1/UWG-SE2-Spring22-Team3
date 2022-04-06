package edu.westga.edu.employee_management.model;

import java.util.ArrayList;
import java.util.List;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

public class EmployeeRequestManager {
	private List<EmployeeRequest> currentRequests;
	private ObservableList<EmployeeRequest> observableList;
	private int numberOfRequests;
	
	public EmployeeRequestManager() {
		
		this.currentRequests = new ArrayList<EmployeeRequest>();
		this.currentRequests.add(new EmployeeRequest("Personal Time", "04/21/2022", "04/26/2022", "PENDING"));
		this.currentRequests.add(new EmployeeRequest("Sick Leave", "02/04/2022", "02/05/2022", "APPROVED"));
		
		this.observableList = FXCollections.observableList(this.currentRequests);
		
		this.numberOfRequests = 2;
	}

	public ObservableList<EmployeeRequest> getCurrentRequests() {
		return this.observableList;
	}
	
	public int getNumberOfRequests() {
		return this.numberOfRequests;
	}

	public void setNumberOfRequests(int numberOfRequests) {
		this.numberOfRequests = numberOfRequests;
	}
	
	public boolean addEmployeeRequest(EmployeeRequest newRequest) {
		if (newRequest == null) {
			throw new IllegalArgumentException("Request cannot be null");
		}
		this.numberOfRequests += 1;
		
		return this.currentRequests.add(newRequest);
	}

}
