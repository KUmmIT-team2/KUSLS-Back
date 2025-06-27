package com.example.auth.domain.community.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
@AllArgsConstructor
public class CommunityDetailResponse {
    private Long id;
    private String title;
    private String content;
    private String writerNickname;
    private String collegeName;
    private String departmentName;
    private LocalDateTime createdAt;
}
