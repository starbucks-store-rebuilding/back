package com.starbucks.back.agreement.dto.out;

import com.starbucks.back.agreement.domain.Agreement;
import com.starbucks.back.agreement.domain.enums.AgreementType;
import com.starbucks.back.agreement.vo.out.ResponseGetSignUpAgreementVo;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class ResponseGetSignUpAgreementDto {
    private Long id;
    private String name;
    private String description;
    private boolean isRequired;
    private AgreementType type;

    @Builder
    public ResponseGetSignUpAgreementDto(Long id, String name, String description, boolean isRequired, AgreementType type) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.isRequired = isRequired;
        this.type = type;
    }

    public static ResponseGetSignUpAgreementDto from(Agreement agreement) {
        return ResponseGetSignUpAgreementDto.builder()
                .id(agreement.getId())
                .name(agreement.getName())
                .description(agreement.getDescription())
                .isRequired(agreement.isRequired())
                .type(agreement.getType())
                .build();
    }

    public ResponseGetSignUpAgreementVo toVo() {
        return ResponseGetSignUpAgreementVo.builder()
                .id(id)
                .name(name)
                .description(description)
                .isRequired(isRequired)
                .type(type)
                .build();
    }
}
