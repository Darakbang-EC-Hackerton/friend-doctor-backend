package com.youngandhun.moduleapi.emotion.application;

import java.time.LocalDate;
import java.util.List;

import com.youngandhun.moduleapi.emotion.dto.TodayEmotionResp;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.youngandhun.moduleapi.emotion.dto.request.MonthlyEmotionReq;
import com.youngandhun.moduleapi.emotion.dto.response.MonthlyEmotionResp;
import com.youngandhun.moduleapi.emotion.dto.request.TodayEmotionReq;
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


	public TodayEmotionResp getTodayEmotion(TodayEmotionReq request) {

		Member member = memberRepository.findById(request.getMemberId())
				.orElseThrow(() -> new MemberException(MemberErrorCode.MEMBER_NOT_FOUND));

		LocalDate today = LocalDate.now();

		    return emotionRepository.findByMemberAndCreatedAt(member, today)
		            .map(emotion -> TodayEmotionResp.builder()
		                    .username(member.getUsername())
		                    .type(emotion.getType())
		                    .build())
		            .orElseGet(() -> TodayEmotionResp.builder()
		                    .username(member.getUsername())
		                    .type(null)
		                    .build());


	@Transactional(readOnly = true)
	public MonthlyEmotionResp getMonthlyEmotion(MonthlyEmotionReq request) {
		Member member = memberRepository.findById(request.getMemberId())
			.orElseThrow(() -> new MemberException(MemberErrorCode.MEMBER_NOT_FOUND));

		List<Emotion> emotions = emotionRepository.findAllByMemberAndYearAndMonth(member,
			request.getYear(), request.getMonth());

		return MonthlyEmotionResp.builder()
			.year(request.getYear())
			.month(request.getMonth())
			.emotions(emotions)
			.build();
	}
}
