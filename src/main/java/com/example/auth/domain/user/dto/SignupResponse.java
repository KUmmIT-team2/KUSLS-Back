package com.example.auth.domain.user.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class SignupResponse {

    @Schema(description = "이름", example = "홍길동")
    private String username;

    @Schema(description = "학번", example = "202123456")
    private String studentNumber;

    @Schema(description = "멘토 여부", example = "true")
    private Boolean isMentor;

    @Schema(description = "본인학과", example = "1")
    private Long departmentId;
}
