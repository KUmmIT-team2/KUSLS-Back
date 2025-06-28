package com.example.auth.domain.community.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CommunityCreateRequest {
    @NotBlank
    @Schema(description = "커뮤니티 게시글 제목", example = "개발 스터디 모집합니다")
    private String title;

    @NotBlank
    @Schema(description = "커뮤니티 게시글 내용", example = "함께 스프링 부트 공부하실 분 구합니다.")
    private String content;

    @Schema(description = "대학 ID", example = "1")
    private Long collegeId;

    @Schema(description = "학과 ID", example = "2")
    private Long departmentId;
}
