package edu.westga.cs3211.employee_management.model.time_sheet;

import static org.junit.Assert.assertFalse;
import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.time.LocalDate;

import org.json.JSONObject;
import org.junit.jupiter.api.Test;

import edu.westga.edu.employee_management.model.TimeSheet;

class TestFromJson {

	@Test
	void testEmptyValidFromJson() {
		String json = "{\"startDate\":\"2022-04-10\"}";
		JSONObject jObject = new JSONObject(json);

		assertAll(() -> {
			TimeSheet sheet = TimeSheet.fromJson(jObject);
			assertEquals(LocalDate.of(2022, 04, 10), sheet.getStartDate());
			assertEquals(14, sheet.daySheets().size());
		});
	}

	@Test
	void testFromJsonWithDaySheets() {
		String json = "{\"daysheets\":[{\"dayIndex\":0,\"times\":[]},"
				+ "{\"dayIndex\":1,\"times\":[]},{\"dayIndex\":2,\"times\":[]},"
				+ "{\"dayIndex\":3,\"times\":[]},{\"dayIndex\":4,\"times\":[]},"
				+ "{\"dayIndex\":5,\"times\":[]},{\"dayIndex\":6,\"times\":[]},"
				+ "{\"dayIndex\":7,\"times\":[]},{\"dayIndex\":8,\"times\":[]},"
				+ "{\"dayIndex\":9,\"times\":[]},{\"dayIndex\":10,\"times\":[]},"
				+ "{\"dayIndex\":11,\"times\":[]},{\"dayIndex\":12,\"times\":[]},"
				+ "{\"dayIndex\":13,\"times\":[]}],\"startDate\":\"2022-04-10\"}";
		JSONObject jObject = new JSONObject(json);

		assertAll(() -> {
			TimeSheet sheet = TimeSheet.fromJson(jObject);
			assertEquals(LocalDate.of(2022, 04, 10), sheet.getStartDate());
			assertEquals(14, sheet.daySheets().size());
			assertFalse(sheet.hasOpenTime());
		});
	}

	@Test
	void testFromJsonWithOpenTime() {
		String json = "{\"daysheets\":[{\"dayIndex\":0,\"times\":[]},"
				+ "{\"dayIndex\":1,\"times\":[]},{\"dayIndex\":2,\"times\":[]},"
				+ "{\"dayIndex\":3,\"times\":[]},{\"dayIndex\":4,\"times\":[]},"
				+ "{\"dayIndex\":5,\"times\":[{\"dayIndex\":5,\"clockout\":\"2022-04-10T12:30\",\"clockin\":\"2022-04-10T10:30\"}]},"
				+ "{\"dayIndex\":6,\"times\":[]}, {\"dayIndex\":7,\"times\":[]},"
				+ "{\"dayIndex\":8,\"times\":[]}, {\"dayIndex\":9,\"times\":[]},"
				+ "{\"dayIndex\":10,\"times\":[{\"dayIndex\":10,\"clockin\":\"2022-04-10T10:30\"}]},"
				+ "{\"dayIndex\":11,\"times\":[]},{\"dayIndex\":12,\"times\":[]},"
				+ "{\"dayIndex\":13,\"times\":[]}],\"startDate\":\"2022-04-10\"}";
		JSONObject jObject = new JSONObject(json);

		TimeSheet sheet = TimeSheet.fromJson(jObject);
		assertTrue(sheet.hasOpenTime());
	}
}
