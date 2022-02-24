package edu.westga.edu.employee_management.model;

import java.time.LocalDateTime;
import java.time.Period;
import java.time.temporal.ChronoField;
import java.time.temporal.TemporalField;
import java.time.temporal.WeekFields;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Locale;

/**
 * Time Sheet Class
 * 
 * @author Brianna Irie
 * @version Spring 2022
 */
public class TimeSheet {
	private Collection<EmployeeTime> timeData;
	private EmployeeTime openTime;
	private LocalDateTime payPeriodStart;
	private LocalDateTime payPeriodEnd;

	/**
	 * 
	 * Creates new instance of TimeSheet
	 * 
	 * Preconditions: none
	 * Postconditions: none
	 *
	 * @param payPeriodDate a date with in pay period
	 */
	public TimeSheet(LocalDateTime payPeriodDate) {
		this.timeData = new ArrayList<EmployeeTime>();
		this.setPayPeriodRange(payPeriodDate);
	}

	private void setPayPeriodRange(LocalDateTime payPeriodDate) {
		int weekOfYear = payPeriodDate.get(ChronoField.ALIGNED_WEEK_OF_YEAR);
		TemporalField defaultField = WeekFields.of(Locale.getDefault()).dayOfWeek();
		LocalDateTime startOfWeek = payPeriodDate.with(defaultField, 1);
		if (weekOfYear % 2 == 0) {
			startOfWeek.minus(Period.ofWeeks(1));
		}

		this.payPeriodStart = startOfWeek;
		this.payPeriodEnd = startOfWeek.plus(Period.ofWeeks(1));
		this.payPeriodEnd = this.payPeriodEnd.with(defaultField, 7);
	}

	/**
	 * Clocks current time in
	 * 
	 * Preconditions: none
	 * Postconditions: none
	 *
	 * @return true if successfully clocked in false otherwise
	 */
	public boolean clockIn() {
		LocalDateTime time = LocalDateTime.now();

		if (this.openTime == null && this.withinTimeSheet(time)) {
			EmployeeTime newTime = new EmployeeTime(time);
			this.openTime = newTime;
			this.timeData.add(newTime);
			return true;
		} else {
			return false;
		}
	}

	private boolean withinTimeSheet(LocalDateTime time) {
		return time.isBefore(this.payPeriodEnd) && time.isAfter(this.payPeriodStart);
	}

	/**
	 * Clocks current time out
	 * 
	 * Preconditions: none
	 * Postconditions: none
	 *
	 * @return true if successfully clocked out false otherwise
	 */
	public boolean clockOut() {
		LocalDateTime time = LocalDateTime.now();

		if (this.openTime != null && this.withinTimeSheet(time)) {
			this.openTime.setClockOutTime(time);
			this.openTime = null;
			return true;
		} else {
			return false;
		}
	}

	/**
	 * Gets the time sheet
	 * 
	 * Preconditions: none
	 * Postconditions: none
	 *
	 * @return the time sheet
	 */
	public Collection<EmployeeTime> getTimeSheet() {
		return this.timeData;
	}
}
