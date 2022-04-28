package edu.westga.edu.employee_management.model.manager;

import java.util.ArrayList;
import java.util.List;

import edu.westga.edu.employee_management.model.EmployeeProfile;
import edu.westga.edu.employee_management.model.EmployeeRequest;

/**
 * Manages the data for the Employee Request Manager object
 * 
 * @author Destiny Harper 
 * @version Sprint 2
 *
 */
public class EmployeeRequestManager {
	private static EmployeeRequestManager single_instance = null;

	private List<EmployeeRequest> confirmedRequests;
	private List<EmployeeRequest> pendingRequests;

	private List<EmployeeRequest> allEmployeesConfirmedRequests;
	private List<EmployeeRequest> allEmployeesPendingRequests;

	private EmployeeProfile activeEmployee;

	private int numberOfRequests;

	/**
	 * Creates a private instance of EmployeeRequestManager
	 * 
	 * @precondition none
	 * @postcondition none
	 * 
	 */
	private EmployeeRequestManager() {
		this.numberOfRequests = 0;

		this.confirmedRequests = new ArrayList<EmployeeRequest>();
		this.pendingRequests = new ArrayList<EmployeeRequest>();

		this.allEmployeesConfirmedRequests = new ArrayList<EmployeeRequest>();
		this.allEmployeesPendingRequests = new ArrayList<EmployeeRequest>();
	}

	/**
	 * Gets the active employee
	 * 
	 * @precondition none
	 * @postcondition none
	 * 
	 */
	public EmployeeProfile getActiveEmployee() {
		return activeEmployee;
	}

	/**
	 * Sets the active employee
	 * 
	 * @precondition activeEmployee != null
	 * @postcondition this.getActiveEmployee() == activeEmployee
	 * 
	 */
	public void setActiveEmployee(EmployeeProfile activeEmployee) {
		this.activeEmployee = activeEmployee;
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
	 * Gets the list of confirmed employee requests
	 * 
	 * @precondition none
	 * @postcondition none
	 * 
	 * @return the list of confirmed employee requests
	 */
	public List<EmployeeRequest> getConfirmedRequests() {
		return this.confirmedRequests;
	}

	/**
	 * Gets the list of pending employee requests
	 * 
	 * @precondition none
	 * @postcondition none
	 * 
	 * @return the list of pending employee requests
	 */
	public List<EmployeeRequest> getPendingRequests() {
		return this.pendingRequests;
	}

	/**
	 * Sets the confirmed requests
	 * 
	 * @precondition confirmedRequests != null
	 * @postcondition this.getConfirmedRequests() == confirmedRequests
	 * 
	 */
	public void setConfirmedRequests(List<EmployeeRequest> confirmedRequests) {
		this.confirmedRequests = confirmedRequests;
	}

	/**
	 * Sets the pending requests
	 * 
	 * @precondition pendingRequests != null
	 * @postcondition this.getPendingRequests() == pendiongRequests
	 * 
	 */
	public void setPendingRequests(List<EmployeeRequest> pendingRequests) {
		this.pendingRequests = pendingRequests;
	}

	/**
	 * Gets the list of all employees confirmed requests
	 * 
	 * @precondition none
	 * @postcondition none
	 * 
	 * @return the list of all confirmed employee requests
	 */
	public List<EmployeeRequest> getAllEmployeesConfirmedRequests() {
		return this.allEmployeesConfirmedRequests;
	}

	/**
	 * Sets the confirmed requests for all employees
	 * 
	 * @precondition allEmployeesConfirmedRequests != null
	 * @postcondition this.getAllEmployeesConfirmedRequests() == allEmployeesConfirmedRequests
	 * 
	 */
	public void setAllEmployeesConfirmedRequests(List<EmployeeRequest> allEmployeesConfirmedRequests) {
		this.allEmployeesConfirmedRequests = allEmployeesConfirmedRequests;
	}

	/**
	 * Gets the list of all employees pending requests
	 * 
	 * @precondition none
	 * @postcondition none
	 * 
	 * @return the list of all pending employee requests
	 */
	public List<EmployeeRequest> getAllEmployeesPendingRequests() {
		return this.allEmployeesPendingRequests;
	}

	/**
	 * Sets the pending requests for all employees
	 * 
	 * @precondition allEmployeesPendingRequests != null
	 * @postcondition this.getAllEmployeesPendingRequests() == allEmployeesPendingRequests
	 * 
	 */
	public void setAllEmployeesPendingRequests(List<EmployeeRequest> allEmployeesPendingRequests) {
		this.allEmployeesPendingRequests = allEmployeesPendingRequests;
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
	 * @return true if the request was added false otherwise
	 */
	public boolean addEmployeeRequest(EmployeeRequest newRequest) {
		if (newRequest == null) {
			throw new IllegalArgumentException("Request cannot be null");
		}
		if (this.pendingRequests.contains(newRequest) || this.confirmedRequests.contains(newRequest)) {
			return false;
		} else {
			if (newRequest.getStatus().equals("PENDING")) {
				this.numberOfRequests += 1;
				return this.pendingRequests.add(newRequest);
			} else {
				this.numberOfRequests += 1;
				return this.confirmedRequests.add(newRequest);
			}
		}
	}

	/**
	 * Adds a new request to the list of all employee requests
	 * 
	 * @precondition newRequest != null
	 * @postcondition getNumberOfRequests++
	 * 
	 * @param newRequest the request to be added
	 * 
	 * @return true if the request was added false otherwise
	 */
	public boolean addToAllEmployeeRequests(EmployeeRequest newRequest) {
		if (newRequest == null) {
			throw new IllegalArgumentException("Request cannot be null");
		}

		if (newRequest.getStatus().equals("PENDING")) {
			return this.allEmployeesPendingRequests.add(newRequest);
		} else {
			return this.allEmployeesConfirmedRequests.add(newRequest);
		}

	}

	/**
	 * Updates the requests lists when the status of the given request is updated
	 * 
	 * @precondition request != null
	 * @postcondition none
	 * 
	 * @param request the request being updated
	 * 
	 */
	public void updateRequestsLists(EmployeeRequest request) {
		if (request == null) {
			throw new IllegalArgumentException("Request cannot be null");
		}

		if (this.pendingRequests.contains(request)) {
			this.confirmedRequests.add(request);
			this.pendingRequests.remove(request);
		}

	}
}
