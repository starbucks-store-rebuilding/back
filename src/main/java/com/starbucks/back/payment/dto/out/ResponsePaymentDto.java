package com.starbucks.back.payment.dto.out;

import com.starbucks.back.payment.domain.Payment;
import com.starbucks.back.payment.domain.PaymentStatus;
import com.starbucks.back.payment.vo.out.ResponsePaymentVo;
import lombok.Builder;
import lombok.Getter;

import java.time.LocalDateTime;

@Getter
public class ResponsePaymentDto {
    private Long id;
    private String userUuid;
    private String paymentUuid;
    private String paymentCode;
    private String couponUuid;
    private Integer totalOriginPrice;
    private Integer totalPurchasePrice;
    private PaymentStatus paymentStatus;
    private String paymentToken;
    private String failReason;
    private LocalDateTime approvedAt;
    private String method;
    private String orderName;
    private String orderListUuid;

    @Builder
    public ResponsePaymentDto(
            Long id,
            String userUuid,
            String paymentUuid,
            String paymentCode,
            String couponUuid,
            Integer totalOriginPrice,
            Integer totalPurchasePrice,
            PaymentStatus paymentStatus,
            String paymentToken,
            String failReason,
            LocalDateTime approvedAt,
            String method,
            String orderName,
            String orderListUuid
    ) {
        this.id = id;
        this.userUuid = userUuid;
        this.paymentUuid = paymentUuid;
        this.paymentCode = paymentCode;
        this.couponUuid = couponUuid;
        this.totalOriginPrice = totalOriginPrice;
        this.totalPurchasePrice = totalPurchasePrice;
        this.paymentStatus = paymentStatus;
        this.paymentToken = paymentToken;
        this.failReason = failReason;
        this.approvedAt = approvedAt;
        this.method = method;
        this.orderName = orderName;
        this.orderListUuid = orderListUuid;
    }

    // entity -> dto
    public static ResponsePaymentDto from(Payment payment) {
        return ResponsePaymentDto.builder()
                .id(payment.getId())
                .userUuid(payment.getUserUuid())
                .paymentUuid(payment.getPaymentUuid())
                .paymentCode(payment.getPaymentCode())
                .couponUuid(payment.getCouponUuid())
                .totalOriginPrice(payment.getTotalOriginPrice())
                .totalPurchasePrice(payment.getTotalPurchasePrice())
                .paymentStatus(payment.getStatus())
                .paymentToken(payment.getPaymentToken())
                .failReason(payment.getFailReason())
                .approvedAt(payment.getApprovedAt())
                .method(payment.getMethod())
                .orderName(payment.getOrderName())
                .orderListUuid(payment.getOrderListUuid())
                .build();
    }

    // dto -> vo
    public ResponsePaymentVo toVo() {
        return ResponsePaymentVo.builder()
                .totalOriginPrice(totalOriginPrice)
                .totalPurchasePrice(totalPurchasePrice)
                .totalSalePrice(totalOriginPrice-totalPurchasePrice)
                .paymentStatus(paymentStatus)
                .method(method)
                .approvedAt(approvedAt)
                .build();
    }
}
