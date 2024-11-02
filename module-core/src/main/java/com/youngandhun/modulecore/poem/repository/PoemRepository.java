package com.youngandhun.modulecore.poem.repository;

import com.youngandhun.modulecore.poem.domain.Poem;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.time.LocalDate;
import java.util.Optional;

public interface PoemRepository extends JpaRepository<Poem, Long> {

    @Query(value = "SELECT * FROM poem "
        + "WHERE date = :date "
        + "ORDER BY RAND() LIMIT 1 "
        , nativeQuery = true)
    Optional<Poem> findRandomPoemByDate(LocalDate date);
}
