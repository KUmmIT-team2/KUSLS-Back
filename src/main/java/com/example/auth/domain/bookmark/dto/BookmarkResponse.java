package com.example.auth.domain.bookmark.dto;

import lombok.Getter;

import java.time.LocalDateTime;

@Getter
public class BookmarkResponse {
    private final Long id;
    private final String bookmarker;
    private final LocalDateTime createdAt;

    public BookmarkResponse(Long id, String bookmarker,
                           LocalDateTime createdAt) {
        this.id = id;
        this.bookmarker = bookmarker;
        this.createdAt = createdAt;
    }
}
