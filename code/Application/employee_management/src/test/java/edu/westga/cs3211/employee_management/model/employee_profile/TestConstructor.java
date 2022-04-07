package edu.westga.cs3211.employee_management.model.employee_profile;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.assertAll;

import org.junit.jupiter.api.Test;

import edu.westga.edu.employee_management.model.EmployeeProfile;


class TestConstructor {

	
	@Test
	void testNullFirstName() {
		
		assertThrows(NullPointerException.class, () -> new EmployeeProfile(1234, null, "d", "smith", "email", "phone", false, "username", "password"));
	}
	
	@Test
	void testNullMiddleName() {
		
		assertThrows(NullPointerException.class, () -> new EmployeeProfile(1234, "john", null, "smith", "email", "phone", false, "username", "password"));
	}
	
	@Test
	void testNullLastName() {
		
		assertThrows(NullPointerException.class, () -> new EmployeeProfile(1234, "john", "d", null, "email", "phone", false, "username", "password"));
	}
	
	@Test
	void testNullEmail() {
		
		assertThrows(NullPointerException.class, () -> new EmployeeProfile(1234, "john", "d", "smith", null, "phone", false, "username", "password"));
	}
	
	@Test
	void testNullPhone() {
		
		assertThrows(NullPointerException.class, () -> new EmployeeProfile(1234, "john", "d", "smith", "email", null, false, "username", "password"));
	}
	
	@Test
	void testEmptyFirstName() {
		
		assertThrows(IllegalArgumentException.class, () -> new EmployeeProfile(1234, "", "d", "smith", "email", "phone", false, "username", "password"));
	}
	
	@Test
	void testEmptyLastName() {
		
		assertThrows(IllegalArgumentException.class, () -> new EmployeeProfile(1234, "john", "d", "", "email", "phone", false, "username", "password"));
	}
	
	@Test
	void testEmptyEmail() {
		
		assertThrows(IllegalArgumentException.class, () -> new EmployeeProfile(1234, "john", "d", "smith", "", "phone", false, "username", "password"));
	}
	
	@Test
	void testNegativeID() {
		
		assertThrows(IllegalArgumentException.class, () -> new EmployeeProfile(-11234, "john", "d", "smith", "email", "phone", false, "username", "password"));
	
	}
	
	@Test
	void testNullPassword() {
		
		assertThrows(NullPointerException.class, () -> new EmployeeProfile(11234, "john", "d", "smith", "email", "phone", false, "username", null));
	
	}
	@Test
	void testNullUserName() {
		
		assertThrows(NullPointerException.class, () -> new EmployeeProfile(11234, "john", "d", "smith", "email", "phone", false, null, "password"));
	
	}
	@Test
	void testValidConstructor() {
		EmployeeProfile profile = new EmployeeProfile(1234, "john", "d", "smith", "email", "phone", true, "username", "password");
		assertAll(() -> assertEquals(1234, profile.getID()), () -> assertEquals("john", profile.getFirstName()),
				() -> assertEquals("d", profile.getMiddleName()), () -> assertEquals("smith", profile.getLastName()),
						() -> assertEquals("email", profile.getEmail()), () -> assertEquals("phone", profile.getPhone()),
						() -> assertTrue(profile.isHR()), () -> assertEquals("username", profile.getUserName()), 
						() -> assertEquals("password", profile.getPassword()));
	}
}
