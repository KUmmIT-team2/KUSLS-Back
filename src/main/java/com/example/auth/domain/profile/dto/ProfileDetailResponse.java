package com.example.auth.domain.profile.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class ProfileDetailResponse {
    private Long id;
    private String username;
    private String college;
    private String department;
    private String studentNumber;
    private String hashtag;
    private Long commentCount;
}
