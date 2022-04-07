package edu.westga.edu.employee_management.model;

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
	 * @postcondition none
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
		
		this.type = type;
		this.startDate = startDate;
		this.endDate = endDate;
		this.status = status;
	}
	
	/**
	 * Creates an instance of EmployeeRequest
	 * 
	 * @precondition type != null && employee != null
	 * @postcondition none
	 * 
	 * @param employee the employee that is creating the request
	 * @param type type of request
	 * @param startDate the start date of the request
	 * @param endDate the end date of the request
	 * @param status the status of the request
	 */
	public EmployeeRequest(EmployeeProfile employee, String type, String startDate, String endDate, String status) {
		if (employee == null) {
			throw new IllegalArgumentException("Employee cannot be null");
		}
		if (type == null) {
			throw new IllegalArgumentException("Request type cannot be null");
		}
		
		this.employee = employee;
		this.type = type;
		this.startDate = startDate;
		this.endDate = endDate;
		this.status = status;
	}
	
	/**
	 * gets the employee that made the request
	 * 
	 * @return the employee
	 */
	public EmployeeProfile getEmployee() {
		return this.employee;
	}

	/**
	 * gets the type of the request
	 * 
	 * @return the type
	 */
	public String getType() {
		return this.type;
	}

	/**
	 * gets the start date of the request
	 * 
	 * @return the start date
	 */
	public String getStartDate() {
		return this.startDate;
	}

	/**
	 * Set the start date of the request
	 * 
	 * @param startDate the start date of the request
	 */
	public void setStartDate(String startDate) {
		this.startDate = startDate;
	}

	/**
	 * gets the end date of the request
	 * 
	 * @return the end date
	 */
	public String getEndDate() {
		return this.endDate;
	}

	/**
	 * Set the end date of the request
	 * 
	 * @param endDate the end date of the request
	 */
	public void setEndDate(String endDate) {
		this.endDate = endDate;
	}

	/**
	 * gets the status of the request
	 * 
	 * @return the status
	 */
	public String getStatus() {
		return this.status;
	}

	/**
	 * Set the status date of the request
	 * 
	 * @param status the start date of the request
	 */
	public void setStatus(String status) {
		this.status = status;
	}
	
	@Override
	public String toString() {
		return "Request Type: " + this.type + " Dates: " + this.startDate + " - " + this.endDate + "  (" + this.status + ")"; 
	}
}
