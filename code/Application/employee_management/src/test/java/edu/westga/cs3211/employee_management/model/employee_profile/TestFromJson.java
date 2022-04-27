package edu.westga.cs3211.employee_management.model.employee_profile;

import static org.junit.jupiter.api.Assertions.*;

import org.json.JSONObject;
import org.junit.jupiter.api.Test;

import edu.westga.edu.employee_management.model.EmployeeProfile;

class TestFromJson {

	@Test
	void testFromJson() {
		EmployeeProfile profile = new EmployeeProfile(1, "Sophie", "", "Atelier", "example@gmail.com", "123-456-7890", true,
				"user name", "password");
		JSONObject jObject = profile.toJson();
		jObject.put("username", profile.getUserName());
		jObject.put("password", profile.getPassword());
		jObject.put("profile", profile.toJson());
		
		assertAll(() -> assertEquals(profile.getID(), EmployeeProfile.fromJson(jObject).getID()), 
				() -> assertEquals(profile.getEmail(), EmployeeProfile.fromJson(jObject).getEmail()),
				() -> assertEquals(profile.getFirstName(), EmployeeProfile.fromJson(jObject).getFirstName()),
				() -> assertEquals(profile.getMiddleName(), EmployeeProfile.fromJson(jObject).getMiddleName()),
				() -> assertEquals(profile.getLastName(), EmployeeProfile.fromJson(jObject).getLastName()),
				() -> assertEquals(profile.getPhone(), EmployeeProfile.fromJson(jObject).getPhone()),
				() -> assertEquals(profile.isHR(), EmployeeProfile.fromJson(jObject).isHR()));
	}

}
