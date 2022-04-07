package edu.westga.edu.employee_management.model;

import java.util.ArrayList;
import java.util.List;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

/**
 * Manages the data for the Employee Request Manager object
 * @author dharpe11
 *
 */
public class EmployeeRequestManager {
	private static EmployeeRequestManager single_instance = null;
	
	private List<EmployeeRequest> currentRequests;
	private ObservableList<EmployeeRequest> observableList;
	private int numberOfRequests;
	
	/**
	 * Creates a private instance of EmployeeRequestManager
	 * 
	 * @precondition none
	 * @postcondition none
	 * 
	 */
	private EmployeeRequestManager() {
		
		this.currentRequests = new ArrayList<EmployeeRequest>();
		this.currentRequests.add(new EmployeeRequest("Personal Time", "04/21/2022", "04/26/2022", "PENDING"));
		this.currentRequests.add(new EmployeeRequest("Sick Leave", "02/04/2022", "02/05/2022", "APPROVED"));
		
		this.observableList = FXCollections.observableList(this.currentRequests);
		
		this.numberOfRequests = 2;
	}
	
	/**
	 * Creates an instance of the EmployeeRequestManager
	 * 
	 * @precondition none
	 * @postcondition none
	 * 
	 * @return an instance of the EmployeeRequestManager
	 */
	public static EmployeeRequestManager getInstance() {
		if (single_instance == null)
			single_instance = new EmployeeRequestManager();

		return single_instance;
	}
	
	/**
	 * Gets the observable list of employee requests
	 * 
	 * @precondition none
	 * @postcondition none
	 * 
	 * @return the observable list of employee requests
	 */
	public ObservableList<EmployeeRequest> getCurrentRequestsObservable() {
		return this.observableList;
	}
	
	/**
	 * Gets the list of employee requests
	 * 
	 * @precondition none
	 * @postcondition none
	 * 
	 * @return the list of employee requests
	 */
	public List<EmployeeRequest> getCurrentRequests() {
		return this.currentRequests;
	}
	
	/**
	 * Gets the number of Employees' Request
	 * 
	 * @precondition none
	 * @postcondition none
	 * 
	 * @return the number of Employees' Request
	 */
	public int getNumberOfRequests() {
		return this.numberOfRequests;
	}
	
	/**
	 * Sets the number of requests 
	 * 
	 * @precondition numberOfRequests >= 0
	 * @postcondition this.getNumberOfRequests = @prev + 1
	 * 
	 * @param numberOfRequests the number of requests
	 */
	public void setNumberOfRequests(int numberOfRequests) {
		if (numberOfRequests < 0) {
			throw new IllegalArgumentException("Number of requests must be positive.");
		}
		
		this.numberOfRequests = numberOfRequests;
	}
	
	/**
	 * Adds a new request to the list
	 * 
	 * @precondition newRequest != null
	 * @postcondition getNumberOfRequests++
	 * 
	 * @param newRequest the request to be added
	 * 
	 * @return true if the request was added
	 * 		   false otherwise
	 */
	public boolean addEmployeeRequest(EmployeeRequest newRequest) {
		if (newRequest == null) {
			throw new IllegalArgumentException("Request cannot be null");
		}
		this.numberOfRequests += 1;
		
		return this.currentRequests.add(newRequest);
	}
}
