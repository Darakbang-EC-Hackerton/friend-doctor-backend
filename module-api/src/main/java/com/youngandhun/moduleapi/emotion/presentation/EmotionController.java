package com.youngandhun.moduleapi.emotion.presentation;

import com.youngandhun.moduleapi.emotion.dto.TodayEmotionResp;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.youngandhun.moduleapi.emotion.application.EmotionService;
import com.youngandhun.moduleapi.emotion.dto.request.MonthlyEmotionReq;
import com.youngandhun.moduleapi.emotion.dto.response.MonthlyEmotionResp;
import com.youngandhun.moduleapi.emotion.dto.request.TodayEmotionReq;
import com.youngandhun.modulecommon.dto.SuccessResponse;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@RestController
@RequestMapping("/api/v1/emotions")
public class EmotionController {

	private final EmotionService emotionService;

	@PostMapping
	public ResponseEntity<SuccessResponse<String>> recordTodayEmotion(@RequestBody TodayEmotionReq request) {

		emotionService.recordTodayEmotion(request);
		return ResponseEntity.ok(SuccessResponse.success("오늘의 감정 기록에 성공하였습니다."));
	}


	@GetMapping
	public ResponseEntity<SuccessResponse<TodayEmotionResp>> getTodayEmotion(@RequestBody TodayEmotionReq request) {
		TodayEmotionResp todayEmotionResp = emotionService.getTodayEmotion(request);
		return ResponseEntity.ok(SuccessResponse.success(todayEmotionResp));

	@GetMapping("/monthly")
	public ResponseEntity<SuccessResponse<MonthlyEmotionResp>> getMonthlyEmotion(
		@RequestBody MonthlyEmotionReq request
	) {
		MonthlyEmotionResp emotions = emotionService.getMonthlyEmotion(request);
		return ResponseEntity.ok(SuccessResponse.success(emotions));
	}
}
