package com.example.auth.domain.bookmark.dto;

import com.example.auth.domain.bookmark.BookmarkableType;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

@Getter @Setter
public class BookmarkCreateRequest {
    @NotNull
    @Schema(description = "북마크 대상 타입", example = "CommunityPost")
    private BookmarkableType bookmarkableType;

    @NotNull
    @Schema(description = "북마크 대상 ID", example = "1")
    private Long bookmarkableId;
}
