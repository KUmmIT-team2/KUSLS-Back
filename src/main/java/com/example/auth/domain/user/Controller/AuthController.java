package com.example.auth.domain.user.Controller;

import com.example.auth.domain.user.Service.AuthService;
import com.example.auth.domain.user.dto.*;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;


@RestController
@RequestMapping("/auth")
@Tag(name = "인증", description = "회원가입/로그인")

@RequiredArgsConstructor
public class AuthController {

    private final AuthService authService;

    @PostMapping("/signup")
    @Operation(summary = "회원가입", description = "사용자의 회원가입을 처리합니다.")
    @ApiResponse(responseCode = "200", description = "회원가입 성공",
            content = @Content(schema = @Schema(implementation = SignupResponse.class)))
    public SignupResponse signup(@RequestBody SignupRequest request) {
        return authService.signup(request);
    }

    @PostMapping("/login")
    @Operation(summary = "로그인", description = "사용자의 로그인 요청을 처리하고 토큰을 반환합니다.")
    @ApiResponse(responseCode = "200", description = "토큰 발급 성공",
            content = @Content(schema = @Schema(implementation = AuthResponse.class)))
    public AuthResponse login(@RequestBody LoginRequest request) {
        return authService.login(request);
    }
}
