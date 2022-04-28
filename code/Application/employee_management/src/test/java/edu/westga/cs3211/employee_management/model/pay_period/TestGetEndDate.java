package edu.westga.cs3211.employee_management.model.pay_period;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import java.time.LocalDate;

import org.junit.jupiter.api.Test;

import edu.westga.edu.employee_management.model.PayPeriod;

class TestGetEndDate {

	@Test
	void testInvalidDateNull() {
		assertThrows(IllegalArgumentException.class, () -> {
			PayPeriod.getEndDate(null);
		});
	}

	@Test
	void testDateIsStartDate() {
		LocalDate startDate = LocalDate.of(2022, 04, 10);
		LocalDate expected = LocalDate.of(2022, 04, 23);
		LocalDate actual = PayPeriod.getEndDate(startDate);
		assertEquals(expected, actual);
	}

	@Test
	void testDateIsEndDate() {
		LocalDate endDate = LocalDate.of(2022, 04, 23);
		LocalDate expected = LocalDate.of(2022, 04, 23);
		LocalDate actual = PayPeriod.getEndDate(endDate);
		assertEquals(expected, actual);
	}

}
