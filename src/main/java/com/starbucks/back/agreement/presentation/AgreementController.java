package com.starbucks.back.agreement.presentation;

import com.starbucks.back.agreement.application.AgreementService;
import com.starbucks.back.agreement.dto.out.ResponseGetAgreementDto;
import com.starbucks.back.agreement.vo.out.ResponseGetAgreementVo;
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
    public List<ResponseGetAgreementVo> getSignUpAgreements() {
        return agreementService.getSignUpAgreements()
                .stream()
                .map(ResponseGetAgreementDto::toVo)
                .toList();
    }

}
