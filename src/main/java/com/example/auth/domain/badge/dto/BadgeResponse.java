package com.example.auth.domain.badge.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class BadgeResponse {
    @Schema(description = "뱃지ID", example = "1")
    private Long id;

    @Schema(description = "뱃지이름", example = "질문왕")
    private String name;

    @Schema(description = "뱃지설명", example = "질문많이함")
    private String description;

    @Schema(description = "뱃지아이콘", example = "")
    private String iconUrl;
}
