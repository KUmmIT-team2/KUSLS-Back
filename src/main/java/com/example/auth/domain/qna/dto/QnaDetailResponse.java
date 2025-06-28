package com.example.auth.domain.qna.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
@AllArgsConstructor
public class QnaDetailResponse {
    @Schema(description = "QnA 게시글 ID", example = "1")
    private Long id;

    @Schema(description = "QnA 게시글 제목", example = "Spring Boot 질문 있습니다.")
    private String title;

    @Schema(description = "작성자 username", example = "kroad0129")
    private String writerUsername;

    @Schema(description = "작성자 소속 대학명", example = "공과대학")
    private String collegeName;

    @Schema(description = "작성자 소속 학과명", example = "컴퓨터공학과")
    private String departmentName;

    @Schema(description = "작성일시", example = "2025-06-28T12:34:56")
    private LocalDateTime createdAt;

    @Schema(description = "북마크 수", example = "10")
    private int bookmarkCount;

    @Schema(description = "추천 수", example = "5")
    private int recommendCount;

    @Schema(description = "댓글 수", example = "3")
    private int replyCount;
}
