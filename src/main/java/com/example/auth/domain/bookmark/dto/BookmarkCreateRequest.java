package com.example.auth.domain.bookmark.dto;

import com.example.auth.domain.bookmark.BookmarkableType;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

@Getter @Setter
public class BookmarkCreateRequest {
    @NotNull
    private BookmarkableType bookmarkableType;

    @NotNull
    private Long bookmarkableId;
}
