package edu.westga.cs3211.employee_management.model.employee_manager;

import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import org.junit.jupiter.api.Test;

import edu.westga.edu.employee_management.model.manager.EmployeeManager;

class TestAddNewEmployee {

	@Test
	void testInvalidId() {
		EmployeeManager manager = EmployeeManager.getInstance();
		
		assertThrows(IllegalArgumentException.class, () -> { 
			manager.addNewEmployee(-1, "Juan", "A", "Jo", "@xd", "1234", false, "gatita", "hotxxx");
		});
	}
	
	@Test
	void testEmptyName() {
		EmployeeManager manager = EmployeeManager.getInstance();
		
		assertThrows(IllegalArgumentException.class, () -> { 
			manager.addNewEmployee(12, "", "A", "Jo", "@xd", "1234", false, "gatita", "hotxxx");
		});
	}
	
	@Test
	void testNullName() {
		EmployeeManager manager = EmployeeManager.getInstance();
		
		assertThrows(IllegalArgumentException.class, () -> { 
			manager.addNewEmployee(12, null, "A", "Jo", "@xd", "1234", false, "gatita", "hotxxx");
		});
	}
	
	@Test
	void testEmptyMidName() {
		EmployeeManager manager = EmployeeManager.getInstance();
		
		assertThrows(IllegalArgumentException.class, () -> { 
			manager.addNewEmployee(12, "Juan", "", "Jo", "@xd", "1234", false, "gatita", "hotxxx");
		});
	}
	
	@Test
	void testNullMidName() {
		EmployeeManager manager = EmployeeManager.getInstance();
		
		assertThrows(IllegalArgumentException.class, () -> { 
			manager.addNewEmployee(12, "Juan", null, "Jo", "@xd", "1234", false, "gatita", "hotxxx");
		});
	}
	
	@Test
	void testEmptyLastName() {
		EmployeeManager manager = EmployeeManager.getInstance();
		
		assertThrows(IllegalArgumentException.class, () -> { 
			manager.addNewEmployee(12, "Juan", "A", "", "@xd", "1234", false, "gatita", "hotxxx");
		});
	}
	
	@Test
	void testNullLastName() {
		EmployeeManager manager = EmployeeManager.getInstance();
		
		assertThrows(IllegalArgumentException.class, () -> { 
			manager.addNewEmployee(12, "juan", "A", null, "@xd", "1234", false, "gatita", "hotxxx");
		});
	}
	
	@Test
	void testEmptyEmail() {
		EmployeeManager manager = EmployeeManager.getInstance();
		
		assertThrows(IllegalArgumentException.class, () -> { 
			manager.addNewEmployee(12, "juan", "A", "Jo", "", "1234", false, "gatita", "hotxxx");
		});
	}
	
	@Test
	void testNullEmail() {
		EmployeeManager manager = EmployeeManager.getInstance();
		
		assertThrows(IllegalArgumentException.class, () -> { 
			manager.addNewEmployee(12, "juan", "A", "Jo", null, "1234", false, "gatita", "hotxxx");
		});	
	}
	
	@Test
	void testEmptyPhone() {
		EmployeeManager manager = EmployeeManager.getInstance();
		
		assertThrows(IllegalArgumentException.class, () -> { 
			manager.addNewEmployee(12, "juan", "A", "Jo", "@xd", "", false, "gatita", "hotxxx");
		});
	}
	
	@Test
	void testNullPhone() {
		EmployeeManager manager = EmployeeManager.getInstance();
		
		assertThrows(IllegalArgumentException.class, () -> { 
			manager.addNewEmployee(12, "juan", "A", "Jo", "@xd", null, false, "gatita", "hotxxx");
		});	
	}
	
	@Test
	void testAddingOneEmployee() {
		EmployeeManager manager = EmployeeManager.getInstance();
		
		manager.addNewEmployee(14, "juan", "A", "Jo", "@xd", "12345", false, "gatita", "hotxxx");
		
		int result = manager.getProfiles().size();
		
		assertEquals(1, result, "it works!");
		manager.getProfiles().clear();
	}
	
	@Test
	void testAddingMultipleEmployees() {
		EmployeeManager manager = EmployeeManager.getInstance();
		
		manager.addNewEmployee(16, "juan", "A", "Jo", "@xd", "12345", false, "gatita", "hotxxx");
		manager.addNewEmployee(166, "jose", "A", "Joa", "@xd", "12345", false, "gatita", "hotxxx");
		manager.addNewEmployee(190, "lucas", "A", "J", "@xd", "12345", true, "gatita", "hotxxx");
		
		int result = manager.getProfiles().size();
		
		assertEquals(3, result, "it works!");
		manager.getProfiles().clear();
	}
	
	@Test
	void testAddinRepeatedEmployee() {
		EmployeeManager manager = EmployeeManager.getInstance();
		
		manager.addNewEmployee(124, "juan", "A", "Jo", "@xd", "12345", false, "gatita", "hotxxx");
		manager.addNewEmployee(137, "juan", "A", "Joa", "@xd", "12345", false, "gatita", "hotxxx");
		
		int result = manager.getProfiles().size();
		
		assertAll(() -> {
			assertThrows(IllegalStateException.class, () -> { 
				manager.addNewEmployee(124, "juan", "A", "Jo", "@xd", "12345", false, "gatita", "hotxxx");
			});
			assertEquals(2, result, "it works!");
		});	
		manager.getProfiles().clear();
	}
	
}