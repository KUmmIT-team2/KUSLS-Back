package com.example.auth.domain.qna.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
@AllArgsConstructor
public class QnaResponse {
    @Schema(description = "QnA 게시글 ID", example = "1")
    private Long id;

    @Schema(description = "QnA 게시글 제목", example = "Spring Boot 질문입니다.")
    private String title;

    @Schema(description = "작성자 username", example = "kroad0129")
    private String writerUsername;

    @Schema(description = "작성일시", example = "2025-06-28T15:30:00")
    private LocalDateTime createdAt;

    @Schema(description = "북마크 수", example = "12")
    private int bookmarkCount;

    @Schema(description = "추천 수", example = "5")
    private int recommendCount;

    @Schema(description = "댓글 수", example = "3")
    private int replyCount;
}