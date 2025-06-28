package com.example.auth.domain.user.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;
import lombok.Setter;

@Getter @Setter
public class SignupRequest {
    @Schema(description = "이름", example = "홍길동")
    private String username;

    @Schema(description = "비밀번호", example = "1234")
    private String password;

    @Schema(description = "학번", example = "202123456")
    private String studentNumber;

    @Schema(description = "멘토 여부", example = "true")
    private Boolean isMentor = false;

    @Schema(description = "본인 학과", example = "1")
    private Long departmentId = null;
}
