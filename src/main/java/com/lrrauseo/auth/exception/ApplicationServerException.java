package com.lrrauseo.auth.exception;

import org.springframework.http.HttpStatus;

import lombok.Getter;

@Getter
public class ApplicationServerException extends BaseException {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private HttpStatus status;

	public ApplicationServerException(String message, Throwable throwable) {
		super(message, throwable);
	}

	public ApplicationServerException(String message) {
		super(message);
	}

	protected ApplicationServerException(builder exceptionBuilder) {
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

		public ApplicationServerException build() {
			var ex = new ApplicationServerException(this);
			return ex;
		}
	}
}
