package com.starbucks.back.agreement.domain.enums;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonValue;
import com.starbucks.back.user.domain.enums.UserGender;
import lombok.Getter;

import java.util.Arrays;

@Getter
public enum AgreementType {
    SUBSCRIBE(0, "회원가입 약관"),
    DELIVERY(1, "배송지 등록 약관");

    private final int code;
    @JsonValue  // json으로 변환 시 해당 값을 사용
    private final String label;

    AgreementType(int code, String label) {
        this.code = code;
        this.label = label;
    }

//    @JsonCreator /* 입력받은 값에 해당하는 AgreementType을 찾아 반환하는 메소드 */
//    public static AgreementType fromInput(int code) {
//        return Arrays.stream(AgreementType.values())
//                .filter(type -> type.code == code)
//                .findFirst()
//                .orElseThrow(() -> new IllegalArgumentException("Invalid gender input: " + code));
//    }
}
