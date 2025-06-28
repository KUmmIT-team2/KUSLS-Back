package com.example.auth.domain.user.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class SignupResponse {

    @Schema(description = "아이디", example = "<USERNAME>")
    private String username;

    private String studentNumber;

    private Boolean isMentor;

    private Long departmentId;
}
