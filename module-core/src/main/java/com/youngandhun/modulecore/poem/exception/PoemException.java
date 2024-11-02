package com.youngandhun.modulecore.poem.exception;

import org.springframework.http.HttpStatus;

import lombok.Getter;

@Getter
public class PoemException extends RuntimeException {

	private final HttpStatus status;
	private final String code;

	public PoemException(PoemErrorCode errorCode) {
		super(errorCode.getMessage());
		this.status = errorCode.getHttpStatus();
		this.code = errorCode.getCode();
	}
}
