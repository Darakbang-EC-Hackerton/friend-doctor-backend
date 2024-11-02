package com.youngandhun.modulecore.emotion.repository;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.youngandhun.modulecore.emotion.domain.Emotion;
import com.youngandhun.modulecore.member.domain.Member;

public interface EmotionRepository extends JpaRepository<Emotion, Long> {

	@Query("SELECT e FROM Emotion e " +
		"WHERE e.member = :member AND FUNCTION('DATE', e.createdAt) = :createdAt")
	Optional<Emotion> findByMemberAndCreatedAt(Member member, LocalDate createdAt);

	@Query("SELECT e FROM Emotion e WHERE e.member = :member " +
		"AND FUNCTION('YEAR', e.createdAt) = :year " +
		"AND FUNCTION('MONTH', e.createdAt) = :month")
	List<Emotion> findAllByMemberAndYearAndMonth(Member member, int year, int month);
}
