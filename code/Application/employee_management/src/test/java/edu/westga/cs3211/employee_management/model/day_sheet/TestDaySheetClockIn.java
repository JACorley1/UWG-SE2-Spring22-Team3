package edu.westga.cs3211.employee_management.model.day_sheet;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;

import org.junit.jupiter.api.Test;

import edu.westga.edu.employee_management.model.DaySheet;

class TestDaySheetClockIn {

	@Test
	void testValidClockin() {
		int dayIndex = 0;
		DaySheet sheet = new DaySheet(dayIndex);
		LocalDate date = LocalDate.of(2022, 4, 1);
		LocalDateTime time = LocalDateTime.of(date, LocalTime.of(3, 20));

		assertAll(() -> {
			assertTrue(sheet.clockIn(time));
			assertEquals(1, sheet.getTimes().size());
		});
	}

	@Test
	void testNullTime() {
		int dayIndex = 0;
		DaySheet sheet = new DaySheet(dayIndex);
		assertThrows(IllegalArgumentException.class, () -> {
			sheet.clockIn(null);
		});
	}

	@Test
	void testDoubleClockin() {
		int dayIndex = 0;
		DaySheet sheet = new DaySheet(dayIndex);
		LocalDate date = LocalDate.of(2022, 4, 1);
		LocalDateTime time = LocalDateTime.of(date, LocalTime.of(3, 20));
		sheet.clockIn(time);

		assertAll(() -> {
			assertFalse(sheet.clockIn(time));
			assertTrue(sheet.hasOpenTime());
		});
	}

}
