package edu.westga.cs3211.employee_management.model.employee_Manager;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import org.junit.jupiter.api.Test;

import edu.westga.edu.employee_management.model.EmployeeManager;
import edu.westga.edu.employee_management.model.EmployeeProfile;

class TestUpdateProfile {

	@Test
	void testInvalidId() {
		EmployeeManager manager = new EmployeeManager();

		assertThrows(IllegalArgumentException.class, () -> {
			manager.updateProfile(-1, null, "A", "Jo", "@xd", "1234", false, "gatita", "hotxxx");
		});
	}
	
	@Test
	void testNullName() {
		EmployeeManager manager = new EmployeeManager();
		assertThrows(IllegalArgumentException.class, () -> {
			manager.updateProfile(12, null, "A", "Jo", "@xd", "1234", false, "gatita", "hotxxx");
		});
	}
	
	@Test
	void testEmptyName() {
		EmployeeManager manager = new EmployeeManager();
		assertThrows(IllegalArgumentException.class, () -> {
			manager.updateProfile(12, "", "A", "Jo", "@xd", "1234", false, "gatita", "hotxxx");
		});
	}

	@Test
	void testNullLastName() {
		EmployeeManager manager = new EmployeeManager();
		assertThrows(IllegalArgumentException.class, () -> {
			manager.updateProfile(12, "Juan", "A", null, "@xd", "1234", false, "gatita", "hotxxx");
		});
	}
	
	@Test
	void testEmptyLastName() {
		EmployeeManager manager = new EmployeeManager();
		assertThrows(IllegalArgumentException.class, () -> {
			manager.updateProfile(12, "Juan", "A", "", "@xd", "1234", false, "gatita", "hotxxx");
		});
	}
	
	@Test
	void testNullMidName() {
		EmployeeManager manager = new EmployeeManager();
		assertThrows(IllegalArgumentException.class, () -> {
			manager.updateProfile(12, "Juan", null, "Jo", "@xd", "1234", false, "gatita", "hotxxx");
		});
	}
	
	@Test
	void testEmptyMidName() {
		EmployeeManager manager = new EmployeeManager();
		assertThrows(IllegalArgumentException.class, () -> {
			manager.updateProfile(12, "Juan", "", "Jo", "@xd", "1234", false, "gatita", "hotxxx");
		});
	}
	
	@Test
	void testNullEmail() {
		EmployeeManager manager = new EmployeeManager();
		assertThrows(IllegalArgumentException.class, () -> {
			manager.updateProfile(12, "Juan", "A", "Jo", null, "1234", false, "gatita", "hotxxx");
		});
	}
	
	@Test
	void testEmptyEmail() {
		EmployeeManager manager = new EmployeeManager();
		assertThrows(IllegalArgumentException.class, () -> {
			manager.updateProfile(12, "Juan", "A", "Jo", "", "1234", false, "gatita", "hotxxx");
		});
	}
	
	@Test
	void testNullPhone() {
		EmployeeManager manager = new EmployeeManager();
		assertThrows(IllegalArgumentException.class, () -> {
			manager.updateProfile(12, "Juan", "A", "Jo", "@xd", null, false, "gatita", "hotxxx");
		});
	}
	
	@Test
	void testEmptyPhone() {
		EmployeeManager manager = new EmployeeManager();
		assertThrows(IllegalArgumentException.class, () -> {
			manager.updateProfile(12, "Juan", "A", "Jo", "@xd", "", false, "gatita", "hotxxx");
		});
	}
	
	@Test
	void testUpdatingAEmployeeNotExist() {
		EmployeeManager manager = new EmployeeManager();
		assertThrows(IllegalStateException.class, () -> {
			manager.updateProfile(12, "Juan", "A", "Jo", "@xd", "123", false, "gatita", "hotxxx");
		});
	}
	
	@Test
	void testUpdatingOneEmployee() {
		EmployeeManager manager = new EmployeeManager();
		manager.addNewEmployee(12, "juan", "A", "Jo", "@xd", "12345", false, "gatita", "hotxxx");
		manager.addNewEmployee(13, "jose", "A", "Joa", "@xd", "12345", false, "gatita", "hotxxx");
		manager.addNewEmployee(11, "jordi", "A", "Jwulen", "@xd", "12345", false, "gatita", "hotxxx");
		manager.updateProfile(12, "Jojo", "A", "Jo", "@xd", "12345", true, "gatita", "hotxxx");
		
		String result = manager.getProfiles().get(2).toString();
		String expected = "Jo ID: 12 HR: Yes";
		
		assertEquals(expected, result, "It works!");
	}
	
	@Test
	void testUpdatingMultipleEmployees() {
		EmployeeManager manager = new EmployeeManager();
		manager.addNewEmployee(12, "juan", "A", "Jo", "@xd", "12345", false, "gatita", "hotxxx");
		manager.addNewEmployee(13, "jose", "A", "Joa", "@xd", "12345", false, "gatita", "hotxxx");
		manager.addNewEmployee(11, "jordi", "A", "Jwulen", "@xd", "12345", false, "gatita", "hotxxx");
		manager.updateProfile(12, "Jojo", "A", "Jer", "@xd", "12345", true, "gatita", "hotxxx");
		manager.updateProfile(13, "jose", "A", "As", "@xd", "12345", true, "gatita", "hotxxx");
		manager.updateProfile(11, "jordi", "A", "Jer", "@xd", "7777", true, "gatita", "hotxxx");
		
		String result = "";
		
		for (EmployeeProfile current : manager.getProfiles()) {
			result = result + current.toString() + System.lineSeparator();
		}
		String expected = "Jer ID: 12 HR: Yes" + System.lineSeparator() + "As ID: 13 HR: Yes" + System.lineSeparator() + "Jer ID: 11 HR: Yes" + System.lineSeparator();
		
		assertEquals(expected, result, "It works!");
	}
	
	@Test
	void testUpdatingSameEmployeeMultipleTimes() {
		EmployeeManager manager = new EmployeeManager();
		manager.addNewEmployee(12, "juan", "A", "Jo", "@xd", "12345", false, "gatita", "hotxxx");
		manager.addNewEmployee(13, "jose", "A", "Joa", "@xd", "12345", false, "gatita", "hotxxx");
		manager.addNewEmployee(11, "jordi", "A", "Jwulen", "@xd", "12345", false, "gatita", "hotxxx");
		manager.updateProfile(12, "Manue", "A", "UI", "@xd", "12345", true, "gatita", "hotxxx");
		manager.updateProfile(12, "Maria", "A", "HYw", "@xd", "12345", true, "gatita", "hotxxx");
		manager.updateProfile(12, "George", "A", "Killer", "@xd", "12345", true, "gatita", "hotxxx");
		
		String result = manager.getProfiles().get(2).toString();
		String expected = "Killer ID: 12 HR: Yes";
		
		assertEquals(expected, result, "It works!");
	}
}
