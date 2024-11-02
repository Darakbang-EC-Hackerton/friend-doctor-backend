package com.youngandhun.moduleapi.auth.exception;

import org.springframework.http.HttpStatus;

import lombok.Getter;

@Getter
public class CustomSecurityException extends RuntimeException {

	private final HttpStatus status;
	private final String code;

	public CustomSecurityException(SecurityErrorCode errorCode) {
		super(errorCode.getMessage());
		this.status = errorCode.getHttpStatus();
		this.code = errorCode.getCode();
	}
}
