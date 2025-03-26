package com.starbucks.back.agreement.domain.converter;

import com.starbucks.back.agreement.domain.enums.AgreementType;
import jakarta.persistence.AttributeConverter;
import jakarta.persistence.Convert;
import jakarta.persistence.Converter;

import java.util.Arrays;

@Converter
public class AgreementTypeConverter implements AttributeConverter<AgreementType, Integer> {

    @Override  //Enum을 DB에 저장할 때 어떻게 변환할지 정의
    public Integer convertToDatabaseColumn(AgreementType agreementType) {
        return agreementType != null ? agreementType.getCode() : null;
    }

    @Override  //DB에서 읽어온 값을 Enum으로 변환
    public AgreementType convertToEntityAttribute(Integer code) {
        return Arrays.stream(AgreementType.values())
                .filter(agreementType -> agreementType.getCode() == code)
                .findFirst()
                .orElseThrow(() -> new IllegalArgumentException("Invalid agreement type code from DB: " + code));
    }
}
