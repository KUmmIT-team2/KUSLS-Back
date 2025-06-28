package com.example.auth.domain.qna.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Getter;

import java.time.LocalDateTime;

@Getter
@AllArgsConstructor
public class QnaSummaryResponse {
    @Schema(description = "QnA 게시글 ID", example = "1")
    private Long id;

    @Schema(description = "QnA 게시글 제목", example = "Spring Boot 의존성 오류 질문")
    private String title;

    @Schema(description = "작성자 학과명", example = "컴퓨터공학과")
    private String departmentName;

    @Schema(description = "작성일시", example = "2025-06-28T16:45:00")
    private LocalDateTime createdAt;

    @Schema(description = "추천 수", example = "8")
    private int recommendCount;

    @Schema(description = "댓글 수", example = "4")
    private int replyCount;
}
