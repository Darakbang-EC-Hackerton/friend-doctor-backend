package com.youngandhun.moduleapi.poem.application;

import com.youngandhun.moduleapi.poem.dto.PoemInfoResp;
import com.youngandhun.modulecore.poem.domain.Poem;
import com.youngandhun.modulecore.poem.exception.PoemErrorCode;
import com.youngandhun.modulecore.poem.exception.PoemException;
import com.youngandhun.modulecore.poem.repository.PoemRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;

@Service
@RequiredArgsConstructor
@Transactional
@Slf4j
public class PoemService {

    private final PoemRepository poemRepository;

    @Transactional(readOnly = true)
    public PoemInfoResp findRandomPoemByDate(){
        LocalDate now = LocalDate.now();
        Poem poem = poemRepository.findRandomPoemByDate(now)
            .orElseThrow(() -> new PoemException(PoemErrorCode.POEM_NOT_FOUND_FOR_DATE));

        log.info(poem.toString());

        return PoemInfoResp.builder()
                .author(poem.getAuthor())
                .title(poem.getTitle())
                .content(poem.getContent())
                .build();
    }

}
