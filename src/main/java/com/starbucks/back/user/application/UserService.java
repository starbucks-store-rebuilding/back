package com.starbucks.back.user.application;

import com.starbucks.back.user.dto.in.RequestSignUpDto;

public interface UserService {
    void signUp(RequestSignUpDto requestSignUpDto); // 회원가입
    boolean emailExists(String email); // 이메일 중복 확인
    boolean nicknameExists(String nickname); // 닉네임 중복 확인


}
