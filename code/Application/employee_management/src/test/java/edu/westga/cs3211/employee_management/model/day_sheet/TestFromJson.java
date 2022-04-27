package edu.westga.cs3211.employee_management.model.day_sheet;

import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.json.JSONObject;
import org.junit.jupiter.api.Test;

import edu.westga.edu.employee_management.model.DaySheet;

class TestFromJson {

	@Test
	void testEmptyValidFromJson() {
		String json = "{\"dayIndex\":5}";
		JSONObject jObject = new JSONObject(json);

		assertAll(() -> {
			DaySheet sheet = DaySheet.fromJson(jObject);
			assertEquals(5, sheet.getDayIndex());
			assertEquals(0, sheet.getHoursWorked());
			assertTrue(sheet.getTimes().isEmpty());
			assertEquals(0, sheet.size());
		});
	}

	@Test
	void testFromJsonWithTimes() {
		String json = "{\"dayIndex\":5,\"times\":"
				+ "[{\"dayIndex\":5,\"clockout\":\"2022-04-10T04:30\",\"clockin\":\"2022-04-10T02:30\"},"
				+ "{\"dayIndex\":5,\"clockout\":\"2022-04-10T08:30\",\"clockin\":\"2022-04-10T06:30\"},"
				+ "{\"dayIndex\":5,\"clockout\":\"2022-04-10T12:30\",\"clockin\":\"2022-04-10T10:30\"}]}";
		JSONObject jObject = new JSONObject(json);
		

		assertAll(() -> {
			DaySheet sheet = DaySheet.fromJson(jObject);
			assertEquals(6, sheet.getHoursWorked());
			assertEquals(3, sheet.size());
			assertFalse(sheet.hasOpenTime());
		});
	}

	@Test
	void testFromJsonWithOpenTime() {
		String json = "{\"dayIndex\":5,\"times\":[{\"dayIndex\":5,\"clockin\":\"2022-04-10T10:30\"}]}";
		JSONObject jObject = new JSONObject(json);

		DaySheet sheet = DaySheet.fromJson(jObject);
		assertTrue(sheet.hasOpenTime());
	}

}
