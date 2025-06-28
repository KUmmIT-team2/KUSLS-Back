package com.example.auth.domain.category.service;

import com.example.auth.domain.category.CollegeRepository;
import com.example.auth.domain.category.DepartmentRepository;
import com.example.auth.domain.category.dto.CollegeResponse;
import com.example.auth.domain.category.dto.DepartmentResponse;
import com.example.auth.exception.CustomException;
import com.example.auth.exception.ErrorCode;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class CategoryService {

    private final CollegeRepository collegeRepository;
    private final DepartmentRepository departmentRepository;

    // 전체 대학 목록 조회
    public List<CollegeResponse> getAllColleges() {
        return collegeRepository.findAll().stream()
                .map(college -> new CollegeResponse(college.getId(), college.getName()))
                .collect(Collectors.toList());
    }

    // 특정 대학에 속한 학과 목록 조회
    public List<DepartmentResponse> getDepartmentsByCollege(Long collegeId) {
        return departmentRepository.findByCollegeId(collegeId).stream()
                .map(dept -> new DepartmentResponse(dept.getId(), dept.getName()))
                .collect(Collectors.toList());
    }

    // 전체 학과 목록 조회
    public List<DepartmentResponse> getAllDepartments() {
        return departmentRepository.findAll().stream()
                .map(dept -> new DepartmentResponse(dept.getId(), dept.getName()))
                .collect(Collectors.toList());
    }

    // 학과 하나만 조회
    public DepartmentResponse getDepartmentById(Long departmentId) {
        return departmentRepository.findById(departmentId)
                .map(dept -> new DepartmentResponse(dept.getId(), dept.getName()))
                .orElseThrow(() -> new CustomException(ErrorCode.DEPARTMENT_NOT_FOUND));
    }
}
