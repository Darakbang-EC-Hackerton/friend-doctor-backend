package com.youngandhun.modulecore.emotion.exception;

import org.springframework.http.HttpStatus;

import com.youngandhun.modulecommon.exception.BaseErrorCode;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum EmotionErrorCode implements BaseErrorCode {

	EMOTION_INVALID_TYPE(HttpStatus.BAD_REQUEST, "EMOTION_400_0", "잘못된 감정 유형입니다.");

	private final HttpStatus httpStatus;
	private final String code;
	private final String message;
}
