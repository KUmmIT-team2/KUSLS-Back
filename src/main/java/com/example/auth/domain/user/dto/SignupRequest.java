package com.example.auth.domain.user.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;
import lombok.Setter;

@Getter @Setter
public class SignupRequest {
    @Schema(description = "아이디", example = "<USERNAME>")
    private String username;

    @Schema(description = "비밀번호", example = "<PASSWORD>")
    private String password;

    @Schema(description = "학번", example = "2021111111")
    private String studentNumber;

    @Schema(description = "멘토 여부", example = "true")
    private Boolean isMentor = false;

    @Schema(description = "본인 학과", example = "15")
    private Long departmentId = null;
}
