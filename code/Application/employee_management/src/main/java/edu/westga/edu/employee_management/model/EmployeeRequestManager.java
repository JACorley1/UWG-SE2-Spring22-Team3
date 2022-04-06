package edu.westga.edu.employee_management.model;

import java.util.ArrayList;
import java.util.List;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

public class EmployeeRequestManager {
	private static EmployeeRequestManager single_instance = null;
	
	private List<EmployeeRequest> currentRequests;
	private ObservableList<EmployeeRequest> observableList;
	private int numberOfRequests;
	
	private EmployeeRequestManager() {
		
		this.currentRequests = new ArrayList<EmployeeRequest>();
		this.currentRequests.add(new EmployeeRequest("Personal Time", "04/21/2022", "04/26/2022", "PENDING"));
		this.currentRequests.add(new EmployeeRequest("Sick Leave", "02/04/2022", "02/05/2022", "APPROVED"));
		
		this.observableList = FXCollections.observableList(this.currentRequests);
		
		this.numberOfRequests = 2;
	}
	
	public static EmployeeRequestManager getInstance() {
		if (single_instance == null)
			single_instance = new EmployeeRequestManager();

		return single_instance;
	}

	public ObservableList<EmployeeRequest> getCurrentRequestsObservable() {
		return this.observableList;
	}
	
	public List<EmployeeRequest> getCurrentRequests() {
		return this.currentRequests;
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
