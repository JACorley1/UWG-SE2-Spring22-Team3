package edu.westga.edu.employee_management.model;

import java.time.LocalDate;
import java.time.Period;
import java.time.temporal.ChronoField;
import java.time.temporal.ChronoUnit;
import java.time.temporal.TemporalField;
import java.time.temporal.WeekFields;
import java.util.Iterator;
import java.util.List;
import java.util.Locale;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class PayPeriod implements Iterable<LocalDate> {

	private LocalDate startDate;
	private LocalDate endDate;

	/**
	 * 
	 * Creates new instance of PayPeriod
	 * 
	 * Preconditions: none
	 * Postconditions: none
	 *
	 * @param date
	 */
	public PayPeriod(LocalDate date) {
		this.startDate = getStartDate(date);
		this.endDate = getEndDate(date);
	}

	/**
	 * Gets the period start date of the given date
	 * 
	 * Preconditions: none
	 * Postconditions: none
	 *
	 * @param date
	 * @return the period start date
	 */
	public static LocalDate getStartDate(LocalDate date) {
		int weekOfYear = date.get(ChronoField.ALIGNED_WEEK_OF_YEAR);
		TemporalField defaultField = WeekFields.of(Locale.getDefault()).dayOfWeek();
		LocalDate startOfWeek = date.with(defaultField, 1);
		if (weekOfYear % 2 == 0) {
			startOfWeek.minus(Period.ofWeeks(1));
		}

		return startOfWeek;
	}

	/**
	 * Gets the period end date of the given date
	 * 
	 * Preconditions: none
	 * Postconditions: none
	 *
	 * @param date
	 * @return the period end date
	 */
	public static LocalDate getEndDate(LocalDate date) {
		LocalDate periodStart = getStartDate(date);
		LocalDate lastWeekOfPeriod = periodStart.plus(Period.ofWeeks(1));
		return lastWeekOfPeriod.with(getDayOfWeekField(), 7);

	}

	private static TemporalField getDayOfWeekField() {
		return WeekFields.of(Locale.getDefault()).dayOfWeek();
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
	public Stream<LocalDate> stream() {
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

}
