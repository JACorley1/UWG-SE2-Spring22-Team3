package edu.westga.cs3211.employee_management.model.employee_time;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import java.time.LocalDateTime;

import org.junit.jupiter.api.Test;

import edu.westga.edu.employee_management.model.EmployeeTime;

class TestSetClockInTime {

	@Test
	void testNullClockOutTime() {
		LocalDateTime clockInDate = LocalDateTime.of(2022, 2, 14, 9, 20);
		EmployeeTime time = new EmployeeTime(clockInDate);
		assertThrows(IllegalArgumentException.class, () -> {
			time.setClockInTime(null);
		});
	}

	@Test
	void testValidDate() {
		LocalDateTime originalClockInDate = LocalDateTime.of(2022, 2, 14, 9, 20);
		LocalDateTime newclockInDate = LocalDateTime.of(2022, 2, 14, 9, 40);
		EmployeeTime time = new EmployeeTime(originalClockInDate);
		time.setClockInTime(newclockInDate);

		assertEquals(LocalDateTime.of(2022, 2, 14, 9, 40), time.getClockInTime());

	}

}
