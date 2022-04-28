package edu.westga.cs3211.employee_management.model.employee_manager;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import org.junit.jupiter.api.Test;

import edu.westga.edu.employee_management.model.manager.EmployeeManager;

class TestGetProfile {

	@Test
	void testInvalidId() {
		EmployeeManager manager = EmployeeManager.getInstance();

		assertThrows(IllegalArgumentException.class, () -> {
			manager.getProfile(-1);
		});
	}
	
	@Test
	void testGettingProfilegMultipleEmployees() {
		EmployeeManager manager = EmployeeManager.getInstance();
		
		manager.addNewEmployee(16, "juan", "A", "Jo", "@xd", "12345", false, "gatita", "hotxxx");
		manager.addNewEmployee(166, "jose", "A", "Joa", "@xd", "12345", false, "gatita", "hotxxx");
		manager.addNewEmployee(190, "lucas", "A", "J", "@xd", "12345", true, "gatita", "hotxxx");
		
		String result = manager.getProfile(16).toString();
		String expected = "Jo ID: 16 HR: No";
		
		assertEquals(expected, result, "it works!");
		manager.getProfiles().clear();
	}
}