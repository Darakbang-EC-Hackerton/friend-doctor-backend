package com.youngandhun.moduleapi.poem.presentation;


import com.youngandhun.moduleapi.poem.application.PoemService;
import com.youngandhun.moduleapi.poem.dto.PoemInfoResp;
import com.youngandhun.modulecommon.dto.SuccessResponse;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Tag(name = "Poem", description = "시 관련 API")
@RestController
@RequestMapping("/api/v1/poems")
@RequiredArgsConstructor
public class PoemController {

    private final PoemService poemService;

    @Operation(summary = "오늘의 랜덤 시 조회")
    @GetMapping
    public ResponseEntity<SuccessResponse<PoemInfoResp>> getRandomPoemByDate(){

        PoemInfoResp poemInfoResp = poemService.findRandomPoemByDate();
        return ResponseEntity.ok(SuccessResponse.success(poemInfoResp));
    }
}
