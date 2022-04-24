package edu.westga.cs3211.employee_management.model.time_sheet;

import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.time.LocalDate;
import java.time.LocalDateTime;

import org.junit.jupiter.api.Test;

import edu.westga.edu.employee_management.model.TimeSheet;

class TestClockOut {

	@Test
	void testValidClockOut() {
		LocalDate period = LocalDate.now();
		TimeSheet sheet = new TimeSheet(period);
		LocalDateTime time = period.atStartOfDay();
		sheet.clockIn(time);
		assertAll(() -> {
			assertTrue(sheet.clockOut(time.plusHours(10)));
			assertFalse(sheet.hasOpenTime());
		});
	}

	@Test
	void testInvalidClockOutNoOpenTime() {
		LocalDate period = LocalDate.now();
		TimeSheet sheet = new TimeSheet(period);
		LocalDateTime time = period.atStartOfDay();
		assertAll(() -> {
			assertFalse(sheet.clockOut(time));
		});
	}
}
