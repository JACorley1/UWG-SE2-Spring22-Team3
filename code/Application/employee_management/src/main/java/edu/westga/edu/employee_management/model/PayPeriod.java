package edu.westga.edu.employee_management.model;

import java.time.LocalDate;
import java.time.Period;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoField;
import java.time.temporal.ChronoUnit;
import java.time.temporal.TemporalField;
import java.time.temporal.WeekFields;
import java.util.Iterator;
import java.util.List;
import java.util.Locale;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * Pay Period Class
 * 
 * @author Brianna Irie
 * @version Spring 2021
 */
public class PayPeriod implements Iterable<LocalDate> {

	private static final String DATE_CANNOT_BE_NULL = "Date cannot be null";
	private LocalDate startDate;
	private LocalDate endDate;

	/**
	 * Creates new instance of PayPeriod
	 * 
	 * Preconditions: date != null
	 * Postconditions: none
	 *
	 * @param date
	 */
	public PayPeriod(LocalDate date) {
		if (date == null) {
			throw new IllegalArgumentException(DATE_CANNOT_BE_NULL);
		}

		this.startDate = getStartDate(date);
		this.endDate = getEndDate(date);
	}

	/**
	 * Gets the period start date of the given date
	 * 
	 * Preconditions: date != null
	 * Postconditions: none
	 *
	 * @param date
	 * @return the period start date
	 */
	public static LocalDate getStartDate(LocalDate date) {
		if (date == null) {
			throw new IllegalArgumentException(DATE_CANNOT_BE_NULL);
		}

		int weekOfYear = date.get(ChronoField.ALIGNED_WEEK_OF_YEAR);
		TemporalField defaultField = WeekFields.of(Locale.getDefault()).dayOfWeek();
		LocalDate startOfWeek = date.with(defaultField, 1);
		if (weekOfYear % 2 == 0) {
			startOfWeek = startOfWeek.minus(Period.ofWeeks(1));
		}

		return startOfWeek;
	}

	/**
	 * Gets the period end date of the given date
	 * 
	 * Preconditions: date != null
	 * Postconditions: none
	 *
	 * @param date
	 * @return the period end date
	 */
	public static LocalDate getEndDate(LocalDate date) {
		if (date == null) {
			throw new IllegalArgumentException(DATE_CANNOT_BE_NULL);
		}

		LocalDate periodStart = getStartDate(date);
		LocalDate lastWeekOfPeriod = periodStart.plus(Period.ofWeeks(1));
		return lastWeekOfPeriod.with(getDayOfWeekField(), 7);

	}

	private static TemporalField getDayOfWeekField() {
		return WeekFields.of(Locale.getDefault()).dayOfWeek();
	}

	/**
	 * Get start date of next pay period
	 * 
	 * Preconditions: none
	 * Postconditions: none
	 *
	 * @param period
	 * @return the next pay period
	 */
	public static PayPeriod getNextPeriod(PayPeriod period) {
		return new PayPeriod(period.startDate.plusDays(14));
	}

	/**
	 * Gets start date of previous pay period
	 * 
	 * Preconditions: none
	 * Postconditions: none
	 *
	 * @param period
	 * @return the previous pay period
	 */
	public static PayPeriod getPreviousPeriod(PayPeriod period) {
		return new PayPeriod(period.startDate.minusDays(14));
	}

	/**
	 * Gets the startDate
	 *
	 * Preconditions: none
	 * Postconditions: none
	 *
	 * @return the startDate
	 */
	public LocalDate getStartDate() {
		return this.startDate;
	}

	/**
	 * Gets the endDate
	 *
	 * Preconditions: none
	 * Postconditions: none
	 *
	 * @return the endDate
	 */
	public LocalDate getEndDate() {
		return this.endDate;
	}

	@Override
	public Iterator<LocalDate> iterator() {
		return this.stream().iterator();
	}

	/**
	 * Gets stream of dates in period
	 * 
	 * Preconditions: none
	 * Postconditions: none
	 *
	 * @return the dates in period
	 */
	private Stream<LocalDate> stream() {
		return Stream.iterate(this.startDate, date -> date.plusDays(1))
				.limit(ChronoUnit.DAYS.between(this.startDate, this.endDate) + 1);
	}

	/**
	 * Gets a list of dates in period
	 * 
	 * Preconditions: none
	 * Postconditions: none
	 *
	 * @return the dates in period
	 */
	public List<LocalDate> toList() {
		List<LocalDate> dates = this.stream().collect(Collectors.toList());
		return dates;
	}

	@Override
	public String toString() {
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MMM dd");
		String startString = this.startDate.format(formatter);
		String endString = this.endDate.format(formatter);

		return startString + " - " + endString;
	}
}
