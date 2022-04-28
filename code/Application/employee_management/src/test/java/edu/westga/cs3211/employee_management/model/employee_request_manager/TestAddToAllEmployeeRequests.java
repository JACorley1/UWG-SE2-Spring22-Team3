package edu.westga.cs3211.employee_management.model.employee_request_manager;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import edu.westga.edu.employee_management.model.EmployeeRequest;
import edu.westga.edu.employee_management.model.manager.EmployeeRequestManager;

public class TestAddToAllEmployeeRequests {

	@Test
	void testNullRequest() {
		// ASSIGN
		EmployeeRequestManager requestManager = EmployeeRequestManager.getInstance();

		// ASSERT
		assertThrows(IllegalArgumentException.class, () -> {
			// ACT
			requestManager.addToAllEmployeeRequests(null);
		});
	}

	@Test
	void testAddPendingRequest() {
		// ASSIGN
		EmployeeRequestManager requestManager = EmployeeRequestManager.getInstance();

		// ACT
		requestManager.setNumberOfRequests(0);
		requestManager.getAllEmployeesConfirmedRequests().clear();
		requestManager.getAllEmployeesPendingRequests().clear();

		EmployeeRequest testRequest = new EmployeeRequest("Vacation", "02/12/2001", "02/14/2001", "PENDING");
		requestManager.addToAllEmployeeRequests(testRequest);

		// ASSERT
		Assertions.assertAll(
				() -> assertEquals(0, requestManager.getNumberOfRequests()),
				() -> assertEquals(1, requestManager.getAllEmployeesPendingRequests().size()),
				() -> assertEquals(0, requestManager.getAllEmployeesConfirmedRequests().size()));

		requestManager.getAllEmployeesConfirmedRequests().clear();
		requestManager.getAllEmployeesPendingRequests().clear();

	}

	@Test
	void testAddConfirmedRequest() {
		// ASSIGN
		EmployeeRequestManager requestManager = EmployeeRequestManager.getInstance();

		// ACT
		requestManager.setNumberOfRequests(0);
		requestManager.getAllEmployeesConfirmedRequests().clear();
		requestManager.getAllEmployeesPendingRequests().clear();

		EmployeeRequest testRequest = new EmployeeRequest("Vacation", "02/12/2001", "02/14/2001", "DENIED");
		requestManager.addToAllEmployeeRequests(testRequest);

		// ASSERT
		Assertions.assertAll(
				() -> assertEquals(0, requestManager.getNumberOfRequests()),
				() -> assertEquals(0, requestManager.getAllEmployeesPendingRequests().size()),
				() -> assertEquals(1, requestManager.getAllEmployeesConfirmedRequests().size()));

		requestManager.getAllEmployeesConfirmedRequests().clear();
		requestManager.getAllEmployeesPendingRequests().clear();

	}
}
