package com.example.auth.domain.qna;

import com.example.auth.domain.category.College;
import com.example.auth.domain.category.Department;
import com.example.auth.domain.user.User;
import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;

import java.time.LocalDateTime;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class QnA {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(optional = false)
    @JoinColumn(name = "user_id")
    private User user;

    @Column(length = 100, nullable = false)
    private String title;

    @Column(columnDefinition = "TEXT", nullable = false)
    private String content;

    @ManyToOne
    @JoinColumn(name = "college_id")
    private College college;

    @ManyToOne
    @JoinColumn(name = "department_id")
    private Department department;

    @CreationTimestamp
    @Column(name = "created_at", updatable = false)
    private LocalDateTime createdAt;

    @Column(nullable = false)
    private Boolean isAnswered = false;

    @Builder
    public QnA(User user, String title, String content, College college, Department department) {
        this.user = user;
        this.title = title;
        this.content = content;
        this.college = college;
        this.department = department;
        this.isAnswered = false;
    }

}