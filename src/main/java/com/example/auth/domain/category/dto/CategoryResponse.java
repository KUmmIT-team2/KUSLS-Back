package com.example.auth.domain.category.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@AllArgsConstructor
@NoArgsConstructor
@Schema(description = "단과대학/학과 응답 DTO")
public class CategoryResponse {
    @Schema(description = "단과대학/학과 이름", example = "컴퓨터공학부")
    private String name;
}
