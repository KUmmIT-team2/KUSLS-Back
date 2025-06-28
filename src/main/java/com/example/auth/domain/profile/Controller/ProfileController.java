package com.example.auth.domain.profile.Controller;

import com.example.auth.domain.category.CollegeRepository;
import com.example.auth.domain.category.DepartmentRepository;
import com.example.auth.domain.profile.ProfileRepository;
import com.example.auth.domain.profile.Service.ProfileService;
import com.example.auth.domain.profile.dto.ProfileDetailResponse;
import com.example.auth.domain.profile.dto.ProfileUpdateRequest;
import com.example.auth.domain.profile.dto.ProfileUpdateResponse;
import com.example.auth.domain.user.UserRepository;
import com.example.auth.domain.user.dto.ProfileResponse;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/profile")
@Tag(name = "멘토 프로필", description = "해쉬태그")
@RequiredArgsConstructor
public class ProfileController {
    private final com.example.auth.domain.profile.Service.ProfileService profileService;

    @GetMapping("/{id}")
    @Operation(summary = "프로필 상세 조회", description = "ID로 특정 멘토의 프로필 상세 내용을 조회합니다.")
    public ProfileDetailResponse getProfile(@PathVariable Long id) {
        ProfileDetailResponse profile = profileService.getProfileById(id);
        return profile;
    }

    @PutMapping("/update")
    @Operation(summary = "내 프로필 수정", description = "자기소개, 링크 정보를 수정합니다.")
    @ApiResponse(responseCode = "200", description = "프로필 수정 성공", content = @Content(schema = @Schema (implementation = ProfileUpdateResponse.class)))
    public ProfileUpdateResponse updateMyProfile(@RequestBody ProfileUpdateRequest request) {
        return profileService.updateMyProfile(request);
    }
}
