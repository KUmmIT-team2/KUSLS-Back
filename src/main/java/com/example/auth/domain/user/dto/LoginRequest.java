package com.example.auth.domain.user.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;
import lombok.Setter;

@Getter @Setter
public class LoginRequest {
    @Schema(description = "이름", example = "홍길동")
    private String username;

    @Schema(description = "비밀번호", example = "1234")
    private String password;
}
