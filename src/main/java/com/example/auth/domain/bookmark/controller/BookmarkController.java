package com.example.auth.domain.bookmark.controller;

import com.example.auth.domain.bookmark.dto.BookmarkResponse;
import com.example.auth.domain.bookmark.service.BookmarkService;
import com.example.auth.domain.user.User;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@Tag(name = "북마크", description = "북마크 기능")
@RequiredArgsConstructor
@RequestMapping("/bookmarks")
public class BookmarkController {
    private final BookmarkService bookmarkService;

    @GetMapping
    @Operation(summary = "사용자의 북마크 목록 조회", description = "로그인한 사용자가 북마크한 QnA 목록을 조회하여 반환한다")
    public ResponseEntity<List<BookmarkResponse>> getBookmarks(
            Authentication auth)
     {
         User user = (User) auth.getPrincipal();
         Long id = user.getId();

         List<BookmarkResponse> bookmarksByUser = bookmarkService.findBookmarksByUser(id);
         return ResponseEntity.ok(bookmarksByUser);
     }

     @DeleteMapping("/{id}")
     @Operation(summary = "북마크 목록 삭제", description = "로그인한 사용자가 북마크한 qna를 삭제한다")
    public ResponseEntity<String> deleteBookmarks(@PathVariable Long id) {
        return ResponseEntity.ok("Bookmark with ID" + id + " has been deleted");

     }

}
