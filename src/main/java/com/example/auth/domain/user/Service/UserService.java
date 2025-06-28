package com.example.auth.domain.user.Service;

import com.example.auth.domain.profile.Profile;
import com.example.auth.domain.profile.ProfileRepository;
import com.example.auth.domain.user.User;
import com.example.auth.domain.user.UserRepository;
import com.example.auth.domain.user.dto.ProfileResponse;
import com.example.auth.exception.CustomException;
import com.example.auth.exception.ErrorCode;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;
    private final ProfileRepository profileRepository;

    public ProfileResponse getProfile() {
        Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();

        if (principal instanceof String && principal.equals("anonymousUser")) {
            throw new CustomException(ErrorCode.UNAUTHORIZED);
        }

        Long userId = Long.parseLong(principal.toString());

        User user = userRepository.findById(userId)
                .orElseThrow(() -> new CustomException(ErrorCode.USER_NOT_FOUND));

        Profile profile = profileRepository.findById(userId)
                .orElseThrow(() -> new CustomException(ErrorCode.USER_NOT_FOUND));

        return new ProfileResponse(
                user.getUsername(),
                user.getEmail(),
                user.getNickname(),
                user.getIsMentor(),
                user.getDepartment() != null ? user.getDepartment().getId() : null,
                profile.getBio(),
                profile.getUrl()
        );
    }

    public void deleteProfile() {
        Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();

        if (principal instanceof String && principal.equals("anonymousUser")) {
            throw new CustomException(ErrorCode.UNAUTHORIZED);
        }

        Long userId = Long.parseLong(principal.toString());

        User user = userRepository.findById(userId)
                .orElseThrow(() -> new CustomException(ErrorCode.USER_NOT_FOUND));

        userRepository.delete(user);
    }

}
