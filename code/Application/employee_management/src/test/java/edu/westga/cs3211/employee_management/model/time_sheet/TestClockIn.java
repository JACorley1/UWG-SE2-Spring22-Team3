package edu.westga.cs3211.employee_management.model.time_sheet;

import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.Period;

import org.json.JSONObject;
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

	@Test
	void testClockinWithMissingDaySheets() {
		String json = "{\"daysheets\":[],\"startDate\":\"2022-04-10\"}";
		JSONObject jObject = new JSONObject(json);
		TimeSheet sheet = TimeSheet.fromJson(jObject);
		LocalDateTime time = LocalDateTime.of(2022, 4, 10, 3, 20);

		sheet.clockIn(time);
		assertAll(() -> {
			assertFalse(sheet.clockIn(time.plusHours(10)));
			assertTrue(sheet.hasOpenTime());
		});
	}

}
