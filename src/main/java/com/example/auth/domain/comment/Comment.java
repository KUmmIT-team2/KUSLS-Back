package com.example.auth.domain.comment;

import com.example.auth.domain.user.User;
import jakarta.persistence.*;
import lombok.*;
import org.springframework.data.annotation.CreatedDate;

import java.time.LocalDateTime;

@Entity
@Table(name = "comment")
@Getter @Setter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Builder
@AllArgsConstructor
public class Comment {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id", nullable = false)
    private User user;

    @Column(name = "content", columnDefinition = "TEXT", nullable = false)
    private String content;

    // 연결된 게시글 ID
    @Column(name = "commentable_id", nullable = false)
    private Long commentableId;

    // 연결된 게시글 종류 구분
    @Enumerated(EnumType.STRING)
    @Column(name = "commentable_type", columnDefinition = "ENUM('CommunityPost','QnaPost')", nullable = false)
    private CommentableType commentableType;

    @CreatedDate
    @Column(name = "created_at", updatable = false)
    private LocalDateTime createdAt;
}