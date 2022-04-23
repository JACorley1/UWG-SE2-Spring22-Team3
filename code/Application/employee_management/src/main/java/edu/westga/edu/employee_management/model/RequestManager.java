package edu.westga.edu.employee_management.model;

import org.json.JSONObject;

public class RequestManager {

	/**
	 * Verify the given password
	 * 
	 * Preconditions: none
	 * Postconditions: none
	 *
	 * @param password the password to verify
	 * @param username the username of user
	 * @return true if password is valid; false otherwise;
	 */
	public static EmployeeProfile verifyPassword(String username, String password) {
		JSONObject request = new JSONObject();
		request.put("username", username);
		request.put("password", password);
		Client client = new Client(RequestType.VERIFY_PASSWORD, request.toString());
		
		try {
			client.start();
			String response = client.sendRequest();
			JSONObject json = new JSONObject(response);
			if (json.get("isValid").equals("1")) {
				return EmployeeProfile.fromJson(new JSONObject(json.get("user").toString()));
			} else {
				return null;
			}

		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}

		
	}

	/**
	 * Adds new employee to profile
	 * 
	 * Preconditions: none
	 * Postconditions: none
	 *
	 * @param profile the profile to add
	 * @return true if employee was added, false otherwise
	 */
	public static boolean addUser(EmployeeProfile profile) {
		JSONObject request = new JSONObject();
		request.put("username", profile.getUserName());
		request.put("password", profile.getPassword());
		request.put("profile", profile.toJson());
		Client client = new Client(RequestType.ADD_USER, request.toString());

		try {
			client.start();
			String response = client.sendRequest();
			JSONObject json = new JSONObject(response);
			return json.get("response").equals("1");

		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
	}

	/**
	 * Adds new employee to profile
	 * 
	 * Preconditions: none
	 * Postconditions: none
	 *
	 * @param profile the profile to add
	 * @return true if employee was updated, false otherwise
	 */
	public static boolean updateUser(EmployeeProfile profile) {
		JSONObject request = new JSONObject();
		request.put("username", profile.getUserName());
		request.put("profile", profile.toJson());
		Client client = new Client(RequestType.UPDATE_USER, request.toString());

		try {
			client.start();
			String response = client.sendRequest();
			JSONObject json = new JSONObject(response);
			return json.get("response").equals("1");

		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
	}
}
