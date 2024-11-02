package com.youngandhun.moduleapi.auth.util.jwt;

import java.io.IOException;

import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.stereotype.Component;

import com.youngandhun.moduleapi.auth.dto.HttpResponse;
import com.youngandhun.moduleapi.auth.exception.SecurityErrorCode;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;

/**
 * 인증(Authentication) 예외가 발생했을 때 예외 처리 -> 인증되지 않은 사용자가 접근하려고 하는 경우
 */
@Slf4j
@Component
public class JwtAuthenticationEntryPoint implements AuthenticationEntryPoint {

	@Override
	public void commence(HttpServletRequest request, HttpServletResponse response,
		AuthenticationException authException)
		throws IOException {

		SecurityErrorCode errorCode = SecurityErrorCode.UNAUTHORIZED_ACCESS;
		HttpResponse.sendErrorResponse(response, errorCode.getHttpStatus(),
			errorCode.getMessage());
	}
}