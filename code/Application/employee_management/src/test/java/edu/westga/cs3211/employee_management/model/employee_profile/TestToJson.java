package edu.westga.cs3211.employee_management.model.employee_profile;

import static org.junit.jupiter.api.Assertions.*;

import org.json.JSONObject;
import org.junit.jupiter.api.Test;

import edu.westga.edu.employee_management.model.EmployeeProfile;

class TestToJson {

	@Test
	void testToJSON() {
		EmployeeProfile profile = new EmployeeProfile(1, "Sophie", "", "Atelier", "example@gmail.com", "123-456-7890", true,
					"user name", "password");
		JSONObject jObject = profile.toJson();
		assertAll(() -> assertEquals(jObject.get("id"), profile.getID()), 
				() -> assertEquals(jObject.get("firstname"), profile.getFirstName()),
				() -> assertEquals(jObject.get("middlename"), profile.getMiddleName()),
				() -> assertEquals(jObject.get("lastname"), profile.getLastName()),
				() -> assertEquals(jObject.get("email"), profile.getEmail()),
				() -> assertEquals(jObject.get("phone"), profile.getPhone()),
				() -> assertEquals(jObject.get("hr"), profile.isHR()));
		
	}

}
