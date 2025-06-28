package com.example.auth.domain.comment.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;

import java.time.LocalDateTime;

@Getter
public class CommentResponse {
    @Schema(description = "댓글 ID", example = "1")
    private final Long id;

    @Schema(description = "댓글 내용", example = "좋은 글 감사합니다.")
    private final String content;

    @Schema(description = "작성자 닉네임", example = "태희")
    private final String authorNickname;

    @Schema(description = "작성일시", example = "2025-06-28T19:00:00")
    private final LocalDateTime createdAt;

    public CommentResponse(Long id, String content, String authorNickname,
                           LocalDateTime createdAt) {
        this.id = id;
        this.content = content;
        this.authorNickname = authorNickname;
        this.createdAt = createdAt;
    }
}
