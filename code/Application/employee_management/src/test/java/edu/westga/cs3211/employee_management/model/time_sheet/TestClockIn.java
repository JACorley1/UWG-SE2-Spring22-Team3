package edu.westga.cs3211.employee_management.model.time_sheet;

import static org.junit.Assert.assertFalse;
import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.time.LocalDateTime;
import java.time.Period;

import org.junit.jupiter.api.Test;

import edu.westga.edu.employee_management.model.TimeSheet;

class TestClockIn {

	@Test
	void testValidClockIn() {
		LocalDateTime period = LocalDateTime.now();
		TimeSheet sheet = new TimeSheet(period);
		assertAll(() -> {
			assertTrue(sheet.clockIn());
			assertEquals(1, sheet.getTimeSheet().size());
		});
	}

	@Test
	void testInvalidClockInOpenTime() {
		LocalDateTime period = LocalDateTime.now();
		TimeSheet sheet = new TimeSheet(period);
		sheet.clockIn();
		assertAll(() -> {
			assertFalse(sheet.clockIn());
			assertEquals(1, sheet.getTimeSheet().size());
		});
	}

	@Test
	void testInvalidClockInAfterPeriodEnd() {
		LocalDateTime period = LocalDateTime.now().minus(Period.ofWeeks(3));
		TimeSheet sheet = new TimeSheet(period);
		assertAll(() -> {
			assertFalse(sheet.clockIn());
			assertEquals(0, sheet.getTimeSheet().size());
		});
	}

	@Test
	void testInvalidClockInBeforePeriodStart() {
		LocalDateTime period = LocalDateTime.now().plus(Period.ofWeeks(3));
		TimeSheet sheet = new TimeSheet(period);
		assertAll(() -> {
			assertFalse(sheet.clockIn());
			assertEquals(0, sheet.getTimeSheet().size());
		});
	}

}
