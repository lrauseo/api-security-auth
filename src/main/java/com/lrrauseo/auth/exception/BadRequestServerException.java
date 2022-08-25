package com.lrrauseo.auth.exception;

import org.springframework.http.HttpStatus;

import lombok.Getter;

@Getter
public class BadRequestServerException extends BaseException {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private HttpStatus status;

	public BadRequestServerException(String message, Throwable throwable) {
		super(message, throwable);
	}

	public BadRequestServerException(String message) {
		super(message);
	}

	protected BadRequestServerException(builder exceptionBuilder) {
		super(exceptionBuilder.message, exceptionBuilder.throwable);
		status = exceptionBuilder.status;

	}

	public static class builder {
		public builder() {
		}

		private String message;
		private Throwable throwable;
		
		private HttpStatus status;

		public builder message(String message) {
			this.message = message;
			return this;
		}

		public builder throwable(Throwable throwable) {
			this.throwable = throwable;
			return this;
		}

		public builder status(HttpStatus status) {
			this.status = status;
			return this;

		}

		public BadRequestServerException build() {
			var ex = new BadRequestServerException(this);
			return ex;
		}

	}
	
}
