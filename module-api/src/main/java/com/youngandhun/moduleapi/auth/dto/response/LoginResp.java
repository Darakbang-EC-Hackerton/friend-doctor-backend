package com.youngandhun.moduleapi.auth.dto.response;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class LoginResp {

	@Schema(description = "이메일", example = "test@test.ac.kr")
	String email;
	@Schema(description = "유저명", example = "홍길동")
	String username;
	@Schema(description = "액세스 토큰", example = "eyJhbGciOiJIUzI1NiJ9.eyJzdQHRlc3QuYWMua3IifQ")
	String accessToken;

	@Builder
	public LoginResp(String email, String username, String accessToken) {
		this.email = email;
		this.username = username;
		this.accessToken = accessToken;
	}
}
