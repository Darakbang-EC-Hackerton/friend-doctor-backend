package com.youngandhun.modulecore.emotion.domain;

import com.youngandhun.modulecommon.domain.BaseEntity;
import com.youngandhun.modulecore.member.domain.Member;

import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
@Entity
public class Emotion extends BaseEntity {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long emotionId;

	@Enumerated(EnumType.STRING)
	private EmotionType type;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "member_id", nullable = false)
	private Member member;

	@Builder
	public Emotion(EmotionType type, Member member) {
		this.type = type;
		this.member = member;
	}

	public void updateEmotion(EmotionType type) {
		this.type = type;
	}
}
