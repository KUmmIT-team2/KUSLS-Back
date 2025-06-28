package com.example.auth.domain.community.service;

import com.example.auth.domain.category.College;
import com.example.auth.domain.category.CollegeRepository;
import com.example.auth.domain.category.Department;
import com.example.auth.domain.category.DepartmentRepository;
import com.example.auth.domain.community.Community;
import com.example.auth.domain.community.CommunityRepository;
import com.example.auth.domain.community.dto.CommunityCreateRequest;
import com.example.auth.domain.community.dto.CommunityDetailResponse;
import com.example.auth.domain.community.dto.CommunityResponse;
import com.example.auth.domain.community.dto.CommunitySummaryResponse;
import com.example.auth.domain.user.User;
import com.example.auth.domain.user.UserRepository;
import com.example.auth.exception.CustomException;
import com.example.auth.exception.ErrorCode;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class CommunityService {
    private final CommunityRepository communityRepository;
    private final UserRepository userRepository;
    private final CollegeRepository collegeRepository;
    private final DepartmentRepository departmentRepository;

    public CommunityResponse createPost(Long userId, CommunityCreateRequest request) {
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new CustomException(ErrorCode.USER_NOT_FOUND));
        College college = null;
        if (request.getCollegeId() != null) {
            college = collegeRepository.findById(request.getCollegeId())
                    .orElseThrow(() -> new CustomException(ErrorCode.COLLEGE_NOT_FOUND));
        }

        Department department = null;
        if (request.getDepartmentId() != null) {
            department = departmentRepository.findById(request.getDepartmentId())
                    .orElseThrow(() -> new CustomException(ErrorCode.DEPARTMENT_NOT_FOUND));
        }

        Community community = Community.builder()
                .user(user)
                .title(request.getTitle())
                .content(request.getContent())
                .college(college)
                .department(department)
                .build();

        Community saved = communityRepository.save(community);
        return new CommunityResponse(
                saved.getId(),
                saved.getTitle(),
                saved.getContent(),
                saved.getUser().getUsername(),
                saved.getCreatedAt(),
                saved.getBookmarkCount(),
                saved.getRecommendCount(),
                saved.getReplyCount()
        );
    }

    public List<CommunitySummaryResponse> getAllPosts(String orderBy) {
        List<Community> list = switch (orderBy) {
            case "likes"  -> communityRepository.findAllByOrderByRecommendCountDesc();
            default       -> communityRepository.findAllByOrderByCreatedAtDesc();
        };
        return list.stream()
                .map(c -> new CommunitySummaryResponse(
                        c.getId(),
                        c.getTitle(),
                        c.getDepartment() != null ? c.getDepartment().getName() : null,
                        c.getCreatedAt(),
                        c.getRecommendCount(),
                        c.getReplyCount()
                ))
                .toList();
    }

    public CommunityDetailResponse getPostById(Long id) {
        Community community = communityRepository.findById(id)
                .orElseThrow(() -> new CustomException(ErrorCode.COMMUNITY_NOT_FOUND));

        return new CommunityDetailResponse(
                community.getId(),
                community.getTitle(),
                community.getContent(),
                community.getUser().getUsername(),
                community.getCollege() != null ? community.getCollege().getName() : null,
                community.getDepartment() != null ? community.getDepartment().getName() : null,
                community.getCreatedAt(),
                community.getBookmarkCount(),
                community.getRecommendCount(),
                community.getReplyCount()
        );
    }

    //todo: 게시글 북마크
}
