package com.youngandhun.moduleapi.member.application;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.youngandhun.moduleapi.member.dto.RegisterReq;
import com.youngandhun.modulecore.member.domain.Member;
import com.youngandhun.modulecore.member.exception.MemberErrorCode;
import com.youngandhun.modulecore.member.exception.MemberException;
import com.youngandhun.modulecore.member.repository.MemberRepository;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Service
@Transactional
public class MemberService {

	private final MemberRepository memberRepository;

	public void register(RegisterReq request) {
		if (memberRepository.existsByEmail(request.getEmail())) {
			throw new MemberException(MemberErrorCode.EMAIL_ALREADY_EXISTS);
		}

		Member newMember = Member.builder()
			.email(request.getEmail())
			.username(request.getUsername())
			.password(request.getPassword())
			.mobileNumber(request.getMobileNumber())
			.build();

		memberRepository.save(newMember);
	}
}
