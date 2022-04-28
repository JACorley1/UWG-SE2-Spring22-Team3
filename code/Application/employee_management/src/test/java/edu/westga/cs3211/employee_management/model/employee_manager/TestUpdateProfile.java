package edu.westga.cs3211.employee_management.model.employee_manager;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import org.junit.jupiter.api.Test;

import edu.westga.edu.employee_management.model.EmployeeProfile;
import edu.westga.edu.employee_management.model.manager.EmployeeManager;

class TestUpdateProfile {

	@Test
	void testInvalidId() {
		EmployeeManager manager = EmployeeManager.getInstance();

		assertThrows(IllegalArgumentException.class, () -> {
			manager.updateProfile(-1, null, "A", "Jo", "@xd", "1234", false, "gatita", "hotxxx");
		});
	}
	
	@Test
	void testNullName() {
		EmployeeManager manager = EmployeeManager.getInstance();
		assertThrows(IllegalArgumentException.class, () -> {
			manager.updateProfile(12, null, "A", "Jo", "@xd", "1234", false, "gatita", "hotxxx");
		});
	}
	
	@Test
	void testEmptyName() {
		EmployeeManager manager = EmployeeManager.getInstance();
		assertThrows(IllegalArgumentException.class, () -> {
			manager.updateProfile(12, "", "A", "Jo", "@xd", "1234", false, "gatita", "hotxxx");
		});
	}

	@Test
	void testNullLastName() {
		EmployeeManager manager = EmployeeManager.getInstance();
		assertThrows(IllegalArgumentException.class, () -> {
			manager.updateProfile(12, "Juan", "A", null, "@xd", "1234", false, "gatita", "hotxxx");
		});
	}
	
	@Test
	void testEmptyLastName() {
		EmployeeManager manager = EmployeeManager.getInstance();
		assertThrows(IllegalArgumentException.class, () -> {
			manager.updateProfile(12, "Juan", "A", "", "@xd", "1234", false, "gatita", "hotxxx");
		});
	}
	
	@Test
	void testNullMidName() {
		EmployeeManager manager = EmployeeManager.getInstance();
		assertThrows(IllegalArgumentException.class, () -> {
			manager.updateProfile(12, "Juan", null, "Jo", "@xd", "1234", false, "gatita", "hotxxx");
		});
	}
	
	@Test
	void testEmptyMidName() {
		EmployeeManager manager = EmployeeManager.getInstance();
		assertThrows(IllegalArgumentException.class, () -> {
			manager.updateProfile(12, "Juan", "", "Jo", "@xd", "1234", false, "gatita", "hotxxx");
		});
	}
	
	@Test
	void testNullEmail() {
		EmployeeManager manager = EmployeeManager.getInstance();
		assertThrows(IllegalArgumentException.class, () -> {
			manager.updateProfile(12, "Juan", "A", "Jo", null, "1234", false, "gatita", "hotxxx");
		});
	}
	
	@Test
	void testEmptyEmail() {
		EmployeeManager manager = EmployeeManager.getInstance();
		assertThrows(IllegalArgumentException.class, () -> {
			manager.updateProfile(12, "Juan", "A", "Jo", "", "1234", false, "gatita", "hotxxx");
		});
	}
	
	@Test
	void testNullPhone() {
		EmployeeManager manager = EmployeeManager.getInstance();
		assertThrows(IllegalArgumentException.class, () -> {
			manager.updateProfile(12, "Juan", "A", "Jo", "@xd", null, false, "gatita", "hotxxx");
		});
	}
	
	@Test
	void testEmptyPhone() {
		EmployeeManager manager = EmployeeManager.getInstance();
		assertThrows(IllegalArgumentException.class, () -> {
			manager.updateProfile(12, "Juan", "A", "Jo", "@xd", "", false, "gatita", "hotxxx");
		});
	}
	
	@Test
	void testUpdatingAEmployeeNotExist() {
		EmployeeManager manager = EmployeeManager.getInstance();
		assertThrows(IllegalStateException.class, () -> {
			manager.updateProfile(12, "Juan", "A", "Jo", "@xd", "123", false, "gatita", "hotxxx");
		});
	}
	
	@Test
	void testUpdatingOneEmployee() {
		EmployeeManager manager = EmployeeManager.getInstance();
		manager.addNewEmployee(123, "juan", "A", "Jo", "@xd", "12345", false, "gatita", "hotxxx");
		manager.addNewEmployee(134, "jose", "A", "Joa", "@xd", "12345", false, "gatita", "hotxxx");
		manager.addNewEmployee(112, "jordi", "A", "Jwulen", "@xd", "12345", false, "gatita", "hotxxx");
		manager.updateProfile(123, "Jojo", "A", "Jo", "@xd", "12345", true, "gatita", "hotxxx");

		String result = manager.getProfiles().get(2).toString();

		String expected = "Jo ID: 123 HR: Yes";
		
		assertEquals(expected, result, "It works!");
		manager.getProfiles().clear();
	}
	
	@Test
	void testUpdatingMultipleEmployees() {
		EmployeeManager manager = EmployeeManager.getInstance();
		manager.addNewEmployee(1223, "juan", "A", "Jo", "@xd", "12345", false, "gatita", "hotxxx");
		manager.addNewEmployee(1334, "jose", "A", "Joa", "@xd", "12345", false, "gatita", "hotxxx");
		manager.addNewEmployee(1112, "jordi", "A", "Jwulen", "@xd", "12345", false, "gatita", "hotxxx");
		manager.updateProfile(1223, "Jojo", "A", "Jer", "@xd", "12345", true, "gatita", "hotxxx");
		manager.updateProfile(1334, "jose", "A", "As", "@xd", "12345", true, "gatita", "hotxxx");
		manager.updateProfile(1112, "jordi", "A", "Jer", "@xd", "7777", true, "gatita", "hotxxx");
		
		String result = "";
		
		for (EmployeeProfile current : manager.getProfiles()) {
			result = result + current.toString() + System.lineSeparator();
		}
		String expected = "Jer ID: 1223 HR: Yes" + System.lineSeparator() + "As ID: 1334 HR: Yes" + System.lineSeparator() + "Jer ID: 1112 HR: Yes" + System.lineSeparator();
		
		assertEquals(expected, result, "It works!");
		manager.getProfiles().clear();
	}
	
	@Test
	void testUpdatingSameEmployeeMultipleTimes() {
		EmployeeManager manager = EmployeeManager.getInstance();
		manager.addNewEmployee(1222, "juan", "A", "Jo", "@xd", "12345", false, "gatita", "hotxxx");
		manager.addNewEmployee(1333, "jose", "A", "Joa", "@xd", "12345", false, "gatita", "hotxxx");
		manager.addNewEmployee(1111, "jordi", "A", "Jwulen", "@xd", "12345", false, "gatita", "hotxxx");
		manager.updateProfile(1222, "Manue", "A", "UI", "@xd", "12345", true, "gatita", "hotxxx");
		manager.updateProfile(1222, "Maria", "A", "HYw", "@xd", "12345", true, "gatita", "hotxxx");
		manager.updateProfile(1222, "George", "A", "Killer", "@xd", "12345", true, "gatita", "hotxxx");
		
		String result = manager.getProfiles().get(2).toString();
		String expected = "Killer ID: 1222 HR: Yes";
		
		assertEquals(expected, result, "It works!");
		manager.getProfiles().clear();
	}
}
