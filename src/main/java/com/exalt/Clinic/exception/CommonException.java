package com.exalt.Clinic.exception;

public class CommonException extends RuntimeException {

	private static final long serialVersionUID = 1L;
	private ErrorEnum error;

	public CommonException(ErrorEnum error) {
		super(error.getMessage());
		this.error = error;
	}

	public CommonException(ErrorEnum error, Throwable throwable) {
		super(error.getMessage(), throwable);
		this.error = error;
	}

	public ErrorEnum getError() {
		return error;
	}

	public void setError(ErrorEnum error) {
		this.error = error;
	}

}
