package com.example.auth.domain.community;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CommunityRepository extends JpaRepository<Community, Long> {
    List<Community> findAllByOrderByCreatedAtDesc();
    List<Community> findAllByOrderByRecommendCountDesc();
}
