package com.example.auth.domain.profile.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;
import lombok.Setter;

@Getter
public class ProfileUpdateRequest {
    @Schema(description = "자기소개", example = "안녕하세요! KUSLS입니다.")
    private String bio;
    @Schema(description = "링크", example = "https://github.com/my-profile")
    private String url;
}
