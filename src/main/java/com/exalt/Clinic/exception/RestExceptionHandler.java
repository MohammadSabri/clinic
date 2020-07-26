package com.exalt.Clinic.exception;

import java.util.ArrayList;
import java.util.Date;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@ControllerAdvice
public class RestExceptionHandler extends ResponseEntityExceptionHandler {

	final Logger logger = LoggerFactory.getLogger(RestExceptionHandler.class);

	@ExceptionHandler(value = { CommonException.class })
	public ResponseEntity<ApiError> resourceNotFoundException(CommonException ex, WebRequest request) {
		logger.error("test the error {} ", ex);
		ApiError apiError = new ApiError(ex.getMessage(), new Date(), request.getDescription(false));

		return new ResponseEntity<>(apiError, new HttpHeaders(), ex.getError().getStatus());
	}

	@Override
	protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex,
			HttpHeaders headers, HttpStatus status, WebRequest request) {
		ArrayList<String> errorList = new ArrayList<>();
		for (ObjectError e : ex.getBindingResult().getFieldErrors()) {
			System.out.println(e.getDefaultMessage());
			errorList.add(e.getDefaultMessage());
		}
		ApiError apiError = new ApiError(errorList.toString(), new Date(), request.getDescription(false));
		return new ResponseEntity<>(apiError, new HttpHeaders(), HttpStatus.BAD_REQUEST);
	}

	@ExceptionHandler(value = { RuntimeException.class })
	public ResponseEntity<Object> myExeptionHandler(Exception ex, WebRequest request) {

		logger.error("test the error");
		System.out.println(ex);
		System.out.println(ex.getMessage());
		ApiError apiError = new ApiError("Internal server error", new Date(), request.getDescription(false));
		return new ResponseEntity<>(apiError, new HttpHeaders(), HttpStatus.INTERNAL_SERVER_ERROR);

	}

}
