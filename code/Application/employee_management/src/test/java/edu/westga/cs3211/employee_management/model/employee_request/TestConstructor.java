package edu.westga.cs3211.employee_management.model.employee_request;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

import edu.westga.edu.employee_management.model.EmployeeProfile;
import edu.westga.edu.employee_management.model.EmployeeRequest;

public class TestConstructor {

	@Test
	void testNullEmployee() {
		// ASSERT
		assertThrows(IllegalArgumentException.class, () -> {
			new EmployeeRequest(null, "", "", "", "");
		});
	}
	
	@Test
	void testNullType() {
		// ASSIGN
		EmployeeProfile profile = new EmployeeProfile(1234, "john", "d", "smith", "email", "phone", true, "username", "password");
		
		// ASSERT
		assertThrows(IllegalArgumentException.class, () -> {
			new EmployeeRequest(profile, null, "", "", "");
		});
	}
	
	@Test
	void testNullStartDate() {
		// ASSIGN
		EmployeeProfile profile = new EmployeeProfile(1234, "john", "d", "smith", "email", "phone", true, "username", "password");
		
		// ASSERT
		assertThrows(IllegalArgumentException.class, () -> {
			new EmployeeRequest(profile, "", null, "", "");
		});
	}
	
	@Test
	void testNullEndDate() {
		// ASSIGN
		EmployeeProfile profile = new EmployeeProfile(1234, "john", "d", "smith", "email", "phone", true, "username", "password");
		
		// ASSERT
		assertThrows(IllegalArgumentException.class, () -> {
			new EmployeeRequest(profile, "", "", null, "");
		});
	}
	
	@Test
	void testNullStatus() {
		// ASSIGN
		EmployeeProfile profile = new EmployeeProfile(1234, "john", "d", "smith", "email", "phone", true, "username", "password");
		
		// ASSERT
		assertThrows(IllegalArgumentException.class, () -> {
			new EmployeeRequest(profile, "", "", "", null);
		});
	}
	
	@Test
	void testValidEmployeeRequest() {
		// ASSIGN
		EmployeeProfile profile = new EmployeeProfile(1234, "john", "d", "smith", "email", "phone", true, "username", "password");
		EmployeeRequest request = new EmployeeRequest(profile, "Vacation", "04/11/2022", "04/15/2022", "DENIED");

		// ASSERT
		assertAll(
				() -> assertEquals(profile, request.getEmployee()), 
				() -> assertEquals("Vacation", request.getType()),
				() -> assertEquals("04/11/2022", request.getStartDate()),
				() -> assertEquals("04/15/2022", request.getEndDate()),
				() -> assertEquals("DENIED", request.getStatus()),
				() -> assertEquals("Request Type: " + request.getType() + " Dates: " + request.getStartDate() + " - " + request.getEndDate() + "  (" + request.getStatus() + ")", request.toString() ));

	}

}
