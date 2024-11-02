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

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@RestController
@RequestMapping("/api/v1/members")
@Tag(name = "Member", description = "회원 관련 API")
public class MemberController {

	private static final Logger log = LoggerFactory.getLogger(MemberController.class);
	private final MemberService memberServcie;

	@Operation(summary = "회원가입")
	@PostMapping("/register")
	public ResponseEntity<SuccessResponse<String>> register(@RequestBody RegisterReq request) {

		log.info(request.getEmail());
		memberServcie.register(request);
		return ResponseEntity.ok(SuccessResponse.success("회원가입에 성공하였습니다."));
	}
}
