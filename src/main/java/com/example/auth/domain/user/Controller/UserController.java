package com.example.auth.domain.user.Controller;

import com.example.auth.domain.user.Service.UserService;
import com.example.auth.domain.user.dto.*;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/users")
@Tag(name = "나의 정보", description = "나의 정보 조회/수정/삭제")
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;

    @GetMapping("/me")
    @Operation(summary = "내 프로필 조회", description = "로그인한 사용자의 정보를 조회합니다.")
    @ApiResponse(responseCode = "200", description = "내 프로필 조회 성공",
            content = @Content(schema = @Schema(implementation = ProfileResponse.class)))
    public ProfileResponse getProfile() {
        return userService.getProfile();
    }

    @DeleteMapping("/me")
    @Operation(summary = "내 프로필 삭제", description = "로그인한 사용자의 정보를 삭제합니다.")
    @ApiResponse(responseCode = "200", description = "내 프로필 삭제 성공",
            content = @Content(schema = @Schema(implementation = ProfileResponse.class)))
    public void deleteProfile() {
        userService.deleteProfile();
    }
}
