package edu.westga.edu.employee_management.model;

/**
 * Enum for request types used in system
 * 
 * @author Team 3
 * @version Sprint 2
 */
public enum RequestType {
	PING("ping"), VERIFY_PASSWORD("verifyPassword"), ADD_USER("addUser"), UPDATE_USER("updateUser"),
	GET_PROFILES("getProfiles"),
	REMOVE_USER("removeUser");

	private String type;

	RequestType(String type) {
		this.type = type;
	}

	/**
	 * Returns the string representation of the RequestType
	 * 
	 * Preconditions: none 
	 * Postconditions: none
	 *
	 * @return string representation of the RequestType
	 */
	@Override
	public String toString() {
		return this.type;
	}
}
