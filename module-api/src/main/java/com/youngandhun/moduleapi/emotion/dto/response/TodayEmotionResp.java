package com.youngandhun.moduleapi.emotion.dto.response;

import com.youngandhun.modulecore.emotion.domain.EmotionType;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class TodayEmotionResp {

    @Schema(description = "감정 종류", example = "매우 행복")
    private EmotionType type;
    @Schema(description = "유저명", example = "홍길동")
    private String username;

    @Builder
    public TodayEmotionResp(String username, EmotionType type){
        this.username = username;
        this.type = type;
    }
}
