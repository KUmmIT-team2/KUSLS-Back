package com.example.auth.domain.user;

import com.example.auth.domain.category.Department;
import jakarta.persistence.*;
import lombok.*;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class User {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, unique = true)
    private String username;

    @Column(nullable = false)
    private String password;

    private String studentNumber;

    @Column(nullable = false)
    private Boolean isMentor = false;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "department_id")
    private Department department;

    @Column(nullable = false)
    private Long commentCount = 0L;

    @Builder
    public User(String username, String password, String studentNumber, Boolean isMentor, Department departmentId, Long commentCount) {
        this.username = username;
        this.password = password;
        this.studentNumber = studentNumber;
        this.isMentor = isMentor != null ? isMentor : false;
        this.department = departmentId;
        this.commentCount = commentCount != null ? commentCount : 0L;
    }
}
