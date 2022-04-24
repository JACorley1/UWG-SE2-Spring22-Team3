package edu.westga.cs3211.employee_management.model.time_sheet;

import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.Period;

import org.junit.jupiter.api.Test;

import edu.westga.edu.employee_management.model.TimeSheet;

class TestClockIn {

	@Test
	void testValidClockIn() {
		LocalDate period = LocalDate.now();
		TimeSheet sheet = new TimeSheet(period);
		assertAll(() -> {
			assertTrue(sheet.clockIn(period.atStartOfDay()));
			assertTrue(sheet.hasOpenTime());
		});
	}

	@Test
	void testInvalidClockInOpenTime() {
		LocalDate period = LocalDate.now();
		TimeSheet sheet = new TimeSheet(period);
		LocalDateTime time = period.atStartOfDay();
		sheet.clockIn(time);
		assertAll(() -> {
			assertFalse(sheet.clockIn(time.plusHours(10)));
			assertTrue(sheet.hasOpenTime());
		});
	}

	@Test
	void testInvalidClockInAfterPeriodEnd() {
		LocalDate period = LocalDate.now();
		TimeSheet sheet = new TimeSheet(period);
		LocalDateTime time = period.minus(Period.ofWeeks(3)).atStartOfDay();
		assertAll(() -> {
			assertFalse(sheet.clockIn(time));
			assertFalse(sheet.hasOpenTime());
		});
	}

	@Test
	void testInvalidClockInBeforePeriodStart() {
		LocalDate period = LocalDate.now();
		TimeSheet sheet = new TimeSheet(period);
		LocalDateTime time = period.plus(Period.ofWeeks(3)).atStartOfDay();
		assertAll(() -> {
			assertFalse(sheet.clockIn(time));
			assertFalse(sheet.hasOpenTime());
		});
	}

}
