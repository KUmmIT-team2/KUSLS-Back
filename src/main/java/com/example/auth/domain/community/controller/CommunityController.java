package com.example.auth.domain.community.controller;

import com.example.auth.domain.community.dto.CommunityCreateRequest;
import com.example.auth.domain.community.dto.CommunityResponse;
import com.example.auth.domain.community.service.CommunityService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@Tag(name = "커뮤니티", description = "커뮤니티 게시판 기능")
@RequiredArgsConstructor
@RequestMapping("/community")
public class CommunityController {
    private final CommunityService communityService;

    @PostMapping
    @Operation(summary = "커뮤니티 게시글 작성", description = "새로운 커뮤니티 글 작성")
    public ResponseEntity<CommunityResponse> createCommunityPost(@RequestParam Long userId,
                                                                 @RequestBody CommunityCreateRequest request) {
        CommunityResponse response = communityService.createPost(userId, request);
        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }
}
