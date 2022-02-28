package edu.westga.cs3211.employee_management.model.employee_Manager;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

import edu.westga.edu.employee_management.model.EmployeeManager;

class TestAddNewEmployee {

	@Test
	void testInvalidId() {
		EmployeeManager manager = new EmployeeManager();
		
		assertThrows(IllegalArgumentException.class, () -> { 
			manager.addNewEmployee(-1, "Juan", "A", "Jo", "@xd", "1234", false);
		});
	}
	
	@Test
	void testEmptyName() {
		EmployeeManager manager = new EmployeeManager();
		
		assertThrows(IllegalArgumentException.class, () -> { 
			manager.addNewEmployee(12, "", "A", "Jo", "@xd", "1234", false);
		});
	}
	
	@Test
	void testNullName() {
		EmployeeManager manager = new EmployeeManager();
		
		assertThrows(IllegalArgumentException.class, () -> { 
			manager.addNewEmployee(12, null, "A", "Jo", "@xd", "1234", false);
		});
	}
	
	@Test
	void testEmptyMidName() {
		EmployeeManager manager = new EmployeeManager();
		
		assertThrows(IllegalArgumentException.class, () -> { 
			manager.addNewEmployee(12, "Juan", "", "Jo", "@xd", "1234", false);
		});
	}
	
	@Test
	void testNullMidName() {
		EmployeeManager manager = new EmployeeManager();
		
		assertThrows(IllegalArgumentException.class, () -> { 
			manager.addNewEmployee(12, "Juan", null, "Jo", "@xd", "1234", false);
		});
	}
	
	@Test
	void testEmptyLastName() {
		EmployeeManager manager = new EmployeeManager();
		
		assertThrows(IllegalArgumentException.class, () -> { 
			manager.addNewEmployee(12, "Juan", "", "Jo", "@xd", "1234", false);
		});
	}
	
	@Test
	void testNullLastName() {
		EmployeeManager manager = new EmployeeManager();
		
		assertThrows(IllegalArgumentException.class, () -> { 
			manager.addNewEmployee(12, "juan", null, "Jo", "@xd", "1234", false);
		});
	}
	
	@Test
	void testEmptyEmail() {
		EmployeeManager manager = new EmployeeManager();
		
		assertThrows(IllegalArgumentException.class, () -> { 
			manager.addNewEmployee(12, "juan", "A", "Jo", "", "1234", false);
		});
	}
	
	@Test
	void testNullEmail() {
		EmployeeManager manager = new EmployeeManager();
		
		assertThrows(IllegalArgumentException.class, () -> { 
			manager.addNewEmployee(12, "juan", "A", "Jo", null, "1234", false);
		});	
	}
	
	@Test
	void testEmptyPhone() {
		EmployeeManager manager = new EmployeeManager();
		
		assertThrows(IllegalArgumentException.class, () -> { 
			manager.addNewEmployee(12, "juan", "A", "Jo", "@xd", "", false);
		});
	}
	
	@Test
	void testNullPhone() {
		EmployeeManager manager = new EmployeeManager();
		
		assertThrows(IllegalArgumentException.class, () -> { 
			manager.addNewEmployee(12, "juan", "A", "Jo", "@xd", null, false);
		});	
	}
	
	@Test
	void testAddingOneEmployee() {
		EmployeeManager manager = new EmployeeManager();
		
		manager.addNewEmployee(12, "juan", "A", "Jo", "@xd", "12345", false);
		
		int result = manager.getProfiles().size();
		
		assertEquals(1, result, "it works!");
	}
	
	@Test
	void testAddingMultipleEmployees() {
		EmployeeManager manager = new EmployeeManager();
		
		manager.addNewEmployee(12, "juan", "A", "Jo", "@xd", "12345", false);
		manager.addNewEmployee(13, "juan", "A", "Joa", "@xd", "12345", false);
		manager.addNewEmployee(11, "juan", "A", "J", "@xd", "12345", true);
		
		int result = manager.getProfiles().size();
		
		assertEquals(3, result, "it works!");
	}
	
	@Test
	void testAddinRepeatedEmployee() {
		EmployeeManager manager = new EmployeeManager();
		
		manager.addNewEmployee(12, "juan", "A", "Jo", "@xd", "12345", false);
		manager.addNewEmployee(13, "juan", "A", "Joa", "@xd", "12345", false);
		
		int result = manager.getProfiles().size();
		
		assertAll(() -> {
			assertThrows(IllegalStateException.class, () -> { 
				manager.addNewEmployee(12, "juan", "A", "Jo", "@xd", "12345", false);
			});
			assertEquals(2, result, "it works!");
		});	
	}
	
}