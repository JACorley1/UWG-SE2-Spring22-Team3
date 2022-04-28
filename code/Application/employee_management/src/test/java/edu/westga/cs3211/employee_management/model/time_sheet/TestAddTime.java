package edu.westga.cs3211.employee_management.model.time_sheet;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;

import org.json.JSONObject;
import org.junit.jupiter.api.Test;

import edu.westga.edu.employee_management.model.EmployeeTime;
import edu.westga.edu.employee_management.model.TimeSheet;

class TestAddTime {

	@Test
	void testValidAdd() {
		LocalDate date = LocalDate.of(2022, 3, 3);
		TimeSheet sheet = new TimeSheet(date);

		EmployeeTime time = new EmployeeTime(4, LocalDateTime.of(date, LocalTime.of(3, 20)));
		sheet.addTime(time);

		assertEquals(1, sheet.getTimeSheet().get(4).getTimes().size());
	}

	@Test
	void testInvalidAddNullTime() {
		LocalDate date = LocalDate.of(2022, 3, 3);
		TimeSheet sheet = new TimeSheet(date);

		assertThrows(IllegalArgumentException.class, () -> {
			sheet.addTime(null);
		});
	}

	@Test
	void testAddTimeWithMissingDaysheets() {
		String json = "{\"daysheets\":[],\"startDate\":\"2022-04-10\"}";
		JSONObject jObject = new JSONObject(json);
		TimeSheet sheet = TimeSheet.fromJson(jObject);
		LocalDate date = LocalDate.of(2022, 4, 10);

		EmployeeTime time = new EmployeeTime(4, LocalDateTime.of(date, LocalTime.of(3, 20)));
		sheet.addTime(time);

		assertEquals(1, sheet.getTimeSheet().get(4).getTimes().size());
	}
}
