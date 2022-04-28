package edu.westga.edu.employee_management.model;

import java.time.LocalDateTime;
import java.time.format.DateTimeParseException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

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
		if (this.openTime != null) {
			return true;
		} else {
			for (EmployeeTime time : this.times) {
				if (time.getClockOutTime() == null) {
					this.openTime = time;
					return true;
				}
			}
		}

		return false;
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
	 * Gets the dayIndex
	 *
	 * Preconditions: none
	 * Postconditions: none
	 *
	 * @return the dayIndex
	 */
	public int getDayIndex() {
		return this.dayIndex;
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

	/**
	 * Returns the number of clock times in the day sheet
	 * 
	 * Preconditions: none
	 * Postconditions: none
	 *
	 * @return the size
	 */
	public int size() {
		return this.times.size();
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

		JSONArray times = new JSONArray();
		for (EmployeeTime time : this.times) {
			times.put(time.toJson());
		}

		json.put("dayIndex", this.dayIndex);
		json.put("times", times);

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
	public static DaySheet fromJson(JSONObject json) throws JSONException, DateTimeParseException {
		int dayIndex = json.getInt("dayIndex");
		DaySheet day = new DaySheet(dayIndex);

		List<EmployeeTime> data = new ArrayList<EmployeeTime>();

		JSONArray times = json.optJSONArray("times");

		if (times != null) {
			for (Object obj : times) {
				JSONObject time = (JSONObject) obj;
				EmployeeTime employeeTime = EmployeeTime.fromJson(time);
				data.add(employeeTime);
			}

			day.setTimes(data);
		}

		return day;

	}

	private void setTimes(List<EmployeeTime> times) {
		this.times = times;
	}
}
