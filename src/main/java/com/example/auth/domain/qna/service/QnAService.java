package com.example.auth.domain.qna.service;

import com.example.auth.domain.category.College;
import com.example.auth.domain.category.CollegeRepository;
import com.example.auth.domain.category.Department;
import com.example.auth.domain.category.DepartmentRepository;
import com.example.auth.domain.community.Community;
import com.example.auth.domain.community.dto.CommunityResponse;
import com.example.auth.domain.qna.QnA;
import com.example.auth.domain.qna.QnaRepository;
import com.example.auth.domain.qna.dto.QnaCreateRequest;
import com.example.auth.domain.qna.dto.QnaDetailResponse;
import com.example.auth.domain.qna.dto.QnaResponse;
import com.example.auth.domain.qna.dto.QnaSummaryResponse;
import com.example.auth.domain.user.User;
import com.example.auth.domain.user.UserRepository;
import com.example.auth.exception.CustomException;
import com.example.auth.exception.ErrorCode;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;

@Service
@RequiredArgsConstructor
public class QnAService {
    private final QnaRepository qnaRepository;
    private final UserRepository userRepository;
    private final CollegeRepository collegeRepository;
    private final DepartmentRepository departmentRepository;

    /**
     * QnA 생성
     */
    public QnaResponse createQnA(Long userId, QnaCreateRequest request) {
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new CustomException(ErrorCode.USER_NOT_FOUND));

        if (user.getIsMentor()) {
            throw new CustomException(ErrorCode.NOT_A_MENTEE);
        }

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

        QnA qna = QnA.builder()
                .user(user)
                .title(request.getTitle())
                .college(college)
                .department(department)
                .build();
        QnA saved = qnaRepository.save(qna);

        return new QnaResponse(
                saved.getId(),
                saved.getTitle(),
                saved.getUser().getUsername(),
                saved.getCreatedAt(),
                saved.getBookmarkCount(),
                saved.getRecommendCount(),
                saved.getReplyCount()
        );
    }

    /**
     * qnA 정렬
     */
    public List<QnaSummaryResponse> getAllPostsSorted(String orderBy) {
        List<QnA> list = switch (orderBy) {
            case "likes"  -> qnaRepository.findAllByOrderByRecommendCountDesc();
            default       -> qnaRepository.findAllByOrderByCreatedAtDesc();
        };
        return list.stream()
                .map(c -> new QnaSummaryResponse(
                        c.getId(),
                        c.getTitle(),
                        c.getDepartment().getName(),
                        c.getCreatedAt(),
                        c.getRecommendCount(),
                        c.getReplyCount()
                ))
                .toList();
    }

    /**
     * qnA 상세조회
     */
    public QnaDetailResponse getQnaDetailById(Long id) {
        QnA qnA = qnaRepository.findById(id)
                .orElseThrow(() -> new CustomException(ErrorCode.QNA_NOT_FOUND));
        return new QnaDetailResponse(
                qnA.getId(),
                qnA.getTitle(),
                qnA.getUser().getUsername(),
                qnA.getCollege() != null ? qnA.getCollege().getName() : null,
                qnA.getDepartment() != null ? qnA.getDepartment().getName() : null,
                qnA.getCreatedAt(),
                qnA.getBookmarkCount(),
                qnA.getRecommendCount(),
                qnA.getReplyCount()
        );
    }

    /**
     * qna 글 추천수 증가
     */
    public int recommend(Long id) {
        QnA qna = qnaRepository.findById(id)
                .orElseThrow(() -> new CustomException(ErrorCode.QNA_NOT_FOUND));
        qna.addRecommend();
        return qna.getRecommendCount();

    }
}

