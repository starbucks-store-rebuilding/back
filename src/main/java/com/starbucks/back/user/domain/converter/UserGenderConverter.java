package com.starbucks.back.user.domain.converter;

import com.starbucks.back.user.domain.enums.UserGender;
import jakarta.persistence.AttributeConverter;
import jakarta.persistence.Converter;

import java.util.Arrays;

@Converter
public class UserGenderConverter implements AttributeConverter<UserGender, String> {

    @Override //Enum을 DB에 저장할 때 어떻게 변환할지 정의
    public String convertToDatabaseColumn(UserGender gender) {
        return gender != null ? gender.getCode() : null;
    }

    @Override //DB에서 읽어온 값을 Enum으로 변환
    public UserGender convertToEntityAttribute(String code) {
        return Arrays.stream(UserGender.values())
                .filter(gender -> gender.getCode().equals(code))
                .findFirst()
                .orElseThrow(() -> new IllegalArgumentException("Invalid gender code from DB: " + code));
    }
}

