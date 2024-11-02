package com.youngandhun.modulecore.member.domain;

import com.youngandhun.modulecommon.domain.BaseEntity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.Size;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Member extends BaseEntity {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long memberId;

	@Enumerated(EnumType.STRING)
	private Role role;

	@Email
	@Column(nullable = false)
	private String email;

	@Size(min = 2, max = 10)
	@Column(nullable = false)
	private String username;

	@Column(nullable = false)
	private String password;

	@Column(nullable = false)
	private String mobileNumber;

	@Builder
	public Member(String email, String username, String password, String mobileNumber) {
		this.email = email;
		this.username = username;
		this.password = password;
		this.role = Role.USER;
		this.mobileNumber = mobileNumber;
	}
}