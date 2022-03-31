package edu.westga.edu.employee_management;

public enum Scenes {
	LOGIN("LoginPage"), LANDINGPAGE("LandingPage"), HRLANDINGPAGE("HrLandingPage"), REQUESTSPAGE("EmployeeRequestsPage");

	private String filename;

	Scenes(String filename) {
		this.filename = filename;
	}

	@Override
	public String toString() {
		return this.filename;
	}

}
