package com.starbucks.back.auth.application;

import com.starbucks.back.auth.dto.UserAuthDto;
import com.starbucks.back.auth.dto.in.RequestSignInDto;
import com.starbucks.back.auth.dto.in.RequestSignUpDto;
import com.starbucks.back.auth.dto.out.ResponseSignInDto;
import com.starbucks.back.common.entity.BaseResponseStatus;
import com.starbucks.back.common.exception.BaseException;
import com.starbucks.back.common.jwt.JwtProvider;
import com.starbucks.back.common.util.RedisUtil;
import com.starbucks.back.user.infrastructure.UserRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.concurrent.TimeUnit;

@Service
@RequiredArgsConstructor
public class AuthServiceImpl implements AuthService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtProvider jwtProvider;
    private final RedisUtil<String> redisUtil;

    @Transactional
    @Override
    public void signUp(RequestSignUpDto requestSignUpDto) {
        if(userRepository.existsByEmail(requestSignUpDto.getEmail())){
            throw new BaseException(BaseResponseStatus.DUPLICATED_EMAIL);
        } else if (userRepository.existsByNickname(requestSignUpDto.getNickname())){
            throw new BaseException(BaseResponseStatus.DUPLICATED_NICKNAME);
        } else if (userRepository.existsByPhoneNumber(requestSignUpDto.getPhoneNumber())){
            throw new BaseException(BaseResponseStatus.DUPLICATED_PHONE_NUMBER);
        }

        if (!"true".equals(redisUtil.get("SignUp:Verified:" + requestSignUpDto.getEmail()))) {
            throw new BaseException(BaseResponseStatus.SIGN_UP_NOT_VERIFIED);
        }
        userRepository.save(requestSignUpDto.toEntity(passwordEncoder));
    }

    @Transactional
    @Override
    public ResponseSignInDto signIn(
            RequestSignInDto requestSignInDto
    ) {
        final UserAuthDto userAuthDto = UserAuthDto.from(
                userRepository.findByEmail(requestSignInDto.getEmail())
                .orElseThrow(() -> new BaseException(BaseResponseStatus.INVALID_LOGIN))
        );

        // 비밀번호 검증
        if (!passwordEncoder.matches(requestSignInDto.getPassword(), userAuthDto.getPassword())) {
            throw new BaseException(BaseResponseStatus.INVALID_LOGIN);
        }

        try {
            // 인증 객체 생성
            final Authentication authentication = new UsernamePasswordAuthenticationToken(
                    userAuthDto.getUserUuid(), // username = 유저uuid
                    null, // 이미 인증 완료 객체를 수동 생성하는 것이기에 null
                    List.of() // 권한 필요 시 추가하도록 빈 리스트 넣음
            );

            final String accessToken = jwtProvider.generateAccessToken(authentication);
            final String refreshToken = jwtProvider.generateRefreshToken(authentication);

            redisUtil.set(
                    "Access:" + authentication.getName(),
                    accessToken,
                    30,
                    TimeUnit.MINUTES
            );

            redisUtil.set(
                    "Refresh:" + authentication.getName(),
                    refreshToken,
                    14,
                    TimeUnit.DAYS
            );

            return ResponseSignInDto.of(accessToken, refreshToken);
        } catch (Exception e) {
            throw new BaseException(BaseResponseStatus.LOGIN_FAILED);
        }
    }

    @Transactional
    @Override
    public ResponseSignInDto reissueAllToken(
            String refreshToken
    ) {
        final String userUuid = jwtProvider.validateAndGetUserUuid(refreshToken);
        final String redisAccessTokenKey = "Access:" + userUuid;
        final String redisRefreshTokenKey = "Refresh:" + userUuid;

        if (redisUtil.get(redisRefreshTokenKey) == null || !redisUtil.get(redisRefreshTokenKey).equals(refreshToken)) {
            throw new BaseException(BaseResponseStatus.INVALID_REFRESH_TOKEN);
        }

        Authentication authentication = new UsernamePasswordAuthenticationToken(
                userUuid,
                null,
                List.of()
        );

        final String newAccessToken = jwtProvider.generateAccessToken(authentication);
        final String newRefreshToken = jwtProvider.generateRefreshToken(authentication);

        redisUtil.set(redisAccessTokenKey, newAccessToken, 30, TimeUnit.MINUTES);
        redisUtil.set(redisRefreshTokenKey, newRefreshToken, 14, TimeUnit.DAYS);

        return ResponseSignInDto.of(newAccessToken, newRefreshToken);
   }

   @Transactional
   @Override
    public void logout(String refreshToken) {
           try {
               redisUtil.delete("Access:" + jwtProvider.validateAndGetUserUuid(refreshToken));
               redisUtil.delete("Refresh:" + jwtProvider.validateAndGetUserUuid(refreshToken));
           } catch (Exception e) {}
   }

    @Override
    public boolean existsEmail(String email) {
        return userRepository.existsByEmail(email);
    }

    @Override
    public boolean existsNickname(String nickname) {
        return userRepository.existsByNickname(nickname);
    }

}
