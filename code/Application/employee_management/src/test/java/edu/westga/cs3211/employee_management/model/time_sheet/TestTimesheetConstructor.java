package edu.westga.cs3211.employee_management.model.time_sheet;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import java.time.LocalDate;

import org.junit.jupiter.api.Test;

import edu.westga.edu.employee_management.model.TimeSheet;

class TestTimesheetConstructor {

	@Test
	void testNullStartDate() {
		assertThrows(IllegalArgumentException.class, () -> {
			new TimeSheet(null);
		});
	}

	@Test
	void testValidConstruction() {
		LocalDate period = LocalDate.of(2022, 04, 10);
		TimeSheet sheet = new TimeSheet(period);

		assertEquals(period, sheet.getStartDate());
	}

}
