package com.starbucks.back.user.presentation;

import com.starbucks.back.user.application.UserService;
import com.starbucks.back.user.dto.in.RequestExistsEmailDto;
import com.starbucks.back.user.dto.in.RequestExistsNicknameDto;
import com.starbucks.back.user.dto.in.RequestSignUpDto;
import com.starbucks.back.user.vo.RequestExistsEmailVo;
import com.starbucks.back.user.vo.RequestExistsNicknameVo;
import com.starbucks.back.user.vo.RequestSignUpVo;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequestMapping("/api/v1/user")
@RestController
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;

    @PostMapping("/sign-up") // 회원가입
    public void signUp(@RequestBody RequestSignUpVo requestSignUpVo) {
        userService.signUp(RequestSignUpDto.from(requestSignUpVo));
    }

    @PostMapping("/email/exists") // 이메일 중복 확인
    public boolean emailExists(@RequestBody RequestExistsEmailVo requestExistsEmailVo) {
        return userService.emailExists(RequestExistsEmailDto.from(requestExistsEmailVo).getEmail());
    }

    @PostMapping("/nickname/exists") // 닉네임 중복 확인
    public boolean nicknameExists(@RequestBody RequestExistsNicknameVo requestExistsNicknameVo) {
        return userService.nicknameExists(RequestExistsNicknameDto.from(requestExistsNicknameVo).getNickname());
    }


}
