package com.example.auth.domain.profile.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;
import lombok.Setter;

@Getter
public class ProfileUpdateRequest {
    @Schema(description = "해쉬태그", example = "#미컴 #동기부여 #진로고민 #학업고민")
    private String hashtag;
}
