package edu.westga.cs3211.employee_management.model.employee_request_manager;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import edu.westga.edu.employee_management.model.EmployeeRequest;
import edu.westga.edu.employee_management.model.manager.EmployeeRequestManager;

public class TestUpdateRequestsLists {

	@Test
	void testNullRequest() {
		// ASSIGN
		EmployeeRequestManager requestManager = EmployeeRequestManager.getInstance();

		// ASSERT
		assertThrows(IllegalArgumentException.class, () -> {
			// ACT
			requestManager.addEmployeeRequest(null);
		});
	}
	
	@Test
	void testUpdateRequestLists() {
		// ASSIGN
		EmployeeRequestManager requestManager = EmployeeRequestManager.getInstance();

		// ACT
		requestManager.setNumberOfRequests(0);
		requestManager.getAllEmployeesConfirmedRequests().clear();
		requestManager.getAllEmployeesPendingRequests().clear();

		EmployeeRequest testRequest = new EmployeeRequest("Vacation", "02/12/2001", "02/14/2001", "PENDING");
		requestManager.addToAllEmployeeRequests(testRequest);
		
		testRequest.setStatus("APPROVED");
		
		requestManager.updateRequestsLists(testRequest);

		// ASSERT
		Assertions.assertAll(
				() -> assertEquals(1, requestManager.getAllEmployeesPendingRequests().size()),
				() -> assertEquals(0, requestManager.getAllEmployeesConfirmedRequests().size()));

		requestManager.getAllEmployeesConfirmedRequests().clear();
		requestManager.getAllEmployeesPendingRequests().clear();

	}

}
