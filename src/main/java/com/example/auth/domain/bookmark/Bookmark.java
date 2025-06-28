package com.example.auth.domain.bookmark;

import com.example.auth.domain.comment.CommentableType;
import com.example.auth.domain.user.User;
import jakarta.persistence.*;
import lombok.*;
import org.springframework.data.annotation.CreatedDate;

import java.time.LocalDateTime;

@Entity
@Table(name = "bookmark")
@Getter  @Setter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Builder
@AllArgsConstructor
public class Bookmark {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id", nullable = false)
    private User user;

    // 연결된 게시글 ID
    @Column(name = "bookmarkable_id", nullable = false)
    private Long bookmarkableId;

    // 연결된 게시글 종류 구분
    @Enumerated(EnumType.STRING)
    @Column(name = "bookmarkable_type", columnDefinition = "ENUM('CommunityPost','QnaPost')", nullable = false)
    private BookmarkableType bookmarkableType;

    @CreatedDate
    @Column(name = "created_at", updatable = false)
    private LocalDateTime createdAt;
}
