package edu.westga.edu.employee_management.model;

import java.time.LocalDateTime;
import java.time.format.DateTimeParseException;
import java.time.temporal.ChronoUnit;

import org.json.JSONException;
import org.json.JSONObject;

/**
 * Employee Time Class
 * 
 * @author Brianna Irie
 * @version Spring 2022
 */
public class EmployeeTime implements Comparable<EmployeeTime> {
	private static final String CLOCK_OUT_TIME_CANNOT_BE_NULL = "clockOutTime cannot be null";
	private static final String CLOCK_IN_TIME_CANNOT_BE_NULL = "clockInTime cannot be null";

	private LocalDateTime clockInTime;
	private LocalDateTime clockOutTime;
	private int dayIndex;
	
	/**
	 * 
	 * Creates new instance of EmployeeTime
	 * 
	 * Preconditions: clockInTime != null
	 * Postconditions: none
	 *
	 * @param clockInTime the time of clock in
	 * @param dayIndex    the index of the day
	 */
	public EmployeeTime(int dayIndex, LocalDateTime clockInTime) {
		if (clockInTime == null) {
			throw new IllegalArgumentException(CLOCK_IN_TIME_CANNOT_BE_NULL);
		}

		this.clockInTime = clockInTime;
		this.dayIndex = dayIndex;
	}

	/**
	 * 
	 * Creates new instance of EmployeeTime
	 * 
	 * Preconditions: clockInTime != null
	 * Postconditions: none
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
	 * Preconditions: none
	 * Postconditions: none
	 *
	 * @return the clock in time
	 */
	public LocalDateTime getClockInTime() {
		return this.clockInTime;
	}

	/**
	 * Sets the clock in time
	 * 
	 * Preconditions: clockInTime != null
	 * Postconditions: none
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
	 * Preconditions: none
	 * Postconditions: none
	 *
	 * @return the clockOutTime
	 */
	public LocalDateTime getClockOutTime() {
		return this.clockOutTime;
	}

	/**
	 * Sets the clock out time
	 * 
	 * Preconditions: clockOutTime != null && clockOutTime > clockInTime
	 * Postconditions: none
	 *
	 * @param clockOutTime the clock out time to set
	 */
	public void setClockOutTime(LocalDateTime clockOutTime) {
		if (clockOutTime == null) {
			throw new IllegalArgumentException(CLOCK_OUT_TIME_CANNOT_BE_NULL);
		}
		
		if (this.clockInTime.isAfter(clockOutTime)) {
			throw new IllegalArgumentException("Clock out time must be after clock in");
		}

		this.clockOutTime = clockOutTime;
	}

	/**
	 * Gets the day
	 *
	 * Preconditions: none
	 * Postconditions: none
	 *
	 * @return the day
	 */
	public int getDayIndex() {
		return this.dayIndex;
	}

	/**
	 * Gets hours worked
	 * 
	 * Preconditions: none Postconditions: none
	 *
	 * @return the hours worked
	 */
	public double getHoursWorked() {
		if (this.clockInTime == null || this.clockOutTime == null) {
			return 0;
		}

		long minutes = ChronoUnit.MINUTES.between(this.clockInTime, this.clockOutTime);
		double hours = minutes / 60.0;
		return hours;
	}

	@Override
	public int compareTo(EmployeeTime other) {
		return this.clockInTime.compareTo(other.clockInTime);
	}

	/**
	 * Converts object to json
	 * 
	 * Preconditions: none
	 * Postconditions: none
	 *
	 * @return the object as a json
	 */
	public JSONObject toJson() {
		JSONObject json = new JSONObject();
		String clockout = this.clockOutTime == null ? null : this.clockOutTime.toString();

		json.put("dayIndex", this.dayIndex);
		json.put("clockin", this.clockInTime.toString());
		json.putOpt("clockout", clockout);

		return json;
	}

	/**
	 * Converts JSON object into day sheet
	 * 
	 * Preconditions: none
	 * Postconditions: none
	 *
	 * @param json the json to convert
	 * @return the employee profile
	 */
	public static EmployeeTime fromJson(JSONObject json) throws JSONException, DateTimeParseException {
		int dayIndex = json.getInt("dayIndex");
		LocalDateTime clockin = LocalDateTime.parse(json.getString("clockin"));

		EmployeeTime time = new EmployeeTime(dayIndex, clockin);

		String clockoutString = json.optString("clockout");

		if (!clockoutString.isEmpty()) {
			LocalDateTime clockout = LocalDateTime.parse(clockoutString);
			time.setClockOutTime(clockout);
		}

		return time;
	}

}
