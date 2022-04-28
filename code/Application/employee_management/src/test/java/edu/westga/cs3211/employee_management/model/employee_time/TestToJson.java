package edu.westga.cs3211.employee_management.model.employee_time;

import static org.junit.jupiter.api.Assertions.*;

import java.time.LocalDateTime;

import org.json.JSONObject;
import org.junit.jupiter.api.Test;

import edu.westga.edu.employee_management.model.EmployeeTime;

class TestToJson {

	@Test
	void testValidToJson() {
		EmployeeTime time = new EmployeeTime(5, LocalDateTime.now());
		JSONObject jObject = time.toJson();
		
		assertEquals(time.getDayIndex(), jObject.get("dayIndex"));
		assertEquals(time.getClockInTime().toString(), jObject.get("clockin"));
	}

}
