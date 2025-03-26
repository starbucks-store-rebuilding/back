package com.starbucks.back.agreement.presentation;

import com.starbucks.back.agreement.application.AgreementService;
import com.starbucks.back.agreement.domain.Agreement;
import com.starbucks.back.agreement.dto.out.ResponseGetSignUpAgreementDto;
import com.starbucks.back.agreement.vo.out.ResponseGetSignUpAgreementVo;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/v1/agreement")
@RequiredArgsConstructor
public class AgreementController {

    private final AgreementService agreementService;

    @GetMapping("/sign-up")
    public List<ResponseGetSignUpAgreementVo> getSignUpAgreements() {
        return agreementService.getSignUpAgreements()
                .stream()
                .map(ResponseGetSignUpAgreementDto::toVo)
                .toList();
    }

}
