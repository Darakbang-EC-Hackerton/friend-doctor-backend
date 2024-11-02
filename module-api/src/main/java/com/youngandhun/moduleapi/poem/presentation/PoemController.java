package com.youngandhun.moduleapi.poem.presentation;


import com.youngandhun.moduleapi.poem.application.PoemService;
import com.youngandhun.moduleapi.poem.dto.PoemInfoResp;
import com.youngandhun.modulecommon.dto.SuccessResponse;
import com.youngandhun.modulecore.poem.domain.Poem;
import com.youngandhun.modulecore.poem.repository.PoemRepository;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDate;
import java.util.Optional;

@Tag(name = "Poem", description = "시 관련 API")
@RestController
@RequestMapping("/api/v1/poems")
@RequiredArgsConstructor
public class PoemController {

    private final PoemService poemService;

    @Operation(summary = "오늘의 시 조회")
    @GetMapping
    public ResponseEntity<SuccessResponse<PoemInfoResp>> getPoemByDate(){

        PoemInfoResp poemInfoResp = poemService.findByDate();
        return ResponseEntity.ok(SuccessResponse.success(poemInfoResp));
    }
}
