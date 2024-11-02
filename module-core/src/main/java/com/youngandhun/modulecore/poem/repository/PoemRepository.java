package com.youngandhun.modulecore.poem.repository;

import com.youngandhun.modulecore.member.domain.Member;
import com.youngandhun.modulecore.poem.domain.Poem;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDate;
import java.util.Optional;

public interface PoemRepository extends JpaRepository<Poem, Long> {
    Optional<Poem> findByDate(LocalDate date);

}
