package com.example.auth.exception;

public enum ErrorCode {
    SUCCESS(200, "성공했습니다."),

    // 400: 잘못된 요청
    BAD_REQUEST(400, "잘못된 요청입니다."),
    DUPLICATE_USERNAME(400, "이미 존재하는 사용자명입니다."),
    DUPLICATE_EMAIL(400, "이미 존재하는 이메일입니다."),
    INVALID_INPUT(400, "입력값이 유효하지 않습니다."),
    NOT_A_MENTOR(400, "멘토가 아니므로 댓글을 작성할 수 없습니다"),

    // 401: 인증 실패
    UNAUTHORIZED(401, "로그인이 필요합니다."),
    INVALID_PASSWORD(401, "비밀번호가 일치하지 않습니다."),

    // 404: 리소스 없음
    NOT_FOUND(404, "리소스를 찾을 수 없습니다."),
    USER_NOT_FOUND(404, "사용자를 찾을 수 없습니다."),
    DEPARTMENT_NOT_FOUND(404, "해당 학과를 찾을 수 없습니다."),
    COLLEGE_NOT_FOUND(404, "해당 대학을 찾을 수 없습니다."),
    BADGE_NOT_FOUND(404, "뱃지를 찾을 수 없습니다."),

    // 500: 서버 오류
    INTERNAL_SERVER_ERROR(500, "서버 오류가 발생했습니다."),
    DATABASE_ERROR(500, "DB 오류가 발생했습니다.");

    private final int status;
    private final String message;

    ErrorCode(int status, String message) {
        this.status = status;
        this.message = message;
    }

    public int getStatus() {
        return status;
    }

    public String getMessage() {
        return message;
    }
}
