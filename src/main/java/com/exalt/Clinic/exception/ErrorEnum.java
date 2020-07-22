package com.exalt.Clinic.exception;

import org.springframework.http.HttpStatus;

public enum ErrorEnum {
	
	WRONG_ID("Wrong clinic ID", HttpStatus.BAD_REQUEST),
	NOT_DELETED("Clinict wasn't deleted", HttpStatus.BAD_REQUEST),
	INVALID_WORKING_FIELD("Working field must be oe of 3 (Owner,Admin,Worker ", HttpStatus.INTERNAL_SERVER_ERROR);

	private String message;
	private HttpStatus status;

	ErrorEnum(String message, HttpStatus status) {
		this.message = message;
		this.status = status;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public HttpStatus getStatus() {
		return status;
	}

	public void setStatus(HttpStatus status) {
		this.status = status;
	}

}
