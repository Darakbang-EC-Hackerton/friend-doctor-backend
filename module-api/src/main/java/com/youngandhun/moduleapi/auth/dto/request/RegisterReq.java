package com.youngandhun.moduleapi.auth.dto.request;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class RegisterReq {

	@Schema(description = "이메일", example = "test@test.ac.kr")
	private String email;
	@Schema(description = "유저명", example = "홍길동")
	private String username;
	@Schema(description = "비밀번호", example = "test1234!!")
	private String password;
	@Schema(description = "휴대폰 번호", example = "01039457136")
	private String mobileNumber;

	@Builder
	public RegisterReq(String email, String username, String password, String mobileNumber) {
		this.email = email;
		this.username = username;
		this.password = password;
		this.mobileNumber = mobileNumber;
	}
}
