package com.gleisoncs.test;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.gleisoncs.berlinclock.exception.InvalidTimeException;
import com.gleisoncs.berlinclock.service.BerlinClockService;

@RunWith(SpringRunner.class)
@SpringBootTest(classes={BerlinClockService.class})
public class BerlinClockApplicationTests {

	@Autowired
	private BerlinClockService clock;
	
	@Test
	public void testMidNight() {
		String midNigth = "O\n" + "OOOO\n" + "OOOO\n" + "OOOOOOOOOOO\n" + "OOOO\n";
		String berlinClockTime = clock.getTime(0, 0, 0);
		
		Assert.assertEquals(berlinClockTime, midNigth);
	}
	
	@Test
	public void testAnteMeridian() {
		String anteMeridian = "Y\n" + "ROOO\n" + "RRRR\n" + "YYRYOOOOOOO\n" + "YYYY\n";
		String berlinClockTime = clock.getTime(9, 24, 54);
		
		Assert.assertEquals(berlinClockTime, anteMeridian);
	}

	@Test
	public void testMidDay() {
		String midDay = "O\n" + "RROO\n" + "RROO\n" + "OOOOOOOOOOO\n" + "OOOO\n";
		String berlinClockTime = clock.getTime(12, 0, 0);
		
		Assert.assertEquals(berlinClockTime, midDay);
	}
	
	@Test
	public void testPostMeridian() {
		String postMeridian = "Y\n" + "RROO\n" + "RRRR\n" + "YYOOOOOOOOO\n" + "YYOO\n";
		String berlinClockTime = clock.getTime(14, 12, 23);

		Assert.assertEquals(berlinClockTime, postMeridian);
	}
	
	@Test
	public void testSecondsOn() {
		String secondsOn = "Y\n" + "OOOO\n" + "OOOO\n" + "OOOOOOOOOOO\n" + "OOOO\n";
		String berlinClockTime = clock.getTime(0, 0, 2);

		Assert.assertEquals(berlinClockTime, secondsOn);
	}
	
	@Test
	public void testSecondsOff() {
		String secondsOff = "O\n" + "OOOO\n" + "OOOO\n" + "OOOOOOOOOOO\n" + "OOOO\n";
		String berlinClockTime = clock.getTime(0, 0, 1);

		Assert.assertEquals(berlinClockTime, secondsOff);
	}

	@Test(expected = InvalidTimeException.class)
	public void testInvalidInputHour() {
		clock.getTime(99, 0, 0);
	}

	@Test(expected = InvalidTimeException.class)
	public void testInvalidInputMinute() {
		clock.getTime(0, 99, 0);
	}

	@Test(expected = InvalidTimeException.class)
	public void testInvalidInputSecond() {
		clock.getTime(0, 0, 99);
	}
}