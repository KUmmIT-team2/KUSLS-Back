package com.example.auth.domain.profile;

import com.example.auth.domain.user.User;
import jakarta.persistence.*;
import lombok.*;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Profile {

    @Id
    private Long id;

    @OneToOne(fetch = FetchType.LAZY)
    @MapsId
    @JoinColumn(name = "user_id")
    private User user;

    @Column(columnDefinition = "TEXT")
    private String hashtag;

    @Builder
    public Profile(User user, String hashtag) {
        this.user = user;
        this.hashtag = hashtag;
    }

    public void update(String hashtag) {
        this.hashtag = hashtag;
    }
}