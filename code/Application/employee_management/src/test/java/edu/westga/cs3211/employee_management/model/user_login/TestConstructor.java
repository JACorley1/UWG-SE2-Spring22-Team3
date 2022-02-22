package edu.westga.cs3211.employee_management.model.user_login;

import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import org.junit.jupiter.api.Test;

import edu.westga.edu.employee_management.model.UserLogin;

public class TestConstructor {

	@Test
	void testDefaultConstructor() {
		// ASSIGN
		UserLogin testLogin = new UserLogin();

		// ASSERT
		assertAll(
				() -> assertEquals("", testLogin.getUsername()), 
				() -> assertEquals("", testLogin.getPassword()),
				() -> assertEquals(4, testLogin.getEmployeeCredentials().size()));
	}

	@Test
	void testNullUsername() {
		assertThrows(IllegalArgumentException.class, () -> {
			new UserLogin(null, "");
		});
	}

	@Test
	void testNullPassword() {
		assertThrows(IllegalArgumentException.class, () -> {
			new UserLogin("", null);
		});
	}

	@Test
	void testValidUserLogin() {
		// ASSIGN
		UserLogin testLogin = new UserLogin("destiny", "harper");

		// ASSERT
		assertAll(
				() -> assertEquals("destiny", testLogin.getUsername()), 
				() -> assertEquals("harper", testLogin.getPassword()),
				() -> assertEquals(4, testLogin.getEmployeeCredentials().size()));

	}
}