package com.example.auth.domain.community.controller;

import com.example.auth.domain.bookmark.BookmarkableType;
import com.example.auth.domain.bookmark.dto.BookmarkCreateRequest;
import com.example.auth.domain.bookmark.dto.BookmarkResponse;
import com.example.auth.domain.bookmark.service.BookmarkService;
import com.example.auth.domain.comment.CommentableType;
import com.example.auth.domain.comment.dto.CommentCreateRequest;
import com.example.auth.domain.comment.dto.CommentResponse;
import com.example.auth.domain.comment.service.CommentService;
import com.example.auth.domain.community.dto.CommunityCreateRequest;
import com.example.auth.domain.community.dto.CommunityDetailResponse;
import com.example.auth.domain.community.dto.CommunityResponse;
import com.example.auth.domain.community.dto.CommunitySummaryResponse;
import com.example.auth.domain.community.service.CommunityService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@Tag(name = "커뮤니티", description = "커뮤니티 게시판 기능")
@RequiredArgsConstructor
@RequestMapping("/community")
public class CommunityController {
    private final CommunityService communityService;
    private final CommentService commentService;
    private final BookmarkService bookmarkService;

    @PostMapping
    @Operation(summary = "커뮤니티 게시글 작성", description = "새로운 커뮤니티 글 작성")
    public ResponseEntity<CommunityResponse> createCommunityPost(@RequestParam Long userId,
                                                                 @RequestBody CommunityCreateRequest request) {
        CommunityResponse response = communityService.createPost(userId, request);
        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }

    @GetMapping
    @Operation(summary = "모든 커뮤니티 게시글 조회", description = "GET /community -> 최신순, GET /community?orderBy=likes -> 추천순")
    public ResponseEntity<List<CommunitySummaryResponse>> getAllPosts(@RequestParam(name = "orderBy", required = false, defaultValue = "createdAt") String orderBy)
    {
        List<CommunitySummaryResponse> posts = communityService.getAllPosts(orderBy);
        return ResponseEntity.ok(posts);
    }

    @GetMapping("/{id}")
    @Operation(summary = "커뮤니티 게시글 상세 조회", description = "ID로 특정 커뮤니티 게시글 상세 내용을 조회합니다.")
    public ResponseEntity<CommunityDetailResponse> getPostById(@PathVariable Long id) {
        CommunityDetailResponse post = communityService.getPostById(id);
        return ResponseEntity.ok(post);
    }

    @PostMapping("/{communityId}/comments")
    @Operation(summary = "커뮤니티 게시글 댓글 작성", description = "communityId로 특정 커뮤니티에 댓글을 작성")
    public ResponseEntity<CommentResponse> addCommunityComment(
            @PathVariable Long communityId,
            @RequestBody CommentCreateRequest req
    ) {
        // path 변수로 받은 communityId를 댓글 DTO에 세팅
        req.setCommentableType(CommentableType.CommunityPost);
        req.setCommentableId(communityId);

        Long userId = getCurrentUserId(); // JWT에서 뽑아오는 현재 유저
        CommentResponse resp = commentService.create(userId, req);
        return ResponseEntity.status(HttpStatus.CREATED).body(resp);
    }

    @PostMapping("/{communityId}/bookmark")
    @Operation(summary = "커뮤니티 게시글 북마크 추가", description = "communityId로 특정 커뮤니티 북마크")
    public ResponseEntity<BookmarkResponse> addCommunityBookmark(
            @PathVariable Long communityId,
            @RequestBody BookmarkCreateRequest req
    ) {
        req.setBookmarkableType(BookmarkableType.CommunityPost);
        req.setBookmarkableId(communityId);

        Long userId = getCurrentUserId();
        BookmarkResponse resp = bookmarkService.create(userId, req);
        return ResponseEntity.status(HttpStatus.CREATED).body(resp);
    }

    private Long getCurrentUserId() {
        // TODO: Spring Security 에서 JWT payload 등에서 추출
        return 1L;
    }
}
