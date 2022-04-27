package edu.westga.cs3211.employee_management.model.day_sheet;

import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertEquals;

import java.time.LocalDateTime;

import org.json.JSONObject;
import org.junit.jupiter.api.Test;

import edu.westga.edu.employee_management.model.DaySheet;

class TestToJson {

	@Test
	void testValidToJson() {
		DaySheet sheet = new DaySheet(5);
		JSONObject jObject = sheet.toJson();
		
		assertEquals(5, jObject.get("dayIndex"));
	}

	@Test
	void testEmptyDaySheet() {
		DaySheet sheet = new DaySheet(1);
		JSONObject json = sheet.toJson();

		assertAll(() -> {
			assertEquals(1, json.get("dayIndex"));
			assertEquals(0, json.getJSONArray("times").length());
		});
	}

	@Test
	void testOneTimeInDaySheet() {
		DaySheet sheet = new DaySheet(1);
		LocalDateTime date = LocalDateTime.of(2022, 04, 10, 2, 30);
		sheet.clockIn(date);
		sheet.clockOut(date.plusHours(2));

		JSONObject json = sheet.toJson();

		assertAll(() -> {
			assertEquals(1, json.get("dayIndex"));
			assertEquals(1, json.getJSONArray("times").length());
		});
	}

	@Test
	void testThreeTimesInDaySheet() {
		DaySheet sheet = new DaySheet(1);
		LocalDateTime date = LocalDateTime.of(2022, 04, 10, 2, 30);
		sheet.clockIn(date);
		sheet.clockOut(date.plusHours(2));
		sheet.clockIn(date.plusHours(4));
		sheet.clockOut(date.plusHours(6));
		sheet.clockIn(date.plusHours(8));
		sheet.clockOut(date.plusHours(10));

		JSONObject json = sheet.toJson();

		assertAll(() -> {
			assertEquals(1, json.get("dayIndex"));
			assertEquals(3, json.getJSONArray("times").length());
		});
	}

}
