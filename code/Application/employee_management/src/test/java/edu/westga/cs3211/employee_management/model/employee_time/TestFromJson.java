package edu.westga.cs3211.employee_management.model.employee_time;

import static org.junit.jupiter.api.Assertions.*;

import java.time.LocalDateTime;

import org.json.JSONObject;
import org.junit.jupiter.api.Test;

import edu.westga.edu.employee_management.model.EmployeeTime;

class TestFromJson {

	@Test
	void test() {
		EmployeeTime time = new EmployeeTime(5, LocalDateTime.now());
		JSONObject jObject = time.toJson();
		
		assertEquals(time.getDayIndex(), EmployeeTime.fromJson(jObject).getDayIndex());
		assertEquals(time.getHoursWorked(), EmployeeTime.fromJson(jObject).getHoursWorked());
		assertEquals(time.getClockInTime(), EmployeeTime.fromJson(jObject).getClockInTime());
		assertEquals(time.getClockOutTime(), EmployeeTime.fromJson(jObject).getClockOutTime());
	}

}
