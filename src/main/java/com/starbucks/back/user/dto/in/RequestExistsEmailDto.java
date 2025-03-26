package com.starbucks.back.user.dto.in;

import com.starbucks.back.user.vo.in.RequestExistsEmailVo;
import lombok.Builder;
import lombok.Getter;

@Getter
public class RequestExistsEmailDto {
    private String email;

    @Builder
    public RequestExistsEmailDto(String email) {
        this.email = email;
    }

    public static RequestExistsEmailDto from(RequestExistsEmailVo requestExistsEmailVo) {
        return RequestExistsEmailDto.builder()
                .email(requestExistsEmailVo.getEmail())
                .build();
    }
}
