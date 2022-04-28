package edu.westga.cs3211.employee_management.viewmodel.login_page_view_model;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

import edu.westga.edu.employee_management.viewmodel.LoginPageViewModel;

class TestVerifyLogin {

	@Test
	void testEmptyUserName() {
		LoginPageViewModel viewmodel = new LoginPageViewModel();
		
		viewmodel.getPasswordProperty().set("");
		viewmodel.getUsernameProperty().set("");
		
		assertEquals(null, viewmodel.verifyLoginInfo());
		assertEquals("Please Input All Credentials And Try Again", viewmodel.getIncorrectCredentialsMessageProperty().get());
	}
	
	@Test
	void testEmptyPassword() {
		LoginPageViewModel viewmodel = new LoginPageViewModel();
		
		viewmodel.getPasswordProperty().set("");
		viewmodel.getUsernameProperty().set("user");
		
		assertEquals(null, viewmodel.verifyLoginInfo());
		assertEquals("Please Input All Credentials And Try Again", viewmodel.getIncorrectCredentialsMessageProperty().get());
	}

}
