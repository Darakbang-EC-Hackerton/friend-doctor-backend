package com.youngandhun.moduleapi.poem.presentation;


import com.youngandhun.moduleapi.poem.application.PoemService;
import com.youngandhun.moduleapi.poem.dto.PoemInfoResp;
import com.youngandhun.modulecommon.dto.SuccessResponse;
import com.youngandhun.modulecore.poem.domain.Poem;
import com.youngandhun.modulecore.poem.repository.PoemRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDate;
import java.util.Optional;

@RestController
@RequestMapping("/api/v1/poems")
@RequiredArgsConstructor
public class PoemController {

    private final PoemService poemService;

    @GetMapping
    public ResponseEntity<SuccessResponse<PoemInfoResp>> getPoemByDate(){

        PoemInfoResp poemInfoResp = poemService.findByDate();
        return ResponseEntity.ok(SuccessResponse.success(poemInfoResp));
    }
}
