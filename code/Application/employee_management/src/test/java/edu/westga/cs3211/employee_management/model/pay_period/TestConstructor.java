package edu.westga.cs3211.employee_management.model.pay_period;

import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import java.time.LocalDate;

import org.junit.jupiter.api.Test;

import edu.westga.edu.employee_management.model.PayPeriod;

class TestConstructor {

	@Test
	void testValidContruction() {
		PayPeriod period = new PayPeriod(LocalDate.of(2022, 3, 3));

		LocalDate expectedStart = LocalDate.of(2022, 2, 27);
		LocalDate expectedEnd = LocalDate.of(2022, 3, 12);

		assertAll(() -> {
			assertEquals(expectedStart, period.getStartDate());
			assertEquals(expectedEnd, period.getEndDate());
			assertEquals(14, period.toList().size());
			assertEquals("Feb 27 - Mar 12", period.toString());
		});
	}

	@Test
	void testInvalidConstructionNullDate() {
		assertThrows(IllegalArgumentException.class, () -> {
			new PayPeriod(null);
		});
	}
}
