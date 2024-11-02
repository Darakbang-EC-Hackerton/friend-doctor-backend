package com.youngandhun.moduleapi.emotion.dto.request;

import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
public class MonthlyEmotionReq {

	private int year;
	private int month;
	private Long memberId;

	@Builder
	public MonthlyEmotionReq(int year, int month, Long memberId) {
		this.year = year;
		this.month = month;
		this.memberId = memberId;
	}
}
