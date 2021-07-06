package com.spmart.server.common.exception;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GlobalExceptionHandler {

	private ResponseEntity<ExceptionDTO> createResponse(StatusCode statusCode) {
		ExceptionDTO exceptionDTO = ExceptionDTO.builder().statusCode(statusCode).build();

		return new ResponseEntity<>(exceptionDTO, statusCode.getHttpStatus());
	}

	@ExceptionHandler(Exception.class)
	public ResponseEntity<ExceptionDTO> handleException(Exception e) {
		e.printStackTrace();

		return createResponse(ExceptionFactory.getInstance(e));
	}
}
