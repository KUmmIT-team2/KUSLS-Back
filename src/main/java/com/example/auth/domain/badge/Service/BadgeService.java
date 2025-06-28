package com.example.auth.domain.badge.Service;

import com.example.auth.domain.badge.*;
import com.example.auth.domain.badge.dto.BadgeResponse;
import com.example.auth.domain.user.User;
import com.example.auth.domain.user.UserRepository;
import com.example.auth.exception.CustomException;
import com.example.auth.exception.ErrorCode;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class BadgeService {

    private final UserBadgeRepository userBadgeRepository;
    private final BadgeRepository badgeRepository;
    private final UserRepository userRepository;

    public List<BadgeResponse> getAllBadges() {
        List<Badge> badges = badgeRepository.findAll();

        return badges.stream()
                .map(badge -> BadgeResponse.builder()
                        .id(badge.getId())
                        .name(badge.getName())
                        .description(badge.getDescription())
                        .iconUrl(badge.getIconUrl())
                        .build())
                .collect(Collectors.toList());
    }

    public void grantBadge(Long userId, Long badgeId) {
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new CustomException(ErrorCode.USER_NOT_FOUND));

        Badge badge = badgeRepository.findById(badgeId)
                .orElseThrow(() -> new CustomException(ErrorCode.BADGE_NOT_FOUND));

        UserBadgeId userBadgeId = new UserBadgeId(userId, badgeId);

        if (userBadgeRepository.existsById(userBadgeId)) {
            throw new IllegalStateException("이미 해당 뱃지를 보유하고 있습니다.");
        }

        UserBadge userBadge = UserBadge.builder()
                .id(userBadgeId)
                .user(user)
                .badge(badge)
                .build();

        userBadgeRepository.save(userBadge);
    }

    public List<BadgeResponse> getUserBadges(Long userId) {
        List<UserBadge> userBadges = userBadgeRepository.findAllByUserId(userId);

        return userBadges.stream()
                .map(ub -> {
                    Badge badge = ub.getBadge();
                    return BadgeResponse.builder()
                            .id(badge.getId())
                            .name(badge.getName())
                            .description(badge.getDescription())
                            .iconUrl(badge.getIconUrl())
                            .build();
                })
                .collect(Collectors.toList());
    }
}
