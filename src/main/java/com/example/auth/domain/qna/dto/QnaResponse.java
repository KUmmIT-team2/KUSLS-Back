package com.example.auth.domain.qna.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
@AllArgsConstructor
public class QnaResponse {
    private Long id;
    private String title;
    private String content;
    private String writerNickname;
    private LocalDateTime createdAt;
    @JsonProperty("isAnswered")
    private boolean isAnswered;
}