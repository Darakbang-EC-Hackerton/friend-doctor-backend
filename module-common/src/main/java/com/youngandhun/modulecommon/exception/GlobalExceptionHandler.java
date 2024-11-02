package com.youngandhun.modulecommon.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import com.youngandhun.modulecommon.dto.ErrorResponse;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@RestControllerAdvice
public class GlobalExceptionHandler {

	@ExceptionHandler(Exception.class)
	public ResponseEntity<ErrorResponse<Void>> handleException(Exception e) {
		log.warn(e.getMessage(), e);

		HttpStatus status = HttpStatus.INTERNAL_SERVER_ERROR;
		return ResponseEntity.internalServerError()
			.body(ErrorResponse.failure(String.valueOf(status.value()), status.getReasonPhrase()));
	}
}
