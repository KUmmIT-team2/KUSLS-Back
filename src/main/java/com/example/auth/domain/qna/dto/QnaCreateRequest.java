package com.example.auth.domain.qna.dto;

import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class QnaCreateRequest {
    @NotBlank
    @Schema(description = "게시글 제목", example = "컴퓨터공학과 질문!")
    private String title;

    @Schema(description = "대학", example = "4")
    private Long collegeId;

    @Schema(description = "학과", example = "15")
    private Long departmentId;

}