package com.example.auth.domain.bookmark.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;

import java.time.LocalDateTime;

@Getter
@Schema(description = "북마크 응답 DTO")
public class BookmarkResponse {
    @Schema(description = "북마크 ID", example = "1")
    private final Long id;

    @Schema(description = "북마크를 한 유저의 이름", example = "건황소")
    private final String bookmarker;

    @Schema(description = "생성날짜", example = "2025-06-28T16:45:00")
    private final LocalDateTime createdAt;

    public BookmarkResponse(Long id, String bookmarker,
                           LocalDateTime createdAt) {
        this.id = id;
        this.bookmarker = bookmarker;
        this.createdAt = createdAt;
    }
}
