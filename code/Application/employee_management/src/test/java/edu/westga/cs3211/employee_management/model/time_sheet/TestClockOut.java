package edu.westga.cs3211.employee_management.model.time_sheet;

import static org.junit.Assert.assertFalse;
import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.time.LocalDate;
import java.time.Period;

import org.junit.jupiter.api.Test;

import edu.westga.edu.employee_management.model.TimeSheet;

class TestClockOut {

	@Test
	void testValidClockOut() {
		LocalDate period = LocalDate.now();
		TimeSheet sheet = new TimeSheet(period);
		sheet.clockIn();
		assertAll(() -> {
			assertTrue(sheet.clockOut());
			assertEquals(1, sheet.getTimeSheet().size());
			assertFalse(sheet.hasOpenTime());
		});
	}

	@Test
	void testInvalidClockOutNoOpenTime() {
		LocalDate period = LocalDate.now();
		TimeSheet sheet = new TimeSheet(period);
		assertAll(() -> {
			assertFalse(sheet.clockOut());
			assertEquals(0, sheet.getTimeSheet().size());
		});
	}

	@Test
	void testInvalidClockOutAfterPeriodEnd() {
		LocalDate period = LocalDate.now().minus(Period.ofWeeks(3));
		TimeSheet sheet = new TimeSheet(period);
		assertAll(() -> {
			assertFalse(sheet.clockIn());
			assertEquals(0, sheet.getTimeSheet().size());
		});
	}

	@Test
	void testInvalidClockOutBeforePeriodStart() {
		LocalDate period = LocalDate.now().plus(Period.ofWeeks(3));
		TimeSheet sheet = new TimeSheet(period);
		assertAll(() -> {
			assertFalse(sheet.clockIn());
			assertEquals(0, sheet.getTimeSheet().size());
		});
	}

}
