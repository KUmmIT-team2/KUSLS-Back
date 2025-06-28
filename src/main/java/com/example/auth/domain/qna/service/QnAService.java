package com.example.auth.domain.qna.service;

import com.example.auth.domain.category.College;
import com.example.auth.domain.category.CollegeRepository;
import com.example.auth.domain.category.Department;
import com.example.auth.domain.category.DepartmentRepository;
import com.example.auth.domain.qna.QnA;
import com.example.auth.domain.qna.QnaRepository;
import com.example.auth.domain.qna.dto.QnaCreateRequest;
import com.example.auth.domain.qna.dto.QnaResponse;
import com.example.auth.domain.user.User;
import com.example.auth.domain.user.UserRepository;
import com.example.auth.exception.CustomException;
import com.example.auth.exception.ErrorCode;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class QnAService {
    private final QnaRepository qnaRepository;
    private final UserRepository userRepository;
    private final CollegeRepository collegeRepository;
    private final DepartmentRepository departmentRepository;

    public QnaResponse createQnA(Long userId, QnaCreateRequest request) {
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new CustomException(ErrorCode.USER_NOT_FOUND));

        College college = null;
        if (request.getCollegeId() != null) {
            college = collegeRepository.findById(request.getCollegeId())
                    .orElseThrow(() -> new CustomException(ErrorCode.NOT_FOUND));
        }

        Department department = null;
        if (request.getDepartmentId() != null) {
            department = departmentRepository.findById(request.getDepartmentId())
                    .orElseThrow(() -> new CustomException(ErrorCode.NOT_FOUND));
        }
        QnA qna = QnA.builder()
                .user(user)
                .title(request.getTitle())
                .content(request.getContent())
                .college(college)
                .department(department)
                .build();
        QnA saved = qnaRepository.save(qna);

        return new QnaResponse(
                saved.getId(),
                saved.getTitle(),
                saved.getContent(),
                saved.getUser().getNickname(),
                saved.getCreatedAt(),
                saved.getIsAnswered()
        );
    }
}
