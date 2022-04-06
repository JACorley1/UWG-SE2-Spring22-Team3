package edu.westga.edu.employee_management.model;

public class EmployeeRequest {
	private EmployeeProfile employee;
	
	private String type;
	private String startDate;
	private String endDate;
	private String status;
	
	public EmployeeRequest(String type, String startDate, String endDate, String status) {
		if (type == null) {
			throw new IllegalArgumentException("Request type cannot be null");
		}
		
		this.employee = employee;
		this.type = type;
		this.startDate = startDate;
		this.endDate = endDate;
		this.status = status;
	}
	
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

	public EmployeeProfile getEmployee() {
		return this.employee;
	}

	public String getType() {
		return this.type;
	}

	public String getStartDate() {
		return this.startDate;
	}

	public void setStartDate(String startDate) {
		this.startDate = startDate;
	}

	public String getEndDate() {
		return this.endDate;
	}

	public void setEndDate(String endDate) {
		this.endDate = endDate;
	}

	public String getStatus() {
		return this.status;
	}

	public void setStatus(String status) {
		this.status = status;
	}
	
	@Override
	public String toString() {
		return "Request Type: " + this.type + " Dates: " + this.startDate + " - " + this.endDate + "  (" + this.status + ")"; 
	}
	
	

}
