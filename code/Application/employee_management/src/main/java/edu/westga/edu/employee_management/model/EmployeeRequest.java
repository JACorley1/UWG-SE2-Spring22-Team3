package edu.westga.edu.employee_management.model;

/**
 * Manages the data of an EmployeeRequest object 
 * 
 * @author Destiny Harper
 * @version 04/03/2022
 *
 */
public class EmployeeRequest {
	private EmployeeProfile employee;
	
	private String type;
	private String startDate;
	private String endDate;
	private String status;
	
	/**
	 * Creates an instance of EmployeeRequest
	 * 
	 * @precondition type != null
	 * @postcondition this.getType() = type && this.getStartDate() = startDate && this.getEndDate() = endDate && this.getStatus() = status
	 * 
	 * @param employee the employee that is creating the request
	 * @param type type of request
	 * @param startDate the start date of the request
	 * @param endDate the end date of the request
	 * @param status the status of the request
	 * 
	 */
	public EmployeeRequest(String type, String startDate, String endDate, String status) {
		if (type == null) {
			throw new IllegalArgumentException("Request type cannot be null");
		}
		if (startDate == null) {
			throw new IllegalArgumentException("Request start date cannot be null");
		}
		if (endDate == null) {
			throw new IllegalArgumentException("Request end date cannot be null");
		}
		if (status == null) {
			throw new IllegalArgumentException("Request status cannot be null");
		}
		
		this.type = type;
		this.startDate = startDate;
		this.endDate = endDate;
		this.status = status;
	}
	
	/**
	 * Creates an instance of EmployeeRequest
	 * 
	 * @precondition employee != null
	 * @postcondition this.getEmployee() = employee && this.getType() = type && this.getStartDate() = startDate && this.getEndDate() = endDate && this.getStatus() = status
	 * 
	 * @param employee the employee that is creating the request
	 * @param type type of request
	 * @param startDate the start date of the request
	 * @param endDate the end date of the request
	 * @param status the status of the request
	 */
	public EmployeeRequest(EmployeeProfile employee, String type, String startDate, String endDate, String status) {
		this(type, startDate, endDate, status);
		
		if (employee == null) {
			throw new IllegalArgumentException("Employee cannot be null");
		}
		this.employee = employee;
	}
	
	/**
	 * Gets the employee that made the request
	 * 
	 * @precondition none
	 * @postcondition none
	 * 
	 * @return the employee associated with the request
	 */
	public EmployeeProfile getEmployee() {
		return this.employee;
	}

	/**
	 * Gets the type of the request
	 * 
	 * @precondition none
	 * @postcondition none
	 * 
	 * @return the type associated with the request
	 */
	public String getType() {
		return this.type;
	}

	/**
	 * Gets the start date of the request
	 * 
	 * @precondition none
	 * @postcondition none
	 * 
	 * @return the start date associated with the request
	 */
	public String getStartDate() {
		return this.startDate;
	}

	/**
	 * Sets the start date of the request
	 * 
	 * @precondition startDate != null
	 * @postcondition this.getStartDate() = startDate
	 * 
	 * @param startDate the start date of the request
	 */
	public void setStartDate(String startDate) {
		if (startDate == null) {
			throw new IllegalArgumentException("Start date cannot be null.");
		}
		
		this.startDate = startDate;
	}

	/**
	 * Gets the end date of the request
	 * 
	 * @precondition none
	 * @postcondition none
	 * 
	 * @return the end date associated with the request
	 */
	public String getEndDate() {
		return this.endDate;
	}

	/**
	 * Sets the end date of the request
	 * 
	 * @precondition endDate != null
	 * @postcondition this.getEndDate() == endDate
	 * 
	 * @param endDate the end date of the request
	 */
	public void setEndDate(String endDate) {
		if (endDate == null) {
			throw new IllegalArgumentException("End date cannot be null.");
		}
		
		this.endDate = endDate;
	}

	/**
	 * Gets the status of the request
	 * 
	 * @precodnition none
	 * @postcondition none
	 * 
	 * @return the status associated with the request
	 */
	public String getStatus() {
		return this.status;
	}

	/**
	 * Set the status date of the request
	 * 
	 * @precondition status != null
	 * @postcondition this.getStatus() = status
	 * 
	 * @param status the start date of the request
	 */
	public void setStatus(String status) {
		if (status == null) {
			throw new IllegalArgumentException("Status cannot be null.");
		}
		
		this.status = status;
	}
	
	/**
	 * Returns the string representation of the EmployeeRequest object 
	 * 
	 * @precondition none
	 * @postcondition none
	 * 
	 * @return the string representation of the EmployeeRequest object 
	 */
	@Override
	public String toString() {
		return "Request Type: " + this.type + " Dates: " + this.startDate + " - " + this.endDate + "  (" + this.status + ")"; 
	}
}
