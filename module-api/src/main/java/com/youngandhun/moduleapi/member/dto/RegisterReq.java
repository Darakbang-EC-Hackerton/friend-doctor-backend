package com.youngandhun.moduleapi.member.dto;

import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class RegisterReq {

	private String email;
	private String username;
	private String password;
	private String mobileNumber;

	@Builder
	public RegisterReq(String email, String username, String password, String mobileNumber) {
		this.email = email;
		this.username = username;
		this.password = password;
		this.mobileNumber = mobileNumber;
	}
}
