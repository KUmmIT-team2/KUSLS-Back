package com.example.auth.domain.profile.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class ProfileUpdateResponse {

    @Schema(description = "hashtag", example = "#미컴 #동기부여 #진로고민 #학업고민")
    private String hastag;
}
