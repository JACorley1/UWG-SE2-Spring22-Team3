package edu.westga.edu.employee_management.model;

import java.time.LocalDateTime;

public class EmployeeTime {
	private static final String CLOCK_OUT_TIME_CANNOT_BE_NULL = "clockOutTime cannot be null";
	private static final String CLOCK_IN_TIME_CANNOT_BE_NULL = "clockInTime cannot be null";

	private LocalDateTime clockInTime;
	private LocalDateTime clockOutTime;
	
	/**
	 * 
	 * Creates new instance of EmployeeTime
	 * 
	 * Preconditions: clockInTime != null Postconditions: none
	 *
	 * @param clockInTime the time of clock in
	 */
	public EmployeeTime(LocalDateTime clockInTime) {
		if (clockInTime == null) {
			throw new IllegalArgumentException(CLOCK_IN_TIME_CANNOT_BE_NULL);
		}

		this.clockInTime = clockInTime;
	}

	/**
	 * Gets the clock in time
	 *
	 * Preconditions: none Postconditions: none
	 *
	 * @return the clock in time
	 */
	public LocalDateTime getClockInTime() {
		return this.clockInTime;
	}

	/**
	 * Sets the clock in time
	 * 
	 * Preconditions: clockInTime != null Postconditions: none
	 *
	 * @param clockInTime the clock in time to set
	 */
	public void setClockInTime(LocalDateTime clockInTime) {
		if (clockInTime == null) {
			throw new IllegalArgumentException(CLOCK_IN_TIME_CANNOT_BE_NULL);
		}

		this.clockInTime = clockInTime;
	}

	/**
	 * Gets the clock out time
	 *
	 * Preconditions: none Postconditions: none
	 *
	 * @return the clockOutTime
	 */
	public LocalDateTime getClockOutTime() {
		return this.clockOutTime;
	}

	/**
	 * Sets the clock out time
	 * 
	 * Preconditions: clockOutTime != null Postconditions: none
	 *
	 * @param clockOutTime the clock out time to set
	 */
	public void setClockOutTime(LocalDateTime clockOutTime) {
		if (clockOutTime == null) {
			throw new IllegalArgumentException(CLOCK_OUT_TIME_CANNOT_BE_NULL);
		}

		this.clockOutTime = clockOutTime;
	}

}
