package com.gleisoncs.berlinclock.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.BAD_REQUEST)
public class InvalidTimeException extends RuntimeException {

	private static final long serialVersionUID = 1L;

	public InvalidTimeException(String err) {
		super(err);
	}
}
