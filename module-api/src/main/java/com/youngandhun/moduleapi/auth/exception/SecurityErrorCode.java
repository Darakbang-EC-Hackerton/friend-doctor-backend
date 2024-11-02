package com.youngandhun.moduleapi.auth.exception;

import org.springframework.http.HttpStatus;

import com.youngandhun.modulecommon.exception.BaseErrorCode;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum SecurityErrorCode implements BaseErrorCode {

	INVALID_TOKEN(HttpStatus.BAD_REQUEST, "SECURITY_400_0", "토큰 형식이 올바르지 않습니다."),
	UNAUTHORIZED_ACCESS(HttpStatus.UNAUTHORIZED, "SECURITY_401_0", "사용자 인증이 필요합니다."),
	EXPIRED_TOKEN(HttpStatus.UNAUTHORIZED, "SECURITY_401_1", "토큰이 만료되었습니다."),
	INVALID_PASSWORD(HttpStatus.UNAUTHORIZED, "SECURITY_401_2", "비밀번호가 일치하지 않습니다."),
	ACCESS_DENIED(HttpStatus.FORBIDDEN, "SECURITY_403_0", "해당 요청에 대한 접근 권한이 없습니다.");

	private final HttpStatus httpStatus;
	private final String code;
	private final String message;
}
