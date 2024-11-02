package com.youngandhun.modulecore.member.exception;

import org.springframework.http.HttpStatus;

import com.youngandhun.modulecommon.exception.BaseErrorCode;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum MemberErrorCode implements BaseErrorCode {

	MEMBER_NOT_FOUND(HttpStatus.NOT_FOUND, "MEMBER_404_0", "존재하지 않는 사용자입니다."),
	JOIN_DATE_NOT_FOUND(HttpStatus.BAD_REQUEST, "MEMBER_400_1", "가입 날짜가 존재하지 않습니다."),
	EMAIL_ALREADY_EXISTS(HttpStatus.CONFLICT, "MEMBER_409_0", "이미 가입된 이메일입니다.");

	private final HttpStatus httpStatus;
	private final String code;
	private final String message;
}
