package com.starbucks.back.agreement.vo.out;

import com.starbucks.back.agreement.domain.enums.AgreementType;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class ResponseGetSignUpAgreementVo {
    private Long id;
    private String name;
    private String description;
    private boolean isRequired;
    private AgreementType type;

    @Builder
    public ResponseGetSignUpAgreementVo(Long id, String name, String description, boolean isRequired, AgreementType type) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.isRequired = isRequired;
        this.type = type;
    }

}
