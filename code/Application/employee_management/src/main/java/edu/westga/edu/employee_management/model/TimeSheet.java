package edu.westga.edu.employee_management.model;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.Period;
import java.util.ArrayList;
import java.util.List;

/**
 * Time Sheet Class
 * 
 * @author Brianna Irie
 * @version Spring 2022
 */
public class TimeSheet {
	private List<EmployeeTime> timeData;
	private EmployeeTime openTime;
	private LocalDate payPeriodStart;
	private LocalDate payPeriodEnd;

	/**
	 * 
	 * Creates new instance of TimeSheet
	 * 
	 * Preconditions: none
	 * Postconditions: none
	 *
	 * @param payPeriodDate a date with in pay period
	 */
	public TimeSheet(LocalDate payPeriodDate) {
		this.timeData = new ArrayList<EmployeeTime>();
		this.setPayPeriodRange(payPeriodDate);
	}

	private void setPayPeriodRange(LocalDate payPeriodDate) {
		this.payPeriodStart = PayPeriod.getStartDate(payPeriodDate);
		this.payPeriodEnd = PayPeriod.getEndDate(payPeriodDate);
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
			int dayIndex = this.getDayIndex(time);
			EmployeeTime newTime = new EmployeeTime(dayIndex, time);
			this.openTime = newTime;
			this.timeData.add(newTime);
			return true;
		} else {
			return false;
		}
	}

	private int getDayIndex(LocalDateTime time) {
		Period periodBetween = Period.between(this.payPeriodStart, time.toLocalDate());
		int dayIndex = Math.abs(periodBetween.getDays());
		return dayIndex;
	}

	private boolean withinTimeSheet(LocalDateTime time) {
		return time.toLocalDate().isBefore(this.payPeriodEnd) && time.toLocalDate().isAfter(this.payPeriodStart);
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
	public List<EmployeeTime> getTimeSheet() {
		return this.timeData;
	}

	/**
	 * Gets Employee time for the day of the index
	 * 
	 * Preconditions: none
	 * Postconditions: none
	 *
	 * @param dayIndex
	 * @return the matching employee time or null if one is not found
	 */
	public EmployeeTime getTime(int dayIndex) {
		for (EmployeeTime currTime : this.timeData) {
			Period periodBetween = Period.between(this.payPeriodStart, currTime.getClockInTime().toLocalDate());
			int currIndex = Math.abs(periodBetween.getDays());
			if (currIndex == dayIndex) {
				return currTime;
			}
		}

		return null;
	}

	/**
	 * Checks if there is a current clock in without a clock out
	 * 
	 * Preconditions: none
	 * Postconditions: none
	 *
	 * @return if there is open time;
	 */
	public boolean hasOpenTime() {
		return this.openTime != null;
	}
}
