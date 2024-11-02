package com.youngandhun.modulecore.poem.exception;

import org.springframework.http.HttpStatus;

import com.youngandhun.modulecommon.exception.BaseErrorCode;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum PoemErrorCode implements BaseErrorCode {
	POEM_NOT_FOUND_FOR_DATE(HttpStatus.NOT_FOUND, "POEM_404_0", "해당 날짜의 시를 찾을 수 없습니다.");

	private final HttpStatus httpStatus;
	private final String code;
	private final String message;
}
