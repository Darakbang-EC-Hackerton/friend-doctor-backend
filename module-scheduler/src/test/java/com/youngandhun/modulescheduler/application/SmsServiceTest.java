package com.youngandhun.modulescheduler.application;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class SmsServiceTest {

	@Autowired
	SmsService smsService;

	@Test
	public void sendMessage() throws Exception{

		smsService.sendEncouragementMessage();
	}
}