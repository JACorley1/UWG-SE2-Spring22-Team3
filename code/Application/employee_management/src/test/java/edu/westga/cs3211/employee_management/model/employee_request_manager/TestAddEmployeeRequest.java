package edu.westga.cs3211.employee_management.model.employee_request_manager;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import java.util.ArrayList;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import edu.westga.edu.employee_management.model.EmployeeRequest;
import edu.westga.edu.employee_management.model.manager.EmployeeRequestManager;

public class TestAddEmployeeRequest {

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
	void testAddPendingRequest() {
		// ASSIGN
		EmployeeRequestManager requestManager = EmployeeRequestManager.getInstance();

		// ACT
		requestManager.setNumberOfRequests(0);
		requestManager.setConfirmedRequests(new ArrayList<EmployeeRequest>());
		requestManager.setPendingRequests(new ArrayList<EmployeeRequest>());

		EmployeeRequest testRequest = new EmployeeRequest("Vacation", "02/12/2001", "02/14/2001", "PENDING");
		requestManager.addEmployeeRequest(testRequest);

		// ASSERT
		Assertions.assertAll(
				() -> assertEquals(1, requestManager.getNumberOfRequests()),
				() -> assertEquals(1, requestManager.getPendingRequests().size()),
				() -> assertEquals(0, requestManager.getConfirmedRequests().size()));

		requestManager.getConfirmedRequests().clear();
		requestManager.getPendingRequests().clear();

	}
	
	@Test
	void testAddConfirmedRequest() {
		// ASSIGN
		EmployeeRequestManager requestManager = EmployeeRequestManager.getInstance();

		// ACT
		requestManager.setNumberOfRequests(0);
		requestManager.setConfirmedRequests(new ArrayList<EmployeeRequest>());
		requestManager.setPendingRequests(new ArrayList<EmployeeRequest>());

		EmployeeRequest testRequest = new EmployeeRequest("Vacation", "02/12/2001", "02/14/2001", "APPROVED");
		requestManager.addEmployeeRequest(testRequest);

		// ASSERT
		Assertions.assertAll(
				() -> assertEquals(1, requestManager.getNumberOfRequests()),
				() -> assertEquals(0, requestManager.getPendingRequests().size()),
				() -> assertEquals(1, requestManager.getConfirmedRequests().size()));

		requestManager.getConfirmedRequests().clear();
		requestManager.getPendingRequests().clear();

	}

}
