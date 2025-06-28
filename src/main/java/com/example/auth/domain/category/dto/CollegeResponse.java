package com.example.auth.domain.category.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class CollegeResponse {
    @Schema(description = "대학ID", example = "4")
    private Long id;

    @Schema(description = "대학이름", example = "공과대학")
    private String name;
}
