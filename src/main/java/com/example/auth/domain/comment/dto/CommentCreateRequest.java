package com.example.auth.domain.comment.dto;

import com.example.auth.domain.comment.CommentableType;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

@Getter @Setter
public class CommentCreateRequest {
    @NotNull
    private CommentableType commentableType;

    @NotNull
    private Long commentableId;

    @NotNull
    private String content;
}
