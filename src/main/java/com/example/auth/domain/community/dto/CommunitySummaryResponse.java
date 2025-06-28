package com.example.auth.domain.community.dto;

import com.example.auth.domain.category.Department;
import lombok.AllArgsConstructor;
import lombok.Getter;

import java.time.LocalDateTime;

@Getter
@AllArgsConstructor
public class CommunitySummaryResponse {
    private Long id;
    private String title;
    private String departmentName;
    private LocalDateTime createdAt;
    private int recommendCount;
    private int replyCount;
}
