package com.youngandhun.moduleapi.emotion.dto;

import com.youngandhun.modulecore.emotion.domain.EmotionType;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class TodayEmotionResp {

    private EmotionType type;
    private String username;

    @Builder
    public TodayEmotionResp(String username, EmotionType type){
        this.username = username;
        this.type = type;
    }
}
