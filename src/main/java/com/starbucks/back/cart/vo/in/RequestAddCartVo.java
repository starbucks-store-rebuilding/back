package com.starbucks.back.cart.vo.in;

import lombok.Getter;

@Getter
public class RequestAddCartVo {
    private Integer productQuantity;
    private Boolean checked;
    private String productUuid;
    private String productOptionUuid;
}
