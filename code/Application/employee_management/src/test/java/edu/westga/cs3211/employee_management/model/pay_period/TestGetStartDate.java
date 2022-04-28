package edu.westga.cs3211.employee_management.model.pay_period;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import java.time.LocalDate;

import org.junit.jupiter.api.Test;

import edu.westga.edu.employee_management.model.PayPeriod;

class TestGetStartDate {
	@Test
	void testInvalidDateNull() {
		assertThrows(IllegalArgumentException.class, () -> {
			PayPeriod.getStartDate(null);
		});
	}

	@Test
	void testDateIsStartDate() {
		LocalDate startdate = LocalDate.of(2022, 04, 10);
		LocalDate date = PayPeriod.getStartDate(startdate);
		assertEquals(startdate, date);
	}

	@Test
	void testDateIsEndDate() {
		LocalDate startdate = LocalDate.of(2022, 04, 10);
		LocalDate enddate = LocalDate.of(2022, 04, 23);
		LocalDate date = PayPeriod.getStartDate(enddate);
		assertEquals(startdate, date);
	}

}
