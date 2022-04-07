package edu.westga.cs3211.employee_management.model.day_sheet;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;

import org.junit.jupiter.api.Test;

import edu.westga.edu.employee_management.model.DaySheet;
import edu.westga.edu.employee_management.model.EmployeeTime;

class TestDaySheetAdd {

	@Test
	void testValidAdd() {
		LocalDate date = LocalDate.of(2022, 3, 3);
		DaySheet sheet = new DaySheet(0);

		EmployeeTime time = new EmployeeTime(4, LocalDateTime.of(date, LocalTime.of(3, 20)));
		sheet.add(time);

		assertEquals(1, sheet.getTimes().size());
	}

	@Test
	void testInvalidAddNullTime() {
		DaySheet sheet = new DaySheet(0);

		assertThrows(IllegalArgumentException.class, () -> {
			sheet.add(null);
		});
	}

}
