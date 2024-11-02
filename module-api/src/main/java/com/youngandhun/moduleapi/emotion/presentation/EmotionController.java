package com.youngandhun.moduleapi.emotion.presentation;

import com.youngandhun.moduleapi.auth.domain.CustomUserDetails;
import com.youngandhun.moduleapi.emotion.dto.response.TodayEmotionResp;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.youngandhun.moduleapi.emotion.application.EmotionService;
import com.youngandhun.moduleapi.emotion.dto.response.MonthlyEmotionResp;
import com.youngandhun.moduleapi.emotion.dto.request.TodayEmotionReq;
import com.youngandhun.modulecommon.config.SwaggerConfig;
import com.youngandhun.modulecommon.dto.SuccessResponse;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@RestController
@RequestMapping("/api/v1/emotions")
@Tag(name = "Emotion", description = "감정 관련 API")
public class EmotionController {

	private final EmotionService emotionService;

	@Operation(summary = "오늘의 감정 기록",
		security = @SecurityRequirement(name = SwaggerConfig.JWT_SECURITY_SCHEME))
	@PostMapping
	public ResponseEntity<SuccessResponse<String>> recordTodayEmotion(
		@RequestBody TodayEmotionReq request,
		@AuthenticationPrincipal CustomUserDetails user) {
		emotionService.recordTodayEmotion(request, user.getId());
		return ResponseEntity.ok(SuccessResponse.success("오늘의 감정 기록에 성공하였습니다."));
	}



	@Operation(summary = "오늘의 감정 조회",
		security = @SecurityRequirement(name = SwaggerConfig.JWT_SECURITY_SCHEME))
	@GetMapping("/today")
	public ResponseEntity<SuccessResponse<TodayEmotionResp>> getTodayEmotion(
		@AuthenticationPrincipal CustomUserDetails user
	) {
		TodayEmotionResp todayEmotionResp = emotionService.getTodayEmotion(user.getId());
		return ResponseEntity.ok(SuccessResponse.success(todayEmotionResp));
	}
	
	@Operation(summary = "월간 감정 기록 조회",
		security = @SecurityRequirement(name = SwaggerConfig.JWT_SECURITY_SCHEME))
	@GetMapping("/monthly")
	public ResponseEntity<SuccessResponse<MonthlyEmotionResp>> getMonthlyEmotion(
		@Schema(description = "년도", example = "2024")
		@RequestParam int year,
		@Schema(description = "월", example = "11")
		@RequestParam int month,
		@AuthenticationPrincipal CustomUserDetails user
	) {
		MonthlyEmotionResp emotions = emotionService.getMonthlyEmotion(year, month, user.getId());
		return ResponseEntity.ok(SuccessResponse.success(emotions));
	}
}
