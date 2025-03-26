package com.starbucks.back.agreement.vo.out;

import com.starbucks.back.agreement.domain.enums.AgreementType;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class ResponseGetAgreementVo {
    private Long id;
    private String name;
    private String description;
    private Boolean required;
    private AgreementType type;

    @Builder
    public ResponseGetAgreementVo(Long id, String name, String description, Boolean required, AgreementType type) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.required = required;
        this.type = type;
    }

}
