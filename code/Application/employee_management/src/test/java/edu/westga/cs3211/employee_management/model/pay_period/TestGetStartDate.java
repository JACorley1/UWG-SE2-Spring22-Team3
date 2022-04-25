package edu.westga.cs3211.employee_management.model.pay_period;

import static org.junit.jupiter.api.Assertions.*;

import java.time.LocalDate;

import org.junit.jupiter.api.Test;

import edu.westga.edu.employee_management.model.EmployeeProfile;
import edu.westga.edu.employee_management.model.PayPeriod;

class TestGetStartDate {

	@Test
	void testNullDate() {
		assertThrows(IllegalArgumentException.class, () -> PayPeriod.getStartDate(null));
	}
	
	@Test
	void testValidDate() {
		PayPeriod period = new PayPeriod(LocalDate.of(2022, 4, 25));
		assertEquals(LocalDate.of(2022, 4, 24), period.getStartDate());
	}
}
