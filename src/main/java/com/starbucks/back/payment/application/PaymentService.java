package com.starbucks.back.payment.application;

import com.starbucks.back.payment.dto.in.RequestPaymentCreateDto;
import com.starbucks.back.payment.dto.out.ResponsePaymentCreateDto;

public interface PaymentService {
    ResponsePaymentCreateDto addPayment(RequestPaymentCreateDto requestPaymentCreateDto);

}
