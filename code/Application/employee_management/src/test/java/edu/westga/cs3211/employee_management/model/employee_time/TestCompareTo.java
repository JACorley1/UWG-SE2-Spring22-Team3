package edu.westga.cs3211.employee_management.model.employee_time;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.time.LocalDateTime;

import org.junit.jupiter.api.Test;

import edu.westga.edu.employee_management.model.EmployeeTime;

class TestCompareTo {

	@Test
	void testLessThan() {
		LocalDateTime date = LocalDateTime.of(2022, 2, 14, 9, 20);
		EmployeeTime time = new EmployeeTime(date);
		EmployeeTime other = new EmployeeTime(date.plusDays(1));

		assertEquals(-1, time.compareTo(other));

	}

	@Test
	void testGreaterThan() {
		LocalDateTime date = LocalDateTime.of(2022, 2, 14, 9, 20);
		EmployeeTime time = new EmployeeTime(date);
		EmployeeTime other = new EmployeeTime(date.minusDays(1));

		assertEquals(1, time.compareTo(other));

	}

	@Test
	void testEqualTo() {
		LocalDateTime date = LocalDateTime.of(2022, 2, 14, 9, 20);
		EmployeeTime time = new EmployeeTime(date);
		EmployeeTime other = new EmployeeTime(date);

		assertEquals(0, time.compareTo(other));

	}

}
