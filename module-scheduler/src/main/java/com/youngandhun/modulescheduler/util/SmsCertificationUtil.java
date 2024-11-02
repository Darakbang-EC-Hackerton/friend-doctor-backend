package com.youngandhun.modulescheduler.util;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import net.nurigo.sdk.NurigoApp;
import net.nurigo.sdk.message.model.Message;
import net.nurigo.sdk.message.request.SingleMessageSendingRequest;
import net.nurigo.sdk.message.service.DefaultMessageService;

import jakarta.annotation.PostConstruct;

@Component
public class SmsCertificationUtil {

	@Value("${coolsms.apiKey}")
	private String apiKey;

	@Value("${coolsms.apiSecret}")
	private String apiSecret;

	@Value("${coolsms.fromNumber}")
	private String fromNumber;

	DefaultMessageService messageService;

	@PostConstruct
	public void init(){
		this.messageService =
			NurigoApp.INSTANCE.initialize(apiKey, apiSecret, "https://api.coolsms.co.kr");
	}

	// 단일 문자 전송
	public void sendMessage(String to, String msg){
		Message message = new Message();
		message.setFrom(fromNumber); // 발신자 번호
		message.setTo(to); // 수신자 번호
		message.setText(msg);
		this.messageService.sendOne(new SingleMessageSendingRequest(message));
	}
}
