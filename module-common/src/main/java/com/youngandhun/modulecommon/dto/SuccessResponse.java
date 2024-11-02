package com.youngandhun.modulecommon.dto;


import com.fasterxml.jackson.annotation.JsonInclude;

import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
@JsonInclude(JsonInclude.Include.NON_NULL)
public class SuccessResponse<T> {

	private String code;
	private String message;
	private T result;

	private final static String SUCCESS_CODE = "200";

	public static <T> SuccessResponse<T> success(T result) {
		return SuccessResponse.<T>builder()
			.code(SUCCESS_CODE)
			.result(result)
			.build();
	}

	public static <T> SuccessResponse<T> success(String message) {
		return SuccessResponse.<T>builder()
			.code(SUCCESS_CODE)
			.message(message)
			.build();
	}

	public static <T> SuccessResponse<T> success(String message, T result) {
		return SuccessResponse.<T>builder()
			.code(SUCCESS_CODE)
			.message(message)
			.result(result)
			.build();
	}
}
