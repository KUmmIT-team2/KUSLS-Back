package com.example.auth.domain.bookmark.service;

import com.example.auth.domain.bookmark.Bookmark;
import com.example.auth.domain.bookmark.BookmarkRepository;
import com.example.auth.domain.bookmark.BookmarkableType;
import com.example.auth.domain.bookmark.dto.BookmarkCreateRequest;
import com.example.auth.domain.bookmark.dto.BookmarkResponse;
import com.example.auth.domain.community.CommunityRepository;
import com.example.auth.domain.qna.QnA;
import com.example.auth.domain.qna.QnaRepository;
import com.example.auth.domain.user.User;
import com.example.auth.domain.user.UserRepository;
import com.example.auth.exception.CustomException;
import com.example.auth.exception.ErrorCode;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
public class BookmarkService {
    private final BookmarkRepository bookmarkRepository;
    private final UserRepository userRepository;
    private final CommunityRepository communityRepository;
    private final QnaRepository qnaRepository;

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
                throw new CustomException(ErrorCode.COMMUNITY_NOT_FOUND);
            }
        } else if (req.getBookmarkableType() == BookmarkableType.QnaPost) {
            if (!communityRepository.existsById(req.getBookmarkableId())) {
                throw new CustomException(ErrorCode.QNA_NOT_FOUND);
            }
        }

        // 3. 저장
        Bookmark bookmark = Bookmark.builder()
                .user(user)
                .bookmarkableId(req.getBookmarkableId())
                .bookmarkableType(req.getBookmarkableType())
                .build();


        Bookmark saved = bookmarkRepository.save(bookmark);
        QnA qnA = qnaRepository.findById(req.getBookmarkableId())
                .orElseThrow(() -> new CustomException(ErrorCode.QNA_NOT_FOUND));
        qnA.incrementBookmarkCount();

        // 4. 결과 반환
        return new BookmarkResponse(
                saved.getId(),
                saved.getUser().getUsername(),
                saved.getCreatedAt()
        );
    }

    // 1) 로그인한 사용자가 북마크한 전체 QnA(또는 모든 타입)의 목록
    @Transactional(readOnly = true)
    public List<BookmarkResponse> findBookmarksByUser(Long userId) {
        var bookmarks = bookmarkRepository.findAllByUserIdOrderByCreatedAtAsc(userId);
        return bookmarks.stream()
                .map(c -> new BookmarkResponse(
                        c.getId(),
                        c.getUser().getUsername(),
                        c.getCreatedAt()
                ))
                .toList();
    }
    }
