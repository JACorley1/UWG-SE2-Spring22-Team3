package edu.westga.cs3211.employee_management.model.day_sheet;

import static org.junit.jupiter.api.Assertions.*;

import org.json.JSONObject;
import org.junit.jupiter.api.Test;

import edu.westga.edu.employee_management.model.DaySheet;

class TestFromJson {

	@Test
	void testValidFromJson() {
		DaySheet sheet = new DaySheet(5);
		JSONObject jObject = sheet.toJson();
		
		assertEquals(sheet.getHoursWorked(), DaySheet.fromJson(jObject).getHoursWorked());
		assertEquals(sheet.getTimes(), DaySheet.fromJson(jObject).getTimes());
		assertEquals(sheet.size(), DaySheet.fromJson(jObject).size());
	}

}
