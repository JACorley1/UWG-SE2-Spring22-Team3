package edu.westga.cs3211.employee_management.model.employee_profile;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import edu.westga.edu.employee_management.model.EmployeeProfile;
import edu.westga.edu.employee_management.model.EmployeeTime;
import edu.westga.edu.employee_management.model.TimeSheet;

class TestGetTimeSheet {
	private EmployeeProfile employee;

	@BeforeEach
	public void init() {
		this.employee = new EmployeeProfile(1, "Sophie", "", "Atelier", "example@gmail.com", "123-456-7890", true,
				"user name", "password");
	}
	
	@Test
	void testNullDate() {
		assertThrows(IllegalArgumentException.class, () -> this.employee.getTimeSheet(null));
	}
	
	@Test
	void testGetUncreatedWithEmptyProfile() {
		LocalDate date = LocalDate.of(2022, 3, 3);
		TimeSheet timesheet = this.employee.getTimeSheet(date);

		assertEquals(14, timesheet.getTimeSheet().size());
	}

	@Test
	void testGetUncreatedTimesheetWithOneOther() {
		LocalDate date = LocalDate.of(2022, 3, 3);
		this.employee.getTimeSheet(date.minusWeeks(2));
		TimeSheet timesheet = this.employee.getTimeSheet(date);

		assertEquals(14, timesheet.getTimeSheet().size());
	}

	@Test
	void testGetCreatedTimesheetWithOneOther() {
		LocalDate date = LocalDate.of(2022, 3, 3);
		EmployeeTime time = new EmployeeTime(LocalDateTime.of(date, LocalTime.of(3, 20)));
		this.employee.getTimeSheet(date.minusWeeks(2));
		TimeSheet timesheet = this.employee.getTimeSheet(date);
		timesheet.addTime(time);

		timesheet = this.employee.getTimeSheet(date);

		assertEquals(14, timesheet.getTimeSheet().size());
	}
}
