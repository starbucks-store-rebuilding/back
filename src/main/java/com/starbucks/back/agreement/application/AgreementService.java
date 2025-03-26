package com.starbucks.back.agreement.application;

import com.starbucks.back.agreement.dto.out.ResponseGetAgreementDto;

import java.util.List;

public interface AgreementService {

    List<ResponseGetAgreementDto> getSignUpAgreements();
}
