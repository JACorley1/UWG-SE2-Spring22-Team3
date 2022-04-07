package edu.westga.cs3211.employee_management.model.day_sheet;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;

import org.junit.jupiter.api.Test;

import edu.westga.edu.employee_management.model.DaySheet;

class TestDaySheetClockout {

	@Test
	void testValidClockout() {
		int dayIndex = 0;
		DaySheet sheet = new DaySheet(dayIndex);
		LocalDate date = LocalDate.of(2022, 4, 1);
		LocalDateTime time = LocalDateTime.of(date, LocalTime.of(3, 20));
		sheet.clockIn(time);
		assertAll(() -> {
			assertTrue(sheet.clockOut(time.plusHours(2)));
			assertEquals(1, sheet.getTimes().size());
			assertEquals(2, sheet.getHoursWorked());
		});
	}

	@Test
	void testClockoutWithoutClockin() {
		int dayIndex = 0;
		DaySheet sheet = new DaySheet(dayIndex);
		LocalDate date = LocalDate.of(2022, 4, 1);
		LocalDateTime time = LocalDateTime.of(date, LocalTime.of(3, 20));
		assertAll(() -> {
			assertFalse(sheet.clockOut(time));
			assertEquals(0, sheet.getTimes().size());
			assertFalse(sheet.hasOpenTime());
		});
	}

	@Test
	void testClockoutTimeBeforeClockinTime() {
		int dayIndex = 0;
		DaySheet sheet = new DaySheet(dayIndex);
		LocalDate date = LocalDate.of(2022, 4, 1);
		LocalDateTime time = LocalDateTime.of(date, LocalTime.of(3, 20));
		sheet.clockIn(time);
		assertAll(() -> {
			assertFalse(sheet.clockOut(time.minusHours(2)));
			assertEquals(1, sheet.getTimes().size());
		});
	}

	@Test
	void testNullTime() {
		int dayIndex = 0;
		DaySheet sheet = new DaySheet(dayIndex);
		assertThrows(IllegalArgumentException.class, () -> {
			sheet.clockOut(null);
		});
	}

}
