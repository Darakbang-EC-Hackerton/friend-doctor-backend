package com.youngandhun.modulecore.emotion.exception;

import org.springframework.http.HttpStatus;

import com.youngandhun.modulecore.member.exception.MemberErrorCode;

import lombok.Getter;

@Getter
public class EmotionException extends RuntimeException {

	private final HttpStatus status;
	private final String code;

	public EmotionException(EmotionErrorCode errorCode) {
		super(errorCode.getMessage());
		this.status = errorCode.getHttpStatus();
		this.code = errorCode.getCode();
	}
}
