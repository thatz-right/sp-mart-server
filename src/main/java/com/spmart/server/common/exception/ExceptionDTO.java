package com.spmart.server.common.exception;

import lombok.Builder;

public class ExceptionDTO {
	private final String message;
	private final String code;

	@Builder
	public ExceptionDTO(StatusCode statusCode) {
		this.message = statusCode.getMessage();
		this.code = statusCode.getCode();
	}
}
