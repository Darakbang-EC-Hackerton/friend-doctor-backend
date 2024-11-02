package com.youngandhun.moduleapi.emotion.application;

import java.time.LocalDate;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.RequestMapping;

import com.youngandhun.moduleapi.emotion.dto.TodayEmotionReq;
import com.youngandhun.modulecore.emotion.domain.Emotion;
import com.youngandhun.modulecore.emotion.repository.EmotionRepository;
import com.youngandhun.modulecore.member.domain.Member;
import com.youngandhun.modulecore.member.exception.MemberErrorCode;
import com.youngandhun.modulecore.member.exception.MemberException;
import com.youngandhun.modulecore.member.repository.MemberRepository;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Service
@Transactional
public class EmotionService {

	private final EmotionRepository emotionRepository;
	private final MemberRepository memberRepository;

	public void recordTodayEmotion(TodayEmotionReq request) {

		Member member = memberRepository.findById(request.getMemberId())
			.orElseThrow(() -> new MemberException(MemberErrorCode.MEMBER_NOT_FOUND));

		LocalDate today = LocalDate.now();

		emotionRepository.findByMemberAndCreatedAt(member, today)
			.ifPresentOrElse(
			emotion -> {
				emotion.updateEmotion(request.getType());
			},
			() -> {
				Emotion emotion = Emotion.builder()
					.member(member)
					.type(request.getType())
					.build();
				emotionRepository.save(emotion);
			}
		);
	}
}