package edu.westga.cs3211.employee_management.model.employee_profile;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

import edu.westga.edu.employee_management.model.EmployeeProfile;


class TestConstructor {

	
	@Test
	void testNullFirstName() {
		
		assertThrows(NullPointerException.class, () -> new EmployeeProfile(1234, null, "d", "smith", "email", "phone"));
	}
	
	@Test
	void testNullMiddleName() {
		
		assertThrows(NullPointerException.class, () -> new EmployeeProfile(1234, "john", null, "smith", "email", "phone"));
	}
	
	@Test
	void testNullLastName() {
		
		assertThrows(NullPointerException.class, () -> new EmployeeProfile(1234, "john", "d", null, "email", "phone"));
	}
	
	@Test
	void testNullEmail() {
		
		assertThrows(NullPointerException.class, () -> new EmployeeProfile(1234, "john", "d", "smith", null, "phone"));
	}
	
	@Test
	void testNullPhone() {
		
		assertThrows(NullPointerException.class, () -> new EmployeeProfile(1234, "john", "d", "smith", "email", null));
	}
	
	@Test
	void testEmptyFirstName() {
		
		assertThrows(IllegalArgumentException.class, () -> new EmployeeProfile(1234, "", "d", "smith", "email", "phone"));
	}
	
	@Test
	void testEmptyLastName() {
		
		assertThrows(IllegalArgumentException.class, () -> new EmployeeProfile(1234, "john", "d", "", "email", "phone"));
	}
	
	@Test
	void testEmptyEmail() {
		
		assertThrows(IllegalArgumentException.class, () -> new EmployeeProfile(1234, "john", "d", "smith", "", "phone"));
	}
	
	@Test
	void testNegativeID() {
		
		assertThrows(IllegalArgumentException.class, () -> new EmployeeProfile(-11234, "john", "d", "smith", "email", "phone"));
	
	}
	
	@Test
	void testValidConstructor() {
		EmployeeProfile profile = new EmployeeProfile(1234, "john", "d", "smith", "email", "phone");
		assertAll(() -> assertEquals(1234, profile.getID()), () -> assertEquals("john", profile.getFirstName()),
				() -> assertEquals("d", profile.getMiddleName()), () -> assertEquals("smith", profile.getLastName()),
						() -> assertEquals("email", profile.getEmail()), () -> assertEquals("phone", profile.getPhone()));
	}
}
