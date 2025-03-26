package com.starbucks.back.agreement.application;

import com.starbucks.back.agreement.domain.Agreement;
import com.starbucks.back.agreement.dto.out.ResponseGetSignUpAgreementDto;

import java.util.List;

public interface AgreementService {

    List<ResponseGetSignUpAgreementDto> getSignUpAgreements();
}
