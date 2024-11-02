package com.youngandhun.moduleapi.emotion.dto.response;

import java.util.List;

import com.youngandhun.modulecore.emotion.domain.Emotion;
import com.youngandhun.modulecore.emotion.domain.EmotionType;

import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;

@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
public class MonthlyEmotionResp {

	private int year;
	private int month;
	private List<EmotionInfo> emotions;

	@Getter
	public static class EmotionInfo {

		private int day;
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
	}

}
