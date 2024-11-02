package com.youngandhun.moduleapi.poem.dto;

import lombok.*;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class PoemInfoResp {
    private String author;
    private String title;
    private String content;

    @Builder
    public PoemInfoResp(String author, String title, String content) {
        this.author = author;
        this.title = title;
        this.content = content;
    }
}
