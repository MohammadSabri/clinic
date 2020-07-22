package com.exalt.Clinic.exception;

import java.util.Date;

public class ApiError {
	private String message;
	private Date timestamp;
	private String uri;

	public ApiError() {
		super();
	}

	public ApiError(String message, Date timestamp, String uri) {
		super();
		this.message = message;
		this.timestamp = timestamp;
		this.uri = uri;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public Date getTimestamp() {
		return timestamp;
	}

	public void setTimestamp(Date timestamp) {
		this.timestamp = timestamp;
	}

	public String getUri() {
		return uri;
	}

	public void setUrl(String uri) {
		this.uri = uri;
	}

}
