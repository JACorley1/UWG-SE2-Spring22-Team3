package edu.westga.edu.employee_management.model;

public enum RequestType {
	VERIFY_PASSWORD("verifyPassword");

	private String type;

	RequestType(String type) {
		this.type = type;
	}

	@Override
	public String toString() {
		return this.type;
	}
}