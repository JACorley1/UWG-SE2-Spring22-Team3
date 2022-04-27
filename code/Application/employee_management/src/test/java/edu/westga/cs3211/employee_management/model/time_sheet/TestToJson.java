package edu.westga.cs3211.employee_management.model.time_sheet;

import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertEquals;

import java.time.LocalDate;
import java.time.LocalDateTime;

import org.json.JSONObject;
import org.junit.jupiter.api.Test;

import edu.westga.edu.employee_management.model.TimeSheet;

class TestToJson {

	@Test
	void testEmptyTimeSheet() {
		LocalDate date = LocalDate.of(2022, 04, 10);
		TimeSheet sheet = new TimeSheet(date);
		JSONObject json = sheet.toJson();

		assertAll(() -> {
			assertEquals(date.toString(), json.get("startDate"));
			assertEquals(14, json.getJSONArray("daysheets").length());
		});
	}

	@Test
	void testOneDayInTimeSheet() {
		LocalDate date = LocalDate.of(2022, 04, 10);
		TimeSheet sheet = new TimeSheet(date);
		LocalDateTime time = date.atStartOfDay();
		sheet.clockIn(time);
		sheet.clockOut(time.plusHours(2));

		JSONObject json = sheet.toJson();

		assertAll(() -> {
			assertEquals(date.toString(), json.getString("startDate"));
			assertEquals(14, json.getJSONArray("daysheets").length());
		});
	}
}
