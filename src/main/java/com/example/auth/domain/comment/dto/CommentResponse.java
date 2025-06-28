package com.example.auth.domain.comment.dto;

import com.example.auth.domain.comment.CommentableType;
import lombok.Getter;

import java.time.LocalDateTime;

@Getter
public class CommentResponse {
    private final Long id;
    private final String content;
    private final String authorNickname;
    private final LocalDateTime createdAt;

    public CommentResponse(Long id, String content, String authorNickname,
                           LocalDateTime createdAt) {
        this.id = id;
        this.content = content;
        this.authorNickname = authorNickname;
        this.createdAt = createdAt;
    }
}
