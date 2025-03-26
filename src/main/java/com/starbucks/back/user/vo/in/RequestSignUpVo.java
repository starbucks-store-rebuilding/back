package com.starbucks.back.user.vo.in;

import com.starbucks.back.user.domain.enums.UserGender;
import lombok.Getter;

import java.time.LocalDate;

@Getter
public class RequestSignUpVo {
    private String email;
    private String password;
    private String name;
    private String nickname;
    private String phoneNumber;
    private LocalDate birthdate;
    private UserGender gender;
}
