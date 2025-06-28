package com.example.auth.domain.community.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
@AllArgsConstructor
public class CommunityResponse {
    @Schema(description = "게시글 ID", example = "1")
    private Long id;

    @Schema(description = "게시글 제목", example = "개발 스터디 모집합니다")
    private String title;

    @Schema(description = "게시글 내용", example = "같이 공부하실 분 구합니다.")
    private String content;

    @Schema(description = "작성자 닉네임", example = "태희")
    private String writerNickname;

    @Schema(description = "작성일시", example = "2025-06-28T17:45:00")
    private LocalDateTime createdAt;

    @Schema(description = "북마크 수", example = "5")
    private int bookmarkCount;

    @Schema(description = "추천 수", example = "2")
    private int recommendCount;

    @Schema(description = "댓글 수", example = "1")
    private int replyCount;
}