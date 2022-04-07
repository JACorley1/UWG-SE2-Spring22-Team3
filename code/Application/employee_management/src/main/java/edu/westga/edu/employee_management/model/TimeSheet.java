package edu.westga.edu.employee_management.model;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.Period;
import java.util.HashMap;
import java.util.Map;

/**
 * Time Sheet Class
 * 
 * @author Brianna Irie
 * @version Spring 2022
 */
public class TimeSheet {
	private static final String DATE_CANNOT_BE_NULL = "Date cannot be null";
	private static final String TIME_CANNOT_BE_NULL = "Time cannot be null";
	private Map<Integer, DaySheet> timeData;
	private DaySheet openTime;
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
		if (payPeriodDate == null) {
			throw new IllegalArgumentException(DATE_CANNOT_BE_NULL);
		}
		this.timeData = new HashMap<Integer, DaySheet>();
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
		if (!this.withinTimeSheet(time)) {
			return false;
		}
		int dayIndex = this.getDayIndex(time);
		DaySheet daySheet = null;

		if (this.timeData.containsKey(dayIndex)) {
			daySheet = this.timeData.get(dayIndex);
		} else {
			daySheet = new DaySheet(dayIndex);
			this.timeData.put(dayIndex, daySheet);
		}

		this.openTime = daySheet;
		return daySheet.clockIn(time);
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

		if (!this.withinTimeSheet(time)) {
			return false;
		}

		if (this.hasOpenTime()) {
			this.openTime.clockOut(time);
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
	public Map<Integer, DaySheet> getTimeSheet() {
		return this.timeData;
	}

	/**
	 * Checks if there is a current clock in without a clock out
	 * 
	 * Preconditions: none Postconditions: none
	 *
	 * @return if there is open time;
	 */
	public boolean hasOpenTime() {
		return this.openTime != null;
	}

	/**
	 * Add time to time sheet
	 * 
	 * Preconditions: time != null Postconditions: getTime().size() += 1
	 *
	 * @param time the employee time
	 */
	public void addTime(EmployeeTime time) {
		if (time == null) {
			throw new IllegalArgumentException(TIME_CANNOT_BE_NULL);
		}
		if (this.timeData.containsKey(time.getDayIndex())) {
			DaySheet sheet = this.timeData.get(time.getDayIndex());
			sheet.add(time);
		} else {
			DaySheet sheet = new DaySheet(time.getDayIndex());
			this.timeData.put(time.getDayIndex(), sheet);
			sheet.add(time);
		}

	}
}
