package com.example.auth.domain.user.Service;

import com.example.auth.domain.category.DepartmentRepository;
import com.example.auth.domain.profile.Profile;
import com.example.auth.domain.profile.ProfileRepository;
import com.example.auth.domain.user.User;
import com.example.auth.domain.user.UserRepository;
import com.example.auth.domain.user.dto.*;
import com.example.auth.security.JwtTokenProvider;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import com.example.auth.exception.CustomException;
import com.example.auth.exception.ErrorCode;

@Service
@RequiredArgsConstructor
public class AuthService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtTokenProvider jwtTokenProvider;
    private final ProfileRepository profileRepository;

    public SignupResponse signup(SignupRequest request) {
        if (userRepository.existsByUsername(request.getUsername())) {
            throw new CustomException(ErrorCode.DUPLICATE_USERNAME);
        }

        User user = User.builder()
                .username(request.getUsername())
                .password(passwordEncoder.encode(request.getPassword()))
                .studentNumber(request.getStudentNumber())
                .isMentor(request.getIsMentor())
                .build();

        userRepository.save(user);

        Profile profile = Profile.builder()
                .user(user)
                .bio("")
                .url("")
                .build();
        profileRepository.save(profile);

        return new SignupResponse(user.getUsername(), user.getStudentNumber(), user.getIsMentor(), user.getDepartment().getId());
    }

    public AuthResponse login(LoginRequest request) {
        User user = userRepository.findByUsername(request.getUsername())
                .orElseThrow(() -> new CustomException(ErrorCode.USER_NOT_FOUND));

        if (!passwordEncoder.matches(request.getPassword(), user.getPassword())) {
            throw new CustomException(ErrorCode.INVALID_PASSWORD);
        }

        String token = jwtTokenProvider.createToken(user.getId());

        return new AuthResponse(token);
    }
}
