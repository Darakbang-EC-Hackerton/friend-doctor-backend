package com.youngandhun.moduleapi.emotion.dto.request;

import com.youngandhun.modulecore.emotion.domain.EmotionType;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
public class TodayEmotionReq {

	private Long memberId;
	private EmotionType type;
}
