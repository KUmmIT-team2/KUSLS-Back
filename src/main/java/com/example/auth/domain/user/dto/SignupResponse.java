package com.example.auth.domain.user.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class SignupResponse {

    @Schema(description = "아이디", example = "<USERNAME>")
    private String username;

    @Schema(description = "이메일", example = "<EMAIL>")
    private String email;

    @Schema(description = "닉네임", example = "<NICKNAME>")
    private String nickname;
}
