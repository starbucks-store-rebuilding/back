package com.starbucks.back.user.vo;

import com.starbucks.back.user.domain.enums.UserGender;
import lombok.Getter;

import java.time.LocalDate;

@Getter
public class SignUpRequestVo {
    private String email;
    private String password;
    private String name;
    private String nickname;
    private String phoneNumber;
    private LocalDate birthdate;
    private UserGender gender;
}
