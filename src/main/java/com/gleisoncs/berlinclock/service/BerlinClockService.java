package com.gleisoncs.berlinclock.service;

import org.springframework.stereotype.Component;

import com.gleisoncs.berlinclock.exception.InvalidTimeException;
import com.gleisoncs.berlinclock.utils.LampStatus;
import com.gleisoncs.berlinclock.vo.Clock;
import com.gleisoncs.berlinclock.vo.Row;

/**
 * <p>Implementation of a Berlin clock. Formats the time as a multiline string
 * where each row contains string representation of the LampStatus as follows: 
 * 
 * <li> Y  yellow lamp status on </li>
 * <li> R  red lamp status on </li>
 * <li> O  lamp status off</li>
 * 
 * <p>The Berlin Clock is always 5 lines:</p>
 * 
 * <li>First Row - one yellow lamp blinks every 2 s </li>
 * <li>Second Row - 4 red lamps each representing 5 hours </li>
 * <li>Third Row - 4 red lamps each representing 1 hours </li>
 * <li>Fourth Row - 11 lamps each representing 5 minutes where every third is</li>
 * red and all other are yellow or off
 * <li>Fifth row - 4 yellow lamps each representing 1 minute</li>
 * 
 * <p>Example 17:24:01 should be represented as 2s+ (5+5+5)h+(1+1)h+(9x5)min+4min or:</p>
 * 
 * <li>Y           -> 2s          |
 * <li>RRRO        -> (5+5+5)h    |
 * <li>RROO        -> (1+1)h      |    -> 17:24:01
 * <li>YYRYOOOOOOO -> (4x5)min    |
 * <li>YYYY        -> 4min        |
 * 
 * @author gleisoncs
 *
 */
@Component
public class BerlinClockService {

	public String getTime(int hour, int minute, int seconds) throws InvalidTimeException {
		validateInputs(hour, minute, seconds);
		return createClock(hour, minute, seconds).toString();
	}

	/**
	 * <p>Creates a Clock base on <b>hour</b>, <b>minute</b>, <b>second.</b> </p> 
	 * 
	 * @param hour
	 * @param minute
	 * @param second
	 * 
	 * @return Clock
	 */
	private Clock createClock(int hour, int minute, int second) {
		Clock clock = new Clock();

		Row firstRow = createRow(1, LampStatus.YELLOW, second % 4 < 2 ? 0 : 1);
		Row secondRow = createRow(4, LampStatus.RED, hour / 5);
		Row thirdRow = createRow(4, LampStatus.RED, hour % 5);
		Row forthRow = createRow(11, LampStatus.YELLOW, minute / 5, 3, LampStatus.RED);
		Row fifthRow = createRow(4, LampStatus.YELLOW, minute % 5);
		
		clock.addRow(firstRow);
		clock.addRow(secondRow);
		clock.addRow(thirdRow);
		clock.addRow(forthRow);
		clock.addRow(fifthRow);
		
		return clock;

	}

	
	private static Row createRow(int size, LampStatus lamp, int numOn) {
		return new Row(size, lamp, numOn, 1, lamp);
	}

	/**
	 * <p>Creates a row of size <b>size</b> with <b>numOn</b> lamps of type <b>lamp</b> where each <b>eachTh</b> is of type <b>eachThLamp</b></p> 
	 * 
	 * @param size
	 * @param lamp
	 * @param numOn
	 * @param iteract
	 * @param lampIteract
	 * 
	 * @return Row of size <b>size</b>
	 */
	private static Row createRow(int size, LampStatus lamp, int numOn, int iteract, LampStatus lampIteract) {
		return new Row(size, lamp, numOn, iteract, lampIteract);
	}	
	
	/**
	 * <p>Validates if all the input parameters are valid.<p>
	 * 
	 * @param hour
	 * @param minute
	 * @param second
	 * 
	 * @throws InvalidTimeException
	 */
	private void validateInputs(int hour, int minute, int second) {
		Clock.validateHour(hour);
		Clock.validateMinute(minute);
		Clock.validateSeconds(second);
	}
}