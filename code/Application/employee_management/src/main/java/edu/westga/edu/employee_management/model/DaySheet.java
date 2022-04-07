package edu.westga.edu.employee_management.model;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class DaySheet {

	private static final String TIME_CANNOT_BE_NULL = "Time cannot be null";
	private List<EmployeeTime> times;
	private int dayIndex;
	private EmployeeTime openTime;

	/**
	 * Creates new instance of DaySheet
	 * 
	 * Preconditions: none Postconditions: none
	 *
	 * @param dayIndex
	 */
	public DaySheet(int dayIndex) {
		this.dayIndex = dayIndex;
		this.times = new ArrayList<EmployeeTime>();
	}

	/**
	 * Adds EmployeeTime for the current date to sheet
	 * 
	 * Preconditions: time != null Postconditions: getTimes.size() ==
	 * getTimes.size()@prev + 1
	 *
	 * @param time the time of the clock in
	 * @return true if successfully clocked in false otherwise
	 */
	public boolean clockIn(LocalDateTime time) {
		if (time == null) {
			throw new IllegalArgumentException(TIME_CANNOT_BE_NULL);
		}

		if (this.openTime == null) {
			EmployeeTime newTime = new EmployeeTime(this.dayIndex, time);
			this.openTime = newTime;
			this.times.add(newTime);
			return true;
		} else {
			return false;
		}
	}

	/**
	 * Clocks current time out
	 * 
	 * Preconditions: time != null Postconditions: none
	 *
	 * @param time the clock out time
	 * @return true if successfully clocked out false otherwise
	 */
	public boolean clockOut(LocalDateTime time) {
		if (time == null) {
			throw new IllegalArgumentException(TIME_CANNOT_BE_NULL);
		}

		if (this.openTime != null && time.isAfter(this.openTime.getClockInTime())) {
			this.openTime.setClockOutTime(time);
			this.openTime = null;
			return true;
		} else {
			return false;
		}
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
	 * Gets hours worked
	 * 
	 * Preconditions: none Postconditions: none
	 *
	 * @return the hours worked
	 */
	public double getHoursWorked() {
		double hoursWorked = 0;
		for (EmployeeTime employeeTime : this.times) {
			hoursWorked += employeeTime.getHoursWorked();
		}

		return hoursWorked;
	}

	/**
	 * Gets the times
	 *
	 * Preconditions: none Postconditions: none
	 *
	 * @return the times
	 */
	public List<EmployeeTime> getTimes() {
		return this.times;
	}

	/**
	 * Adds given time to DaySheet
	 * 
	 * Preconditions: time != null Postconditions: getTimes.size() ==
	 * getTimes.size()@prev + 1
	 *
	 * @param time
	 */
	public void add(EmployeeTime time) {
		if (time == null) {
			throw new IllegalArgumentException(TIME_CANNOT_BE_NULL);
		}
		this.times.add(time);
		Collections.sort(this.times);
	}
}
