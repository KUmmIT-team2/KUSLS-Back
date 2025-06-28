package com.example.auth.domain.qna.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
@AllArgsConstructor
public class QnaDetailResponse {
    private Long id;
    private String title;
    private String writerUsername;
    private String collegeName;
    private String departmentName;
    private LocalDateTime createdAt;
    private int bookmarkCount;
    private int recommendCount;
    private int replyCount;
}
