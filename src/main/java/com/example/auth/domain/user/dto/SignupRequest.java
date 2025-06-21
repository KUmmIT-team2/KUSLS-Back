package com.example.auth.domain.user.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;
import lombok.Setter;

@Getter @Setter
public class SignupRequest {
    @Schema(description = "아이디", example = "<USERNAME>")
    private String username;

    @Schema(description = "이메일", example = "<EMAIL>")
    private String email;

    @Schema(description = "닉네임", example = "<NICKNAME>")
    private String nickname;

    @Schema(description = "비밀번호", example = "<PASSWORD>")
    private String password;
}
