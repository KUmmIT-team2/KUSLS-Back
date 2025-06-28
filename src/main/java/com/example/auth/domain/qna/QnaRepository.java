package com.example.auth.domain.qna;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface QnaRepository extends JpaRepository<QnA, Long> {
    List<QnA> findAllByOrderByCreatedAtDesc();
    List<QnA> findAllByOrderByRecommendCountDesc();
}