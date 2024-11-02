package com.youngandhun.moduleapi.emotion.dto.response;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import com.youngandhun.modulecore.emotion.domain.Emotion;
import com.youngandhun.modulecore.emotion.domain.EmotionType;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;

@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
public class MonthlyEmotionResp {

	@Schema(description = "년도", example = "2024")
	private int year;
	@Schema(description = "월", example = "11")
	private int month;
	private List<EmotionInfo> emotions;
	@Schema(description = "감정 종류별 수")
	private Map<EmotionType, Long> emotionCount;

	@Getter
	public static class EmotionInfo {

		@Schema(description = "날짜", example = "1")
		private int day;
		@Schema(description = "감정 종류", example = "매우 행복")
		private EmotionType type;

		public EmotionInfo(int day, EmotionType type) {
			this.day = day;
			this.type = type;
		}

		public static EmotionInfo from(Emotion emotion) {
			return new EmotionInfo(emotion.getCreatedAt().getDayOfMonth(), emotion.getType());
		}
	}

	@Builder
	public MonthlyEmotionResp(int year, int month, List<Emotion> emotions) {
		this.year = year;
		this.month = month;
		this.emotions = emotions.stream()
			.map(EmotionInfo::from)
			.toList();
		this.emotionCount = emotions.stream()
			.collect(Collectors.groupingBy(Emotion::getType, Collectors.counting()));
	}

}
