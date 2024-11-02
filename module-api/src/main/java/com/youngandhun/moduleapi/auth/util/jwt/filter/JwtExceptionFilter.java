package com.youngandhun.moduleapi.auth.util.jwt.filter;

import java.io.IOException;

import org.springframework.http.HttpStatus;
import org.springframework.web.filter.OncePerRequestFilter;

import com.youngandhun.moduleapi.auth.dto.HttpResponse;
import com.youngandhun.moduleapi.auth.exception.CustomSecurityException;
import com.youngandhun.moduleapi.auth.exception.SecurityErrorCode;
import com.youngandhun.modulecommon.dto.ErrorResponse;
import com.youngandhun.modulecommon.dto.SuccessResponse;

import jakarta.servlet.FilterChain;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.NonNull;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class JwtExceptionFilter extends OncePerRequestFilter {

	@Override
	protected void doFilterInternal(
		@NonNull HttpServletRequest request,
		@NonNull HttpServletResponse response,
		@NonNull FilterChain filterChain) throws IOException {
		try {
			filterChain.doFilter(request, response);
		} catch (CustomSecurityException e) {
			ErrorResponse<Void> errorResponse = ErrorResponse.failure(
				e.getCode(),
				e.getMessage()
			);
			HttpResponse.sendErrorResponse(
				response,
				e.getStatus(),
				errorResponse
			);
		} catch (Exception e) {
			HttpResponse.sendErrorResponse(
				response,
				HttpStatus.INTERNAL_SERVER_ERROR,
				HttpStatus.INTERNAL_SERVER_ERROR.getReasonPhrase()
			);
		}
	}
}
