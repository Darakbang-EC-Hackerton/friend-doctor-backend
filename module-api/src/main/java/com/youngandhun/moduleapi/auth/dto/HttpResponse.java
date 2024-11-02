package com.youngandhun.moduleapi.auth.dto;

import java.io.IOException;

import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;

import com.fasterxml.jackson.databind.ObjectMapper;

import jakarta.servlet.http.HttpServletResponse;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;

@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class HttpResponse {
	private static final ObjectMapper objectMapper = new ObjectMapper();

	public static void sendErrorResponse(HttpServletResponse response, HttpStatus httpStatus, Object body)
			throws IOException {
		response.setContentType(MediaType.APPLICATION_JSON_VALUE);
		response.setStatus(httpStatus.value());
		response.setCharacterEncoding("UTF-8");
		String responseBody = objectMapper.writeValueAsString(body);
		response.getWriter().write(responseBody);
	}
}