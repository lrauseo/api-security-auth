package com.lrrauseo.auth.exception;

import org.springframework.http.HttpStatus;

import lombok.Getter;
@Getter
public class ServerErrorServerException extends BaseException {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private HttpStatus status;

	public ServerErrorServerException(String message, Throwable throwable) {
		super(message, throwable);
	}

	public ServerErrorServerException(String message) {
		super(message);
	}

	protected ServerErrorServerException(builder exceptionBuilder) {
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

		public ServerErrorServerException build() {
			var ex = new ServerErrorServerException(this);
			return ex;
		}
	}
}
