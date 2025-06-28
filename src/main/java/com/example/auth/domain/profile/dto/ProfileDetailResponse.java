package com.example.auth.domain.profile.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@AllArgsConstructor
@NoArgsConstructor
@Schema(description = "사용자 프로필 상세 응답 DTO")
public class ProfileDetailResponse {
    @Schema(description = "사용자 고유 ID", example = "1")
    private Long id;

    @Schema(description = "사용자 계정명/닉네임", example = "건황소")
    private String username;

    @Schema(description = "소속 대학명", example = "문과대학")
    private String college;

    @Schema(description = "소속 학과명", example = "미디어커뮤니케이션학부")
    private String department;

    @Schema(description = "학번", example = "22학번")
    private String studentNumber;

    @Schema(description = "해시태그", example = "#미컴 #동기부여 #진로고민 #학업고민")
    private String hashtag;

    @Schema(description = "질문 답변 수", example = "108")
    private Long commentCount;
}
