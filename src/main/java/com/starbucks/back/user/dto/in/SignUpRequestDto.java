package com.starbucks.back.user.dto.in;

import com.starbucks.back.user.domain.User;
import com.starbucks.back.user.domain.enums.UserGender;
import com.starbucks.back.user.vo.SignUpRequestVo;
import lombok.Builder;
import lombok.Getter;

import java.time.LocalDate;

@Getter
public class SignUpRequestDto {
    private String email;
    private String password;
    private String name;
    private String nickname;
    private String phoneNumber;
    private LocalDate birthdate;
    private UserGender gender;

    @Builder
    public SignUpRequestDto(String email, String password, String name,
                            String nickname, String phoneNumber, LocalDate birthdate, UserGender gender)
    {
        this.email = email;
        this.password = password;
        this.name = name;
        this.nickname = nickname;
        this.phoneNumber = phoneNumber;
        this.birthdate = birthdate;
        this.gender = gender;
    }

    public User toEntitu(String userUuid) {
        return User.builder()
                .userUuid(userUuid)
                .email(email)
                .password(password)
                .name(name)
                .nickname(nickname)
                .phoneNumber(phoneNumber)
                .birthdate(birthdate)
                .gender(gender)
                .build();
    }

    public static SignUpRequestDto from(SignUpRequestVo signUpRequestVo) {
        return SignUpRequestDto.builder()
                .email(signUpRequestVo.getEmail())
                .password(signUpRequestVo.getPassword())
                .name(signUpRequestVo.getName())
                .nickname(signUpRequestVo.getNickname())
                .phoneNumber(signUpRequestVo.getPhoneNumber())
                .birthdate(signUpRequestVo.getBirthdate())
                .gender(signUpRequestVo.getGender())
                .build();
    }
}

