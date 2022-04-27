package edu.westga.cs3211.employee_management.model.pay_period;

import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import java.time.LocalDate;

import org.junit.jupiter.api.Test;

import edu.westga.edu.employee_management.model.PayPeriod;

class TestGetPreviousPeriod {

	@Test
	void testInvalidPeriodNull() {
		assertThrows(IllegalArgumentException.class, () -> {
			PayPeriod.getPreviousPeriod(null);
		});
	}

	@Test
	void testNextPeriod() {
		LocalDate date = LocalDate.of(2022, 04, 10);
		PayPeriod period = new PayPeriod(date);

		PayPeriod actual = PayPeriod.getPreviousPeriod(period);
		LocalDate expectedStartDate = LocalDate.of(2022, 03, 27);
		LocalDate expectedEndDate = LocalDate.of(2022, 04, 9);

		assertAll(() -> {
			assertEquals(expectedStartDate, actual.getStartDate());
			assertEquals(expectedEndDate, actual.getEndDate());
		});
	}

}
