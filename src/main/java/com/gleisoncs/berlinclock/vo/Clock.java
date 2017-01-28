package com.gleisoncs.berlinclock.vo;

import java.util.ArrayList;
import java.util.List;

import com.gleisoncs.berlinclock.exception.InvalidTimeException;

/**
 * <p>
 * Representation of a Berlin clock. The Berlin clock has 5 rows:
 * </p>
 * 
 * <li>First row means the top lamp is a pump which is blinking on/off every two
 * seconds
 * <li>Second row means 4 red lamps representing 5 hours each
 * <li>Third row means 4 red lamps representing 1 hour each
 * <li>Fourth row means 11 lamps each representing 5 minutes where every third
 * is red and all other are yellow
 * <li>Fifth row - 4 yellow lamps each representing 1 minute
 * 
 * @author gleisoncs
 *
 */
public class Clock {
	private final List<Row> rows = new ArrayList<Row>();

	public void addRow(Row row) {
		rows.add(row);
	}

	public String toString() {
		StringBuilder sb = new StringBuilder();
		rows.stream().forEach(row -> sb.append(row.toString()));
		return sb.toString();
	}

	public static void validateHour(int hour) {
		if (hour < 0 || hour > 23)
			throw new InvalidTimeException("Param hour is incorrect: " + hour + ". Correct is between (0,23)");
	}

	public static void validateMinute(int minute) {
		if (minute < 0 || minute > 59) {
			throw new InvalidTimeException("Param minutes is incorrect: " + minute + ". Correct is between (0,59)");
		}
	}

	public static void validateSeconds(int seconds) {
		if (seconds < 0 || seconds > 59) {
			throw new InvalidTimeException("Param second is incorrect: " + seconds + ". Correct is between (0,59)");
		}
	}
}