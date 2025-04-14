package com.starbucks.back.payment.dto.out;

import lombok.Builder;
import lombok.Getter;

@Getter
public class ResponsePaymentCreateDto {
    private String checkoutUrl;
    private String paymentUuid;

    @Builder
    public ResponsePaymentCreateDto(
            String checkoutUrl,
            String paymentUuid
    ) {
        this.checkoutUrl = checkoutUrl;
        this.paymentUuid = paymentUuid;
    }

    public static ResponsePaymentCreateDto from(
            String checkoutUrl,
            String paymentUuid
    ) {
        return ResponsePaymentCreateDto.builder()
                .checkoutUrl(checkoutUrl)
                .paymentUuid(paymentUuid)
                .build();
    }
}
