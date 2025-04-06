package com.starbucks.back.auth.presentation;

import com.starbucks.back.auth.application.AuthService;
import com.starbucks.back.auth.dto.in.RequestExistsEmailDto;
import com.starbucks.back.auth.dto.in.RequestExistsNicknameDto;
import com.starbucks.back.auth.dto.in.RequestSignInDto;
import com.starbucks.back.auth.dto.in.RequestSignUpDto;
import com.starbucks.back.auth.dto.out.ResponseSignInDto;
import com.starbucks.back.auth.vo.in.RequestExistsEmailVo;
import com.starbucks.back.auth.vo.in.RequestExistsNicknameVo;
import com.starbucks.back.auth.vo.in.RequestSignInVo;
import com.starbucks.back.auth.vo.in.RequestSignUpVo;
import com.starbucks.back.auth.vo.out.ResponseSignInVo;
import com.starbucks.back.common.entity.BaseResponseEntity;
import com.starbucks.back.common.entity.BaseResponseStatus;
import com.starbucks.back.common.exception.BaseException;
import io.swagger.v3.oas.annotations.Operation;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseCookie;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/auth")
@RequiredArgsConstructor
public class AuthController {

    private final AuthService authService;

    @Operation(summary = "SignUp API", description = "회원가입", tags = {"Auth-service"})
    @PostMapping("/sign-up")
    public BaseResponseEntity<Void> signUp(
            @Valid @RequestBody RequestSignUpVo requestSignUpVo
    ) {
        authService.signUp(RequestSignUpDto.from(requestSignUpVo));
        return new BaseResponseEntity<>(BaseResponseStatus.SIGN_UP_SUCCESS);
    }

    @Operation(summary = "SignIn API", description = "로그인", tags = {"Auth-service"})
    @PostMapping("/sign-in")
    public BaseResponseEntity<ResponseSignInVo> signIn(
            @Valid @RequestBody RequestSignInVo requestSignInVo,
            HttpServletResponse httpServletResponse
    ) {
        return new BaseResponseEntity<>(
                BaseResponseStatus.SIGN_IN_SUCCESS,
                authService.signIn(
                        RequestSignInDto.from(requestSignInVo),
                        httpServletResponse
                ).toVo()
        );
    }

    @Operation(summary = "Reissue Token API", description = "토큰 재발급(access, refresh)", tags = {"Auth-service"})
    @PostMapping("/reissue")
    public BaseResponseEntity<ResponseSignInVo> reissueToken(
            HttpServletRequest httpServletRequest,
            HttpServletResponse httpServletResponse
    ) {
        return new BaseResponseEntity<>(
                authService.reissueToken(httpServletRequest, httpServletResponse).toVo()
        );
    }

    @Operation(summary = "Logout API", description = "로그아웃", tags = {"Auth-service"})
    @PostMapping("/logout")
    public BaseResponseEntity<Void> logout(
            HttpServletRequest httpServletRequest,
            HttpServletResponse httpServletResponse
    ) {
        authService.logout(httpServletRequest, httpServletResponse);
        return new BaseResponseEntity<>(BaseResponseStatus.LOGOUT_SUCCESS);
    }


    @Operation(summary = "Check Email API", description = "이메일 중복 확인", tags = {"Auth-service"})
    @PostMapping("/exists/email")
    public BaseResponseEntity<Boolean> emailExists(
            @Valid @RequestBody RequestExistsEmailVo requestExistsEmailVo
    ) {
        return new BaseResponseEntity<>(
                authService.existsEmail(RequestExistsEmailDto.from(requestExistsEmailVo).getEmail())
        );
    }

    @Operation(summary = "Check Nickname API", description = "닉네임 중복 확인", tags = {"Auth-service"})
    @PostMapping("/exists/nickname")
    public BaseResponseEntity<Boolean> nicknameExists(
            @Valid @RequestBody RequestExistsNicknameVo requestExistsNicknameVo
    ) {
        return new BaseResponseEntity<>(
                authService.existsNickname(RequestExistsNicknameDto.from(requestExistsNicknameVo).getNickname())
        );
    }
}
