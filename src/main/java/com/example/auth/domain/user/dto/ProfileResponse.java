package com.example.auth.domain.user.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class ProfileResponse {
    @Schema(description = "아이디", example = "<USERNAME>")
    private String username;

    @Schema(description = "이메일", example = "<EMAIL>")
    private String email;

    @Schema(description = "닉네임", example = "<NICKNAME>")
    private String nickname;

    @Schema(description = "멘토 여부", example = "true")
    private Boolean isMentor;

    @Schema(description = "학과 ID", example = "2")
    private Long departmentId;

    @Schema(description = "자기소개", example = "안녕하세요! KUSLS입니다.")
    private String bio;

    @Schema(description = "링크", example = "https://github.com/my-profile")
    private String url;
}
