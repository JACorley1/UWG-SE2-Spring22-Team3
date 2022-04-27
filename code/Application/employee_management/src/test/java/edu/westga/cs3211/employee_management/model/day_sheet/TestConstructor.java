package edu.westga.cs3211.employee_management.model.day_sheet;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

import edu.westga.edu.employee_management.model.DaySheet;

class TestConstructor {

	@Test
	void testValidConstructor() {
		DaySheet sheet = new DaySheet(5);
		assertEquals(0, sheet.size());
	}
	

}
