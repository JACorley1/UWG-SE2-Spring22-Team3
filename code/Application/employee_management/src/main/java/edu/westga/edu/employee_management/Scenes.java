package edu.westga.edu.employee_management;

/**
 * Enum for Scene names used in system
 * 
 * @author Team 3
 * @version Sprint 2
 */
public enum Scenes {
  LOGIN("LoginPage"), LANDINGPAGE("LandingPage"), HRLANDINGPAGE("HrLandingPage"), REQUESTSPAGE("EmployeeRequestsPage"), ADDREQUESTPAGE("AddRequestPage"), ADDNEWPROFILE("AddProfile"), HRREQUESTSPAGE("HrRequestsPage"), DAILYTIMEPAGE("DailyTimePage");
	private String filename;

	Scenes(String filename) {
		this.filename = filename;
	}

	/**
	 * Converts to a string object
	 * 
	 * @precondition none
	 * @postcondition none
	 * 
	 */
	@Override
	public String toString() {
		return this.filename;
	}

}
