package com.starbucks.back.agreement.application;

import com.starbucks.back.agreement.domain.enums.AgreementType;
import com.starbucks.back.agreement.dto.out.ResponseGetAgreementDto;
import com.starbucks.back.agreement.infrastructure.AgreementRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class AgreementServiceImpl implements AgreementService {

    private final AgreementRepository agreementRepository;

    @Override
    public List<ResponseGetAgreementDto> getSignUpAgreements() {
        return agreementRepository.findByType(AgreementType.SIGNUP)
                .stream()
                .map(ResponseGetAgreementDto::from)
                .toList();
    }
}
