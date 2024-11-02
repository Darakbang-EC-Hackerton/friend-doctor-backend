package com.youngandhun.moduleapi.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import com.youngandhun.modulecommon.dto.ErrorResponse;
import com.youngandhun.modulecore.emotion.exception.EmotionErrorCode;
import com.youngandhun.modulecore.emotion.exception.EmotionException;
import com.youngandhun.modulecore.member.exception.MemberException;
import com.youngandhun.modulecore.poem.exception.PoemException;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@RestControllerAdvice
public class GlobalExceptionHandler {

	@ExceptionHandler(HttpMessageNotReadableException.class)
	public ResponseEntity<ErrorResponse<Void>> handleHttpMessageNotReadableException(HttpMessageNotReadableException e) {
		if (e.getMessage().contains("EmotionType")) {
			EmotionException exception = new EmotionException(EmotionErrorCode.EMOTION_INVALID_TYPE);

			return ResponseEntity.status(exception.getStatus())
				.body(ErrorResponse.failure(exception.getCode(), exception.getMessage()));
		}

		return ResponseEntity.badRequest().body(ErrorResponse.failure("400", "잘못된 요청입니다."));
	}

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

	@ExceptionHandler(PoemException.class)
	public ResponseEntity<ErrorResponse<Void>> handlePoemException(PoemException e) {
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
