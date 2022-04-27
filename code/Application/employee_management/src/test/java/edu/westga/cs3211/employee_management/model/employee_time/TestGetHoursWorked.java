package edu.westga.cs3211.employee_management.model.employee_time;

import static org.junit.jupiter.api.Assertions.*;

import java.time.LocalDateTime;

import org.junit.jupiter.api.Test;

import edu.westga.edu.employee_management.model.EmployeeProfile;
import edu.westga.edu.employee_management.model.EmployeeTime;

class TestGetHoursWorked {

	@Test
	void testNullClockOut() {
		EmployeeTime time = new EmployeeTime(LocalDateTime.now());
		assertEquals(0, time.getHoursWorked());
	}
	
	@Test
	void testValidTime() {
		EmployeeTime time = new EmployeeTime(LocalDateTime.of(2022, 4, 25, 4, 0));
		time.setClockOutTime(LocalDateTime.of(2022, 4, 25, 5, 0));
		assertEquals(1, time.getHoursWorked());
	}
}
