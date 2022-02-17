package edu.westga.cs3211.employee_management.model.employee_time;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import java.time.LocalDateTime;

import org.junit.jupiter.api.Test;

import edu.westga.edu.employee_management.model.EmployeeTime;

class TestSetClockOutTime {

	@Test
	void testNullClockOutTime() {
		assertThrows(IllegalArgumentException.class, () -> {
			new EmployeeTime(null);
		});
	}

	@Test
	void testValidDate() {
		LocalDateTime clockInDate = LocalDateTime.of(2022, 2, 14, 9, 20);
		LocalDateTime clockOutDate = LocalDateTime.of(2022, 2, 14, 9, 40);
		EmployeeTime time = new EmployeeTime(clockOutDate);
		time.setClockOutTime(clockOutDate);

		assertEquals(LocalDateTime.of(2022, 2, 14, 9, 40), time.getClockInTime());

	}
}
