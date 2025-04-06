package com.starbucks.back.product.vo.in;

import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class RequestAddProductCategoryListVo {

    private String productUuid;
    private Long categoryId;
    private Long subCategoryId;

}
