package com.example.auth.domain.profile;

import com.example.auth.domain.user.User;
import jakarta.persistence.*;
import lombok.*;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Profile {

    @Id
    private Long userId;

    @OneToOne(fetch = FetchType.LAZY)
    @MapsId
    @JoinColumn(name = "user_id")
    private User user;

    @Column(columnDefinition = "TEXT")
    private String bio;

    @Column(length = 255)
    private String url;

    @Builder
    public Profile(User user, String bio, String url) {
        this.user = user;
        this.bio = bio;
        this.url = url;
    }

    public void update(String bio, String url) {
        this.bio = bio;
        this.url = url;
    }
}