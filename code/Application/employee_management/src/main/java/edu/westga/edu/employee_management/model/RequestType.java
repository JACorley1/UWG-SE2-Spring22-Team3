package edu.westga.edu.employee_management.model;

public enum RequestType {
	PING("ping"), VERIFY_PASSWORD("verifyPassword"), ADD_USER("addUser"), UPDATE_USER("updateUser"),
	GET_PROFILES("getProfiles"),
	REMOVE_USER("removeUser");

	private String type;

	RequestType(String type) {
		this.type = type;
	}

	@Override
	public String toString() {
		return this.type;
	}
}
