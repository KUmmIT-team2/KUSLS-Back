package com.example.auth.domain.profile.Service;

import com.example.auth.domain.category.CollegeRepository;
import com.example.auth.domain.category.Department;
import com.example.auth.domain.category.DepartmentRepository;
import com.example.auth.domain.profile.Profile;
import com.example.auth.domain.profile.ProfileRepository;
import com.example.auth.domain.profile.dto.ProfileDetailResponse;
import com.example.auth.domain.profile.dto.ProfileUpdateRequest;
import com.example.auth.domain.profile.dto.ProfileUpdateResponse;
import com.example.auth.domain.user.User;
import com.example.auth.domain.user.UserRepository;
import com.example.auth.exception.CustomException;
import com.example.auth.exception.ErrorCode;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ProfileService {

    private final ProfileRepository profileRepository;
    private final UserRepository userRepository;

    public ProfileUpdateResponse updateMyProfile(ProfileUpdateRequest request) {
        Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();

        if (principal instanceof String && principal.equals("anonymousUser")) {
            throw new CustomException(ErrorCode.UNAUTHORIZED);
        }

        Long userId = Long.parseLong(principal.toString());

        User user = userRepository.findById(userId)
                .orElseThrow(() -> new CustomException(ErrorCode.USER_NOT_FOUND));

        Profile profile = profileRepository.findById(userId)
                .orElseThrow(() -> new CustomException(ErrorCode.USER_NOT_FOUND));

        profile.update(request.getHashtag());

        Profile updatedProfile = profileRepository.save(profile);

        return new ProfileUpdateResponse(
                updatedProfile.getHashtag()
        );
    }

    public ProfileDetailResponse getProfileById(Long id) {
        User user = userRepository.findById(id)
                .orElseThrow(() -> new CustomException(ErrorCode.USER_NOT_FOUND));
        Profile profile = profileRepository.findById(id)
                .orElseThrow(() -> new CustomException(ErrorCode.USER_NOT_FOUND));

        return new ProfileDetailResponse(
                id,
                user.getUsername(),
                user.getDepartment().getCollege().getName(),
                user.getDepartment().getName(),
                user.getStudentNumber(),
                profile.getHashtag(),
                user.getCommentCount()
        );
    }
}
