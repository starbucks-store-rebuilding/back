package com.starbucks.back.category.vo.in;

import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class RequestAddCategoryVo {

    private String name;
    private String image;
    private String description;

}
