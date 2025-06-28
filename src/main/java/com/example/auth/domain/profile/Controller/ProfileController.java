package com.example.auth.domain.profile.Controller;

import com.example.auth.domain.profile.dto.ProfileUpdateRequest;
import com.example.auth.domain.profile.dto.ProfileUpdateResponse;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/profile")
@Tag(name = "프로필 수정", description = "자기소개/링크 수정")
@RequiredArgsConstructor
public class ProfileController {

    private final com.example.auth.domain.profile.Service.ProfileService profileService;

    @PutMapping("/update")
    @Operation(summary = "내 프로필 수정", description = "자기소개, 링크 정보를 수정합니다.")
    @ApiResponse(responseCode = "200", description = "프로필 수정 성공", content = @Content(schema = @Schema (implementation = ProfileUpdateResponse.class)))
    public ProfileUpdateResponse updateMyProfile(@RequestBody ProfileUpdateRequest request) {
        return profileService.updateMyProfile(request);
    }
}
