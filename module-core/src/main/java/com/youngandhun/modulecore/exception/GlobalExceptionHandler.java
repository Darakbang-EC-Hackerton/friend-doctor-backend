package com.youngandhun.modulecore.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import com.youngandhun.modulecommon.dto.ErrorResponse;
import com.youngandhun.modulecore.emotion.exception.EmotionException;
import com.youngandhun.modulecore.member.exception.MemberException;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@RestControllerAdvice
public class GlobalExceptionHandler {

	@ExceptionHandler(MemberException.class)
	public ResponseEntity<ErrorResponse<Void>> handleMemberException(MemberException e) {
		log.warn(e.getMessage(), e);
		return ResponseEntity.status(e.getStatus()).body(ErrorResponse.failure(e.getCode(), e.getMessage()));
	}

	@ExceptionHandler(EmotionException.class)
	public ResponseEntity<ErrorResponse<Void>> handleEmotionException(EmotionException e) {
		log.warn(e.getMessage(), e);
		return ResponseEntity.status(e.getStatus()).body(ErrorResponse.failure(e.getCode(), e.getMessage()));
	}

	@ExceptionHandler(Exception.class)
	public ResponseEntity<ErrorResponse<Void>> handleException(Exception e) {
		log.warn(e.getMessage(), e);

		HttpStatus status = HttpStatus.INTERNAL_SERVER_ERROR;
		return ResponseEntity.internalServerError()
			.body(ErrorResponse.failure(String.valueOf(status.value()), status.getReasonPhrase()));
	}
}
