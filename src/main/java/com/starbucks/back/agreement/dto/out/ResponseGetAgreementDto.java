package com.starbucks.back.agreement.dto.out;

import com.starbucks.back.agreement.domain.Agreement;
import com.starbucks.back.agreement.domain.enums.AgreementType;
import com.starbucks.back.agreement.vo.out.ResponseGetAgreementVo;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class ResponseGetAgreementDto {
    private Long id;
    private String name;
    private String description;
    private Boolean required;
    private AgreementType type;

    @Builder
    public ResponseGetAgreementDto(Long id, String name, String description, Boolean required, AgreementType type) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.required = required;
        this.type = type;
    }

    public static ResponseGetAgreementDto from(Agreement agreement) {
        return ResponseGetAgreementDto.builder()
                .id(agreement.getId())
                .name(agreement.getName())
                .description(agreement.getDescription())
                .required(agreement.isRequired())
                .type(agreement.getType())
                .build();
    }

    public ResponseGetAgreementVo toVo() {
        return ResponseGetAgreementVo.builder()
                .id(id)
                .name(name)
                .description(description)
                .required(required)
                .type(type)
                .build();
    }
}
