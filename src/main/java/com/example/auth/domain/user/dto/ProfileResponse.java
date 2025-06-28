package com.example.auth.domain.user.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class ProfileResponse {
    @Schema(description = "아이디", example = "<USERNAME>")
    private String username;

    @Schema(description = "학번", example = "2021111111")
    private String studentNumber;

    @Schema(description = "멘토 여부", example = "true")
    private Boolean isMentor;

    @Schema(description = "학과 ID", example = "2")
    private Long departmentId;

    @Schema(description = "답변 횟수", example = "10")
    private Long commentCount;

}
