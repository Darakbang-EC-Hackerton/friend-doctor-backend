package com.youngandhun.moduleapi.emotion.dto.request;

import com.youngandhun.modulecore.emotion.domain.EmotionType;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
public class TodayEmotionReq {
	@Schema(description = "회원 ID", example = "1")
	private Long memberId;
	@Schema(description = "감정 종류(매우 행복/행복/보통/슬픔/매우 슬픔)", example = "매우 행복")
	private EmotionType type;
}
