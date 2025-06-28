package com.example.auth.domain.bookmark.dto;

import com.example.auth.domain.bookmark.BookmarkableType;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Schema(description = "스크랩 DTO")
public class BookmarkCreateRequest {
    @NotNull
    @Schema(description = "스크랩 종류", example = "Community")
    private BookmarkableType bookmarkableType;

    @NotNull
    @Schema(description = "스크랩 게시글 ID", example = "1")
    private Long bookmarkableId;
}
