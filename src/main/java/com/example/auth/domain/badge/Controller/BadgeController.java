package com.example.auth.domain.badge.Controller;

import com.example.auth.domain.badge.service.BadgeService;
import com.example.auth.domain.badge.dto.BadgeResponse;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/badges")
@Tag(name = "뱃지", description = "뱃지 지급 및 조회 API")
@RequiredArgsConstructor
public class BadgeController {

    private final BadgeService badgeService;

    @GetMapping("")
    @Operation(summary = "전체 뱃지 조회", description = "시스템에 등록된 모든 뱃지를 조회합니다.")
    public List<BadgeResponse> getAllBadges() {
        return badgeService.getAllBadges();
    }

    @PostMapping("/grant")
    @Operation(summary = "뱃지 지급", description = "특정 사용자에게 뱃지를 지급합니다.")
    public String grantBadge(
            @RequestParam Long userId,
            @RequestParam Long badgeId
    ) {
        badgeService.grantBadge(userId, badgeId);
        return "뱃지 지급 완료";
    }

    @GetMapping("/user/{userId}")
    @Operation(summary = "사용자 보유 뱃지 조회", description = "특정 사용자가 보유한 모든 뱃지를 조회합니다.")
    @ApiResponse(responseCode = "200", description = "모든 뱃지 조회 성공",
    content = @Content(schema = @Schema(implementation = BadgeResponse.class)))
    public List<BadgeResponse> getUserBadges(@PathVariable Long userId) {
        return badgeService.getUserBadges(userId);
    }
}
