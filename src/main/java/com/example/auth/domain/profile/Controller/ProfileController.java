package com.example.auth.domain.profile.Controller;

import com.example.auth.domain.profile.dto.ProfileUpdateRequest;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/profile")
@Tag(name = "프로필 수정", description = "자기소개, 링크 수정")
@RequiredArgsConstructor
public class ProfileController {

    private final com.example.auth.domain.profile.Service.ProfileService profileService;

    @PutMapping("/update")
    @Operation(summary = "내 프로필 수정", description = "자기소개, 링크 정보를 수정합니다.")
    public ResponseEntity<Void> updateMyProfile(@RequestBody ProfileUpdateRequest request) {
        profileService.updateMyProfile(request);
        return ResponseEntity.ok().build();
    }
}
