package com.spmart.server.common.exception;

import com.spmart.server.common.dto.StatusMessage;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GlobalExceptionHandler {

	private ResponseEntity<StatusMessage> createResponse(StatusCode statusCode) {
		return ResponseEntity
				.status(statusCode.getHttpStatus())
				.body(new StatusMessage(statusCode));
	}

	@ExceptionHandler(Exception.class)
	public ResponseEntity<StatusMessage> handleException(Exception e) {
		e.printStackTrace();

		return createResponse(ExceptionFactory.getInstance(e));
	}
}
