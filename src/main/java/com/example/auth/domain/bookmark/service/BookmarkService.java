package com.example.auth.domain.bookmark.service;

import com.example.auth.domain.bookmark.Bookmark;
import com.example.auth.domain.bookmark.BookmarkRepository;
import com.example.auth.domain.bookmark.BookmarkableType;
import com.example.auth.domain.bookmark.dto.BookmarkCreateRequest;
import com.example.auth.domain.bookmark.dto.BookmarkResponse;
import com.example.auth.domain.comment.Comment;
import com.example.auth.domain.comment.CommentableType;
import com.example.auth.domain.comment.dto.CommentResponse;
import com.example.auth.domain.community.CommunityRepository;
import com.example.auth.domain.user.User;
import com.example.auth.domain.user.UserRepository;
import com.example.auth.exception.CustomException;
import com.example.auth.exception.ErrorCode;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class BookmarkService {
    private final BookmarkRepository bookmarkRepository;
    private final UserRepository userRepository;
    private final CommunityRepository communityRepository;

    /**
     * 북마크 추가
     */
    public BookmarkResponse create(Long userId, BookmarkCreateRequest req) {
        // 1. 작성자 검증
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new CustomException(ErrorCode.USER_NOT_FOUND));

        // 2. commentable 존재 검증
        if (req.getBookmarkableType() == BookmarkableType.CommunityPost) {
            if (!communityRepository.existsById(req.getBookmarkableId())) {
                throw new CustomException(ErrorCode.NOT_FOUND);
            }
        } //todo: qna

        // 3. 저장
        Bookmark bookmark = Bookmark.builder()
                .user(user)
                .bookmarkableId(req.getBookmarkableId())
                .bookmarkableType(req.getBookmarkableType())
                .build();


        Bookmark saved = bookmarkRepository.save(bookmark);

        // 4. 결과 반환
        return new BookmarkResponse(
                saved.getId(),
                saved.getUser().getNickname(),
                saved.getCreatedAt()
        );
    }

    public List<BookmarkResponse> list(BookmarkableType type, Long bookmarkableId) {
        var bookmarks = bookmarkRepository
                .findAllByBookmarkableTypeAndBookmarkableIdOrderByCreatedAtAsc(type, bookmarkableId);

        return bookmarks.stream()
                .map(c -> new BookmarkResponse(
                        c.getId(),
                        c.getUser().getNickname(),
                        c.getCreatedAt()
                ))
                .toList();
    }
    }
