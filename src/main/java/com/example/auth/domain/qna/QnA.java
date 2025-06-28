package com.example.auth.domain.qna;

import com.example.auth.domain.category.College;
import com.example.auth.domain.category.Department;
import com.example.auth.domain.user.User;
import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;

import java.time.LocalDateTime;

@Builder
@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor(access = AccessLevel.PRIVATE)
public class QnA {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(optional = false)
    @JoinColumn(name = "user_id")
    private User user;

    @Column(length = 100, nullable = false)
    private String title;

    @ManyToOne
    @JoinColumn(name = "college_id")
    private College college;

    @ManyToOne
    @JoinColumn(name = "department_id")
    private Department department;

    @CreationTimestamp
    @Column(name = "created_at", updatable = false)
    private LocalDateTime createdAt;

    @Builder.Default
    private int bookmarkCount = 0;

    @Builder.Default
    private int recommendCount = 0;

    @Builder.Default
    private int replyCount = 0;

    // 추천 수 1 올리는 도메인 메서드
    public void addRecommend() {
        this.recommendCount++;
    }

    public void incrementReplyCount() {
        this.replyCount++;
    }

}
