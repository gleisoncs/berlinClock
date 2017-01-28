package com.gleisoncs.berlinclock.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.gleisoncs.berlinclock.exception.InvalidTimeException;
import com.gleisoncs.berlinclock.service.BerlinClockService;

/**
 * <h1>BerlinClockController</h1> 
 * 
 * <p>BerlinClockController class implements basic access to the BerlinClockService.</p> 
 * 
 * @author Gleison Caetano
 * @version 1.0
 * @since 2016-09-23
 * 
 */
@RestController
@RequestMapping("/berlinclock")
public class BerlinClockController {

	@Autowired
	BerlinClockService berlinClockService;

	@RequestMapping("/")
	public String welcomeScreen() {
		return "Welcome to Berlin Clock Service";
	}
	
	@RequestMapping(method = RequestMethod.GET)
	public ResponseEntity<?> getParentalControl(@RequestParam int hour, @RequestParam int minute,
			@RequestParam int second) throws InvalidTimeException {
		return new ResponseEntity<String>(berlinClockService.getTime(hour, minute, second), HttpStatus.OK);
	}
}