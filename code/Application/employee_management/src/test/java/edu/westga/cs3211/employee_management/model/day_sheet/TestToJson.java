package edu.westga.cs3211.employee_management.model.day_sheet;

import static org.junit.jupiter.api.Assertions.*;

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

}
