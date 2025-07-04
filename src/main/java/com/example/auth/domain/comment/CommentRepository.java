package com.example.auth.domain.comment;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CommentRepository extends JpaRepository<Comment, Long> {
    List<Comment> findAllByCommentableTypeAndCommentableIdOrderByCreatedAtAsc(CommentableType type, Long commentableId);
}
