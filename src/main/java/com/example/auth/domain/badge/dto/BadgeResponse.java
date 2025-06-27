package com.example.auth.domain.badge.dto;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class BadgeResponse {
    private Long id;
    private String name;
    private String description;
    private String iconUrl;
}
