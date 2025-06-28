package com.example.auth.domain.category.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class DepartmentResponse {
    @Schema(description = "학과 ID", example = "15")
    private Long id;

    @Schema(description = "학과이름", example = "컴퓨터공학부")
    private String name;
}
