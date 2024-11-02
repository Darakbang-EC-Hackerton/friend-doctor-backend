package com.youngandhun.moduleapi.poem.dto;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class PoemInfoResp {
    @Schema(description = "시 작가", example = "권한")
    private String author;
    @Schema(description = "시 제목", example = "행복")
    private String title;
    @Schema(description = "시 내용", example = "얼근히 기분 좋게아버지는 오래간만에 취하였다배나무 밑 분(粉)네집서추탕하고")
    private String content;

    @Builder
    public PoemInfoResp(String author, String title, String content) {
        this.author = author;
        this.title = title;
        this.content = content;
    }
}
