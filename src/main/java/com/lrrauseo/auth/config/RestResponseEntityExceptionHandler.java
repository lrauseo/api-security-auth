package com.lrrauseo.auth.config;

import java.time.LocalDateTime;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import com.lrrauseo.auth.exception.AuthenticationExceptionDetails;
import com.lrrauseo.auth.exception.BadRequestServerException;
import com.lrrauseo.auth.exception.ExceptionDetails;
import com.lrrauseo.auth.exception.ServerErrorServerException;

@ControllerAdvice
public class RestResponseEntityExceptionHandler extends ResponseEntityExceptionHandler {

	@ExceptionHandler(value = { AuthenticationException.class, UsernameNotFoundException.class, })
	public ResponseEntity<AuthenticationExceptionDetails> handleForbiddenException(RuntimeException exception) {
		return new ResponseEntity<>(AuthenticationExceptionDetails.builder().date(LocalDateTime.now())
				.statusCode(HttpStatus.FORBIDDEN.value()).status(HttpStatus.FORBIDDEN).title("Authentication Exception")
				.message(exception.getMessage()).developerMessage(exception.getClass().getName()).build(),
				HttpStatus.FORBIDDEN);
	}

	@ExceptionHandler(value = { BadRequestServerException.class })
	public ResponseEntity<ExceptionDetails> handleBadRequestException(RuntimeException exception) {

		var ex = ExceptionDetails.builder().date(LocalDateTime.now()).statusCode(HttpStatus.BAD_REQUEST.value())
				.status(HttpStatus.BAD_REQUEST).title("Exception").message(exception.getMessage())
				.developerMessage(exception.getClass().getName()).build();

		return new ResponseEntity<>(ex, HttpStatus.BAD_REQUEST);
	}

	@ExceptionHandler(value = { ServerErrorServerException.class })
	public ResponseEntity<ExceptionDetails> handleInternalServerException(RuntimeException exception) {

		var ex = ExceptionDetails.builder().date(LocalDateTime.now())
				.statusCode(HttpStatus.INTERNAL_SERVER_ERROR.value()).status(HttpStatus.INTERNAL_SERVER_ERROR)
				.title("Exception").message(exception.getMessage()).developerMessage(exception.getClass().getName())
				.build();

		return new ResponseEntity<>(ex, HttpStatus.INTERNAL_SERVER_ERROR);
	}

	@Override
	protected ResponseEntity<Object> handleExceptionInternal(Exception ex, Object body,
			org.springframework.http.HttpHeaders headers, HttpStatus status, WebRequest request) {
		ExceptionDetails exceptionDetails = ExceptionDetails.builder().date(LocalDateTime.now())
				.statusCode(status.value()).title(ex.getCause().getMessage()).message(ex.getMessage()).status(status)
				.developerMessage(ex.getClass().getName()).build();

		return new ResponseEntity<>(exceptionDetails, headers, status);
	}
}
