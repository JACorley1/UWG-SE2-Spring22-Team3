package edu.westga.cs3211.employee_management.model.user_login;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

import edu.westga.edu.employee_management.model.UserLogin;


public class TestVerifyLoginCredentials {
	
	@Test
	void testValidLoginCredentials() {
		// ASSIGN
		UserLogin testLogin = new UserLogin("destiny", "harper");
		
		// ACT
		boolean output = testLogin.verifyLoginCredentials();
		
		// ASSERT
		assertEquals(true, output);
	}
	
	@Test
	void testInvalidLoginCredentials() {
		// ASSIGN
		UserLogin testLogin = new UserLogin("chase", "1235");
		
		// ACT
		boolean output = testLogin.verifyLoginCredentials();
		
		// ASSERT
		assertEquals(false, output);
	}
}