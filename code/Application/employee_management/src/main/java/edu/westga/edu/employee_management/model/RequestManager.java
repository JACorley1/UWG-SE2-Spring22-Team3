package edu.westga.edu.employee_management.model;

import java.util.ArrayList;
import java.util.List;

import org.json.JSONArray;
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

	/**
	 * Gets all employee profiles from server
	 * 
	 * Preconditions: none
	 * Postconditions: none
	 *
	 * @return the employee profile
	 */
	public static List<EmployeeProfile> getProfiles() {
		Client client = new Client(RequestType.GET_PROFILES, "{}");
		List<EmployeeProfile> employees = new ArrayList<EmployeeProfile>();

		try {
			client.start();
			String response = client.sendRequest();
			JSONObject json = new JSONObject(response);
			Boolean successful = json.get("response").equals("1");
			if (successful) {
				JSONArray array = new JSONArray(json.getString("users"));
				for (Object object : array) {
					JSONObject user = (JSONObject) object;
					user.remove("timesheets");
					EmployeeProfile profile = EmployeeProfile.fromJson(user);
					employees.add(profile);
				}
			}

			return employees;
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}

	/**
	 * Removes User
	 * 
	 * Preconditions: none
	 * Postconditions: none
	 *
	 * @param username
	 * @return true if the user was remove, false otherwise
	 */
	public static boolean removeUser(String username) {
		JSONObject request = new JSONObject();
		request.put("username", username);
		Client client = new Client(RequestType.REMOVE_USER, request.toString());

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
