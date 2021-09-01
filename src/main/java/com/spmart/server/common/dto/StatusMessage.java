package com.spmart.server.common.dto;

import com.spmart.server.common.exception.StatusCode;

public class StatusDto {
	private String message;
	private String code;

	public StatusDto(StatusCode statusCode) {
		this.message = statusCode.getMessage();
		this.code = statusCode.getCode();
	}
}
