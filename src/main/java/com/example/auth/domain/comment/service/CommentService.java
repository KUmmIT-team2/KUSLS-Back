package com.example.auth.domain.comment.service;

import com.example.auth.domain.comment.Comment;
import com.example.auth.domain.comment.CommentRepository;
import com.example.auth.domain.comment.CommentableType;
import com.example.auth.domain.comment.dto.CommentCreateRequest;
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
public class CommentService {
    private final CommentRepository commentRepository;
    private final UserRepository userRepository;
    private final CommunityRepository communityRepository;

    /**
     * 댓글 작성
     */
    public CommentResponse create(Long userId, CommentCreateRequest req) {
        // 1. 작성자 검증
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new CustomException(ErrorCode.USER_NOT_FOUND));

        // 2. commentable 존재 검증
        if (req.getCommentableType() == CommentableType.CommunityPost) {
            if (!communityRepository.existsById(req.getCommentableId())) {
                throw new CustomException(ErrorCode.COMMUNITY_NOT_FOUND);
            }
        } else if (req.getCommentableType() == CommentableType.QnaPost) {
            if (!commentRepository.existsById(req.getCommentableId())) {
                throw new CustomException(ErrorCode.QNA_NOT_FOUND);
            }

            // QnA 댓글은 멘토만 가능
            if (!user.getIsMentor()) {
                throw new CustomException(ErrorCode.NOT_A_MENTOR);
            }
        }

        // 3. 저장
        Comment comment = Comment.builder()
                .user(user)
                .content(req.getContent())
                .commentableId(req.getCommentableId())
                .commentableType(req.getCommentableType())
                .build();

        Comment saved = commentRepository.save(comment);

        // 4. 결과 반환
        return new CommentResponse(
                saved.getId(),
                saved.getContent(),
                saved.getUser().getUsername(),
                saved.getCreatedAt()
        );
    }

    /**
     * 댓글 표시
     */
    public List<CommentResponse> list(CommentableType type, Long commentableId) {
        var comments = commentRepository
                .findAllByCommentableTypeAndCommentableIdOrderByCreatedAtAsc(type, commentableId);

        return comments.stream()
                .map(c -> new CommentResponse(
                        c.getId(),
                        c.getContent(),
                        c.getUser().getUsername(),
                        c.getCreatedAt()
                ))
                .toList();
    }
}

