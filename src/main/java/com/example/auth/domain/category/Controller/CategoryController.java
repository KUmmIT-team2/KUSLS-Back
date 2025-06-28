package com.example.auth.domain.category.Controller;

import com.example.auth.domain.category.service.CategoryService;
import com.example.auth.domain.category.dto.CollegeResponse;
import com.example.auth.domain.category.dto.DepartmentResponse;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/categories")
@Tag(name = "카테고리", description = "대학 및 학과 조회")
@RequiredArgsConstructor
public class CategoryController {

    private final CategoryService categoryService;

    @GetMapping("/colleges")
    @Operation(summary = "모든 단과대학 조회", description = "등록된 모든 단과대학(College)을 조회합니다.")
    public List<CollegeResponse> getAllColleges() {
        return categoryService.getAllColleges();
    }

    @GetMapping("/colleges/{collegeId}")
    @Operation(summary = "단과대학별 학과 조회", description = "특정 단과대학에 소속된 학과(Department) 목록을 조회합니다.")
    public List<DepartmentResponse> getDepartmentsByCollege(@PathVariable Long collegeId) {
        return categoryService.getDepartmentsByCollege(collegeId);
    }

    @GetMapping("/departments")
    @Operation(summary = "전체 학과 조회", description = "모든 학과(Department)를 조회합니다.")
    public List<DepartmentResponse> getAllDepartments() {
        return categoryService.getAllDepartments();
    }

    @GetMapping("/departments/{departmentId}")
    @Operation(summary = "학과 단건 조회", description = "특정 학과(Department)를 ID로 조회합니다.")
    @ApiResponse(responseCode = "200", description = "학과 조회 성공",
            content = @Content(schema = @Schema(implementation = DepartmentResponse.class)))
    public DepartmentResponse getDepartmentById(@PathVariable Long departmentId) {
        return categoryService.getDepartmentById(departmentId);
    }

}
