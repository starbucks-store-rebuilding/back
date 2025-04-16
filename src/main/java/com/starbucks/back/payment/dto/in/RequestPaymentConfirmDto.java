package com.starbucks.back.payment.dto.in;

import com.starbucks.back.payment.domain.Payment;
import com.starbucks.back.payment.domain.PaymentStatus;
import com.starbucks.back.payment.vo.in.RequestPaymentConfirmVo;
import lombok.Builder;
import lombok.Getter;

import java.time.LocalDateTime;
import java.util.UUID;

@Getter
public class RequestPaymentConfirmDto {
    private String userUuid;
    private String paymentCode;      // toss의 paymentKey
    private String paymentUuid;     // toss의 orderId
    private Integer totalAmount;    // toss의 amount

    @Builder
    public RequestPaymentConfirmDto(
            String userUuid,
            String paymentCode,
            String paymentUuid,
            Integer totalAmount
    ) {
        this.userUuid = userUuid;
        this.paymentCode = paymentCode;
        this.paymentUuid = paymentUuid;
        this.totalAmount = totalAmount;
    }

    public static RequestPaymentConfirmDto from(
            String userUuid,
            RequestPaymentConfirmVo requestPaymentConfirmVo
    ) {
        return RequestPaymentConfirmDto.builder()
                .userUuid(userUuid)
                .paymentCode(requestPaymentConfirmVo.getPaymentKey())
                .paymentUuid(requestPaymentConfirmVo.getOrderId())
                .totalAmount(requestPaymentConfirmVo.getAmount())
                .build();
    }

    public Payment updateSuccessPayment(
            Payment payment,
            String paymentCode,
            String method,
            Integer amount,
            PaymentStatus paymentStatus,
            LocalDateTime approvedAt
    ) {
        return Payment.builder()
                .id(payment.getId())
                .userUuid(userUuid)
                .paymentUuid(paymentUuid)
                .paymentCode(paymentCode)
                // amount, method, orderName은 Payment 테이블에 저장하지 않음. 차후 추가될 수도 있음
                .saleAmount(payment.getSaleAmount())
                .totalAmount(totalAmount)
                .status(paymentStatus)
                .pgProvider("TOSS")
                .pgTid(payment.getPgTid())
                .paymentToken(payment.getPaymentToken())
                .failReason(payment.getFailReason())
                .approvedAt(approvedAt)
                .build();
    }

    public Payment updateFailPayment(
            Payment payment,
            String failReason
    )
    {
        return Payment.builder()
                .id(payment.getId())
                .userUuid(userUuid)
                .paymentUuid(paymentUuid)
                .paymentCode(paymentCode)
                // amount, method, orderName은 Payment 테이블에 저장하지 않음. 차후 추가될 수도 있음
                .saleAmount(payment.getSaleAmount())
                .totalAmount(totalAmount)
                .status(PaymentStatus.ABORTED)
                .pgProvider("TOSS")
                .pgTid(payment.getPgTid())
                .paymentToken(payment.getPaymentToken())
                .failReason(failReason)
                .approvedAt(payment.getApprovedAt())
                .build();
    }

}
