package com.youngandhun.moduleapi.member.presentation;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.youngandhun.moduleapi.member.application.MemberService;
import com.youngandhun.moduleapi.member.dto.RegisterReq;
import com.youngandhun.modulecommon.dto.SuccessResponse;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@RestController
@RequestMapping("/api/v1/members")
public class MemberController {

	private static final Logger log = LoggerFactory.getLogger(MemberController.class);
	private final MemberService memberServcie;

	@PostMapping("/register")
	public ResponseEntity<SuccessResponse<String>> register(@RequestBody RegisterReq request) {

		log.info(request.getEmail());
		memberServcie.register(request);
		return ResponseEntity.ok(SuccessResponse.success("회원가입에 성공하였습니다."));
	}
}
