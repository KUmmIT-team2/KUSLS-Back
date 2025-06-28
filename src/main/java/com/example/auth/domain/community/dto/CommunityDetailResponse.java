package com.example.auth.domain.community.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
@AllArgsConstructor
public class CommunityDetailResponse {
    @Schema(description = "게시글 ID", example = "1")
    private Long id;

    @Schema(description = "게시글 제목", example = "개발 스터디 모집합니다")
    private String title;

    @Schema(description = "게시글 내용", example = "스프링 부트 스터디 모집 중입니다.")
    private String content;

    @Schema(description = "작성자 username", example = "kroad0129")
    private String writerUsername;

    @Schema(description = "작성자 소속 대학명", example = "공과대학")
    private String collegeName;

    @Schema(description = "작성자 소속 학과명", example = "컴퓨터공학과")
    private String departmentName;

    @Schema(description = "작성일시", example = "2025-06-28T17:30:00")
    private LocalDateTime createdAt;

    @Schema(description = "북마크 수", example = "10")
    private int bookmarkCount;

    @Schema(description = "추천 수", example = "4")
    private int recommendCount;

    @Schema(description = "댓글 수", example = "3")
    private int replyCount;
}
