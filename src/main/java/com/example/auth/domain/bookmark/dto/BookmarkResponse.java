package com.example.auth.domain.bookmark.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;

import java.time.LocalDateTime;

@Getter
public class BookmarkResponse {
    @Schema(description = "북마크 ID", example = "1")
    private final Long id;

    @Schema(description = "북마크한 사용자 username", example = "kroad0129")
    private final String bookmarker;

    @Schema(description = "북마크 생성일시", example = "2025-06-28T19:30:00")
    private final LocalDateTime createdAt;

    public BookmarkResponse(Long id, String bookmarker,
                           LocalDateTime createdAt) {
        this.id = id;
        this.bookmarker = bookmarker;
        this.createdAt = createdAt;
    }
}
