package com.youngandhun.modulescheduler.scheduler;

import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import com.youngandhun.modulescheduler.application.SmsService;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@RequiredArgsConstructor
@Component
public class SmsScheduler {

	private final SmsService smsService;

	// @Scheduled(cron = "*/10 * * * * *") // 10초마다 실행
	@Scheduled(cron = "0 0 18 1 * *") // 매월 1일 18시
	public void sendEncouragementMessage() {
		smsService.sendEncouragementMessage();
	}
}