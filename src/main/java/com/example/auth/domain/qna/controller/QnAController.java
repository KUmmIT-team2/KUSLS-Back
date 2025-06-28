package com.example.auth.domain.qna.controller;

import com.example.auth.domain.bookmark.BookmarkableType;
import com.example.auth.domain.bookmark.dto.BookmarkCreateRequest;
import com.example.auth.domain.bookmark.dto.BookmarkResponse;
import com.example.auth.domain.bookmark.service.BookmarkService;
import com.example.auth.domain.comment.CommentableType;
import com.example.auth.domain.comment.dto.CommentCreateRequest;
import com.example.auth.domain.comment.dto.CommentResponse;
import com.example.auth.domain.comment.service.CommentService;
import com.example.auth.domain.qna.dto.QnaCreateRequest;
import com.example.auth.domain.qna.dto.QnaDetailResponse;
import com.example.auth.domain.qna.dto.QnaResponse;
import com.example.auth.domain.qna.service.QnAService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@Tag(name = "QnA", description = "QnA 게시판 기능")
@RequiredArgsConstructor
@RequestMapping("/qna")
public class QnAController {
    private final QnAService qnAService;
    private final BookmarkService bookmarkService;
    private final CommentService commentService;

    @PostMapping
    @Operation(summary = "QnA 게시글 작성", description = "새로운 QnA 글 작성")
    public ResponseEntity<QnaResponse> createQnAPost(@RequestParam Long userId,
                                                           @RequestBody QnaCreateRequest request) {
        QnaResponse response = qnAService.createQnA(userId, request);
        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }

    @GetMapping
    @Operation(summary = "모든 qna 게시글 조회", description = "전체 qna 게시글을 조회")
    public ResponseEntity<List<QnaResponse>> getAllQnas() {
        List<QnaResponse> allQnAs = qnAService.getAllQnAs();
        return ResponseEntity.ok(allQnAs);
    }

    @GetMapping("/{id}")
    @Operation(summary = "qna 게시글 상세 조회", description = "ID로 특정 qna 글 찾기")
    public ResponseEntity<QnaDetailResponse> getQnaById(@PathVariable Long id) {
        QnaDetailResponse qna = qnAService.getQnaDetailById(id);
        return ResponseEntity.ok(qna);

    }

    @PostMapping("/{qnaId}/comments")
    @Operation(summary = "qna 게시글 댓글 작성", description = "qnaId로 특정 qna 게시글 댓글 작성")
    public ResponseEntity<CommentResponse> createQnaComment(
            @PathVariable Long qnaId,
            @RequestBody CommentCreateRequest request) {
        request.setCommentableType(CommentableType.QnaPost);
        request.setCommentableId(qnaId);

        Long userId = getCurrentUserId();
        CommentResponse resp = commentService.create(userId, request);
        return ResponseEntity.status(HttpStatus.CREATED).body(resp);
    }

    @PostMapping("/{qnaId}/bookmark")
    @Operation(summary = "qna 게시글 북마크 추가", description = "qnaId로 특정 qna 북마크")
    public ResponseEntity<BookmarkResponse> bookmarkQna(
            @PathVariable Long qnaId,
            @RequestBody BookmarkCreateRequest request) {
        request.setBookmarkableType(BookmarkableType.QnaPost);
        request.setBookmarkableId(qnaId);

        Long userId = getCurrentUserId();
        BookmarkResponse response = bookmarkService.create(userId, request);
        return ResponseEntity.status(HttpStatus.CREATED).body(response);

    }

    private Long getCurrentUserId() {
        // TODO: Spring Security 에서 JWT payload 등에서 추출
        return 1L;
    }
}
