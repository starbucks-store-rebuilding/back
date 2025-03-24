package com.starbucks.back.user.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.starbucks.back.common.entity.BaseEntity;
import com.starbucks.back.user.domain.converter.UserGenderConverter;
import com.starbucks.back.user.domain.enums.UserGender;
import com.starbucks.back.user.domain.enums.UserState;
import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)  //기본 생성자 생성, 외부에서 생성자 호출 방지
public class User extends BaseEntity {
    @Id  //primary key
    @GeneratedValue(strategy = GenerationType.IDENTITY)  //auto increment
    @JsonIgnore  //json으로 변환 시 제외
    private Long id;

    @Column(name = "user_uuid", nullable = false, unique = true, length = 36, updatable = false)
    private String userUuid;

    @Column(name = "email", nullable = false, unique = true, updatable = false)
    private String email;

    @Column(name = "password", nullable = false, length = 60)
    private String password;

    @Column(name = "nickname")
    private String nickname;

    @Column(name = "name", nullable = false)
    private String name;

    @Column(name = "phone_number", nullable = false, unique = true)
    private String phoneNumber;

    @Column(name = "birthdate", nullable = false)
    private String birthdate;

    @Convert(converter = UserGenderConverter.class)
    @Column(name = "gender", nullable = false, length = 1)
    private UserGender gender;

    @Column(name = "state", nullable = false, length = 1)
    private UserState state;


    @Builder
    public User(Long id, String userUuid, String email, String password, String nickname, String name,
                String phoneNumber, String birthdate, UserGender gender, UserState state)
    {
        this.id = id;
        this.userUuid = userUuid;
        this.email = email;
        this.password = password;
        this.nickname = nickname;
        this.name = name;
        this.phoneNumber = phoneNumber;
        this.birthdate = birthdate;
        this.gender = gender;
        this.state = state;
    }
}
