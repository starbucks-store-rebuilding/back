package com.starbucks.back.user.domain.converter;

import com.starbucks.back.user.domain.enums.UserGender;
import jakarta.persistence.AttributeConverter;
import jakarta.persistence.Converter;

import java.util.Arrays;

@Converter
public class UserGenderConverter implements AttributeConverter<UserGender, String> {

    @Override
    public String convertToDatabaseColumn(UserGender gender) {
        return gender != null ? gender.getCode() : null;
    }

    @Override
    public UserGender convertToEntityAttribute(String code) {
        return Arrays.stream(UserGender.values())
                .filter(gender -> gender.getCode().equals(code))
                .findFirst()
                .orElseThrow(() -> new IllegalArgumentException("Invalid gender code from DB: " + code));
    }
}

