package com.example.auth.domain.qna.dto;

import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class QnaCreateRequest {
    @NotBlank
    private String title;

    @NotBlank
    private String content;

    private Long collegeId;
    private Long departmentId;

}
