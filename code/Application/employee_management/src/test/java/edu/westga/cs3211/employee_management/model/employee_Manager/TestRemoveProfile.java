package edu.westga.cs3211.employee_management.model.employee_Manager;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

import edu.westga.edu.employee_management.model.EmployeeManager;

class TestRemoveProfile {

	@Test
	public void testInvalidId() {
		EmployeeManager manager = EmployeeManager.getInstance();

		assertThrows(IllegalArgumentException.class, () -> {
			manager.removeProfile(-1, "Ja");
		});
	}

	@Test
	public void testNullLastName() {
		EmployeeManager manager = EmployeeManager.getInstance();
		assertThrows(IllegalArgumentException.class, () -> {
			manager.removeProfile(-1, null);
		});
	}
	
	@Test
	public void testEmptyLastName() {
		EmployeeManager manager = EmployeeManager.getInstance();
		assertThrows(IllegalArgumentException.class, () -> {
			manager.removeProfile(-1, "");
		});
	}
	
	@Test
	public void testRemovingEmployeeNotExist() {
		EmployeeManager manager = EmployeeManager.getInstance();
		assertThrows(IllegalStateException.class, () -> {
			manager.removeProfile(11, "Ja");
		});
	}
	
	@Test
	public void testRemovingEmployee() {
		EmployeeManager manager = EmployeeManager.getInstance();
		manager.addNewEmployee(12, "juan", "A", "Jo", "@xd", "12345", false, "gatita", "hotxxx");
		manager.addNewEmployee(13, "juan", "A", "Joa", "@xd", "12345", false, "gatita", "hotxxx");
		manager.addNewEmployee(11, "juan", "A", "Jwulen", "@xd", "12345", false, "gatita", "hotxxx");
		manager.removeProfile(11, "Jwulen");
		
		int result = manager.getProfiles().size();
		
		assertEquals(2, result, "It works!");
	}
	
	@Test
	public void testRemovingMultipleEmployees() {
		EmployeeManager manager = EmployeeManager.getInstance();
		manager.addNewEmployee(123, "juan", "A", "Jo", "@xd", "12345", false, "gatita", "hotxxx");
		manager.addNewEmployee(133, "juan", "A", "Joa", "@xd", "12345", false, "gatita", "hotxxx");
		manager.addNewEmployee(111, "juan", "A", "Jwulen", "@xd", "12345", false, "gatita", "hotxxx");
		manager.removeProfile(111, "Jwulen");
		manager.removeProfile(123, "Jo");
		
		int result = manager.getProfiles().size();
		
		assertEquals(3, result, "It works!");
	}

}
