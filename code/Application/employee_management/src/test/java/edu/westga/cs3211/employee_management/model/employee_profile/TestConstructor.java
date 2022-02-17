package edu.westga.cs3211.employee_management.model.employee_profile;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

import edu.westga.edu.employee_management.model.EmployeeProfile;


class TestConstructor {

	@Test
	void testNullTitle() {
		
		assertThrows(NullPointerException.class, () -> new EmployeeProfile(null, "john", "d", "smith", "email", "phone"));
	}
	
	@Test
	void testNullFirstName() {
		
		assertThrows(NullPointerException.class, () -> new EmployeeProfile("title", null, "d", "smith", "email", "phone"));
	}
	
	@Test
	void testNullMiddleName() {
		
		assertThrows(NullPointerException.class, () -> new EmployeeProfile("title", "john", null, "smith", "email", "phone"));
	}
	
	@Test
	void testNullLastName() {
		
		assertThrows(NullPointerException.class, () -> new EmployeeProfile("title", "john", "d", null, "email", "phone"));
	}
	
	@Test
	void testNullEmail() {
		
		assertThrows(NullPointerException.class, () -> new EmployeeProfile("title", "john", "d", "smith", null, "phone"));
	}
	
	@Test
	void testNullPhone() {
		
		assertThrows(NullPointerException.class, () -> new EmployeeProfile("title", "john", "d", "smith", "email", null));
	}
	
	@Test
	void testEmptyTitle() {
		
		assertThrows(IllegalArgumentException.class, () -> new EmployeeProfile("", "john", "d", "smith", "email", "phone"));
	}
	@Test
	void testEmptyFirstName() {
		
		assertThrows(IllegalArgumentException.class, () -> new EmployeeProfile("title", "", "d", "smith", "email", "phone"));
	}
	
	@Test
	void testEmptyLastName() {
		
		assertThrows(IllegalArgumentException.class, () -> new EmployeeProfile("title", "john", "d", "", "email", "phone"));
	}
	
	@Test
	void testEmptyEmail() {
		
		assertThrows(IllegalArgumentException.class, () -> new EmployeeProfile("title", "john", "d", "smith", "", "phone"));
	}
	
	@Test
	void testvalidConstructor() {
		EmployeeProfile profile = new EmployeeProfile("title", "john", "d", "smith", "email", "phone");
		assertAll(() -> assertEquals("title", profile.getTitle()), () -> assertEquals("john", profile.getFirstName()),
				() -> assertEquals("d", profile.getMiddleName()), () -> assertEquals("smith", profile.getLastName()),
						() -> assertEquals("email", profile.getEmail()), () -> assertEquals("phone", profile.getPhone()));
	}
}
