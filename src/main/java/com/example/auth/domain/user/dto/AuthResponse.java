package com.example.auth.domain.user.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class AuthResponse {
    @Schema(description = "토큰", example = "eyJhbGciOiJIUzI1NiIsIn...")
    private String token;
}
