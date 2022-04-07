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
	public static boolean verifyPassword(String username, String password) {
		JSONObject request = new JSONObject();
		request.put("username", username);
		request.put("password", password);
		Client client = new Client(RequestType.VERIFY_PASSWORD, request.toString());
		client.start();
		
		String response = client.sendRequest();
		try {
			JSONObject json = new JSONObject(response);
			return json.get("isValid").equals("1");

		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}

		
	}
}
