package com.example.auth.domain.qna.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.time.LocalDateTime;

@Getter
@AllArgsConstructor
public class QnaSummaryResponse {
    private Long id;
    private String title;
    private String departmentName;
    private LocalDateTime createdAt;
    private int recommendCount;
    private int replyCount;
}
