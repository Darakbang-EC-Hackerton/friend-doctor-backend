package com.youngandhun.moduleapi.auth.util.jwt.filter;

import java.io.IOException;

import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.filter.OncePerRequestFilter;

import com.youngandhun.moduleapi.auth.domain.CustomUserDetails;
import com.youngandhun.moduleapi.auth.exception.CustomSecurityException;
import com.youngandhun.moduleapi.auth.exception.SecurityErrorCode;
import com.youngandhun.moduleapi.auth.util.jwt.JwtProvider;

import io.jsonwebtoken.ExpiredJwtException;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@RequiredArgsConstructor
public class JwtAuthenticationFilter extends OncePerRequestFilter {

	private final JwtProvider jwtProvider;

	@Override
	protected void doFilterInternal(
		@NonNull HttpServletRequest request,
		@NonNull HttpServletResponse response,
		@NonNull FilterChain filterChain
	) throws ServletException, IOException {
		try {
			// 임시
			String accessToken = "eyJhbGciOiJIUzUxMiJ9.eyJzdWIiOiIxIiwiZW1haWwiOiJ0ZXN0QHRlc3QuYWMua3IiLCJ1c2VybmFtZSI6IuuwsOyerO2biCIsImlhdCI6MTczMDU2OTgxMSwiZXhwIjoxNzMxNDMzODExfQ.Vxnni89PLXiJjBpcZrd7nOjTo1hs_19kXG6ScFrZ-49tjv6NQ4X1aD6EtfiZdrAX655LohZiTzw9Ey6FolvDMQ";
			// String accessToken = jwtProvider.resolveAccessToken(request);
			//
			// if (accessToken == null) {
			// 	filterChain.doFilter(request, response);
			// 	return;
			// }

			// 정상 토큰인 경우 인증 처리
			authenticateAccessToken(accessToken);
			filterChain.doFilter(request, response);
		} catch (ExpiredJwtException e) {
			throw new CustomSecurityException(SecurityErrorCode.EXPIRED_TOKEN);
		}
	}

	private void authenticateAccessToken(String accessToken) {
		CustomUserDetails userDetails = new CustomUserDetails(
			jwtProvider.getId(accessToken),
			jwtProvider.getEmail(accessToken),
			jwtProvider.getAuthority(accessToken)
		);

		// 인증용 객체 생성
		UsernamePasswordAuthenticationToken authentication = new UsernamePasswordAuthenticationToken(
			userDetails,
			null,
			userDetails.getAuthorities());

		// authentication 객체를 security context holder에 저장
		SecurityContextHolder.getContext().setAuthentication(authentication);
	}
}
