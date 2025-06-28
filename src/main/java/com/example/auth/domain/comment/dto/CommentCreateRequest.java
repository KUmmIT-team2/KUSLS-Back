package com.example.auth.domain.comment.dto;

import com.example.auth.domain.comment.CommentableType;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

@Getter @Setter
public class CommentCreateRequest {
    @NotNull
    @Schema(description = "댓글이 달리는 대상 타입", example = "CommunityPost")
    private CommentableType commentableType;

    @NotNull
    @Schema(description = "댓글이 달리는 대상 ID", example = "1")
    private Long commentableId;

    @NotNull
    @Schema(description = "댓글 내용", example = "저도 이렇게 생각합니다!")
    private String content;
}
