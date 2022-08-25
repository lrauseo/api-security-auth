package com.lrrauseo.auth.exception;

import lombok.Getter;
import lombok.extern.log4j.Log4j2;

@Log4j2

@Getter
public class BaseException extends RuntimeException {

	

	/**
	 * 
	 */
	private static final long serialVersionUID = -3464199931786011851L;

	public BaseException() {
		super();
		log.error("base exception");

	}

	public BaseException(String message, Throwable throwable) {
		super(message, throwable);
		log.error("Base exception: {}", message);

	}

	public BaseException(String message) {
		super(message);
		log.error("Base exception: {}", message);

	}

}
