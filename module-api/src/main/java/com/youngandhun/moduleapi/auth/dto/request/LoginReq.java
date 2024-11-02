package com.youngandhun.moduleapi.auth.presentation;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
public class LoginReq {

	@Schema(description = "이메일", example = "test@test.ac.kr")
	private String email;
	@Schema(description = "비밀번호", example = "test1234!!")
	private String password;
}
