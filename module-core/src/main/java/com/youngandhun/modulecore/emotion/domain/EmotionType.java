package com.youngandhun.modulecore.emotion.domain;

import static com.youngandhun.modulecore.emotion.exception.EmotionErrorCode.*;

import java.util.HashMap;
import java.util.Map;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonValue;
import com.youngandhun.modulecore.emotion.exception.EmotionErrorCode;
import com.youngandhun.modulecore.emotion.exception.EmotionException;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Getter
public enum EmotionType {
	VERY_HAPPY("매우 행복"),
	HAPPY("행복"),
	MID("보통"),
	SAD("슬픔"),
	ANGRY("매우 슬픔");

	private final String name;

	private static final Map<String, EmotionType> NAME_TO_ENUM_MAP = new HashMap<>();

	static {
		for (EmotionType type : EmotionType.values()) {
			NAME_TO_ENUM_MAP.put(type.name, type);
		}
	}

	@JsonValue
	public String getName() { return name; }

	@JsonCreator
	public static EmotionType fromName(String name) {
		EmotionType emotionType = NAME_TO_ENUM_MAP.get(name);
		if (emotionType == null) throw new EmotionException(EMOTION_INVALID_TYPE);

		return emotionType;
	}
}
