package com.starbucks.back.user.domain.enums;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonValue;
import lombok.Getter;

import java.util.Arrays;

@Getter
public enum UserGender {
    MALE("M", "남", "남성"),
    FEMALE("F", "여", "여성");

    private final String code;
    private final String input;
    @JsonValue  // json으로 변환 시 해당 값을 사용
    private final String label;

    UserGender(String code, String input, String label) {
        this.code = code;
        this.input = input;
        this.label = label;
    }

    @JsonCreator /* 입력받은 값에 해당하는 UserGender를 찾아 반환하는 메소드 */
    public static UserGender fromInput(String input) {
        return Arrays.stream(UserGender.values())
                .filter(gender -> gender.input.equals(input))
                .findFirst()
                .orElseThrow(() -> new IllegalArgumentException("Invalid gender input: " + input));
    }
}
