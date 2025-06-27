package com.example.auth.domain.badge;

import com.example.auth.domain.user.User;
import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "user_badge")
@Getter @Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class UserBadge {

    @EmbeddedId
    private UserBadgeId id;

    @ManyToOne(fetch = FetchType.LAZY)
    @MapsId("userId")
    @JoinColumn(name = "user_id")
    private User user;

    @ManyToOne(fetch = FetchType.LAZY)
    @MapsId("badgeId")
    @JoinColumn(name = "badge_id")
    private Badge badge;
}
