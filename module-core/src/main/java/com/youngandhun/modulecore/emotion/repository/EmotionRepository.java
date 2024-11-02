package com.youngandhun.modulecore.emotion.repository;

import java.time.LocalDate;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.youngandhun.modulecore.emotion.domain.Emotion;
import com.youngandhun.modulecore.member.domain.Member;

public interface EmotionRepository extends JpaRepository<Emotion, Long> {

	@Query("SELECT e FROM Emotion e " +
		"WHERE e.member = :member AND FUNCTION('DATE', e.createdAt) = :createdAt")
	Optional<Emotion> findByMemberAndCreatedAt(Member member, LocalDate createdAt);
}
