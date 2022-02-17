package edu.westga.cs3211.employee_management.model.employee_time;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import java.time.LocalDateTime;

import org.junit.jupiter.api.Test;

import edu.westga.edu.employee_management.model.EmployeeTime;

class TestConstrutor {

	@Test
	void testNullClockInTime() {
		assertThrows(IllegalArgumentException.class, () -> {
			new EmployeeTime(null);
		});
	}

	@Test
	void testValidDate() {
		LocalDateTime date = LocalDateTime.of(2022, 2, 14, 9, 20);
		EmployeeTime time = new EmployeeTime(date);

		assertEquals(LocalDateTime.of(2022, 2, 14, 9, 20), time.getClockInTime());
		
	}

}
