package edu.westga.cs3211.employee_management.model.employee_Manager;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

import edu.westga.edu.employee_management.model.EmployeeManager;

class TestRemoveProfile {

	@Test
	public void testInvalidId() {
		EmployeeManager manager = new EmployeeManager();

		assertThrows(IllegalArgumentException.class, () -> {
			manager.removeProfile(-1, "Ja");
		});
	}

	@Test
	public void testNullLastName() {
		EmployeeManager manager = new EmployeeManager();
		assertThrows(IllegalArgumentException.class, () -> {
			manager.removeProfile(-1, null);
		});
	}
	
	@Test
	public void testEmptyLastName() {
		EmployeeManager manager = new EmployeeManager();
		assertThrows(IllegalArgumentException.class, () -> {
			manager.removeProfile(-1, "");
		});
	}
	
	@Test
	public void testRemovingEmployeeNotExist() {
		EmployeeManager manager = new EmployeeManager();
		assertThrows(IllegalStateException.class, () -> {
			manager.removeProfile(11, "Ja");
		});
	}
	
	@Test
	public void testRemovingEmployee() {
		EmployeeManager manager = new EmployeeManager();
		manager.addNewEmployee(12, "juan", "A", "Jo", "@xd", "12345", false);
		manager.addNewEmployee(13, "juan", "A", "Joa", "@xd", "12345", false);
		manager.addNewEmployee(11, "juan", "A", "Jwulen", "@xd", "12345", false);
		manager.removeProfile(11, "Jwulen");
		
		int result = manager.getProfiles().size();
		
		assertEquals(2, result, "It works!");
	}
	
	@Test
	public void testRemovingMultipleEmployees() {
		EmployeeManager manager = new EmployeeManager();
		manager.addNewEmployee(12, "juan", "A", "Jo", "@xd", "12345", false);
		manager.addNewEmployee(13, "juan", "A", "Joa", "@xd", "12345", false);
		manager.addNewEmployee(11, "juan", "A", "Jwulen", "@xd", "12345", false);
		manager.removeProfile(11, "Jwulen");
		manager.removeProfile(12, "Jo");
		
		int result = manager.getProfiles().size();
		
		assertEquals(1, result, "It works!");
	}

}