package com.youngandhun.moduleapi.auth.presentation;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.youngandhun.moduleapi.auth.application.AuthService;
import com.youngandhun.moduleapi.auth.dto.request.LoginReq;
import com.youngandhun.moduleapi.auth.dto.response.LoginResp;
import com.youngandhun.moduleapi.auth.dto.request.RegisterReq;
import com.youngandhun.modulecommon.dto.SuccessResponse;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Tag(name = "Auth", description = "로그인 관련 API")
@RestController
@RequestMapping("/api/v1/members")
@RequiredArgsConstructor
@Slf4j
public class AuthController {

    private final AuthService authService;

    @Operation(summary = "회원가입")
    @PostMapping("/register")
    public ResponseEntity<SuccessResponse<LoginResp>> register(
            @RequestBody RegisterReq request
    ) {
        authService.register(request);

        return ResponseEntity.ok(SuccessResponse.success("회원가입에 성공하였습니다."));
    }

    @Operation(summary = "로그인")
    @PostMapping("/login")
    public ResponseEntity<SuccessResponse<LoginResp>> login(
             @RequestBody LoginReq request
    ) {
        LoginResp response = authService.login(request.getEmail(), request.getPassword());
        return ResponseEntity.ok(SuccessResponse.success(response));
    }
}
