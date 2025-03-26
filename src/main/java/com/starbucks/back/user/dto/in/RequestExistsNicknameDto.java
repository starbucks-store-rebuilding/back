package com.starbucks.back.user.dto.in;

import com.starbucks.back.user.vo.in.RequestExistsNicknameVo;
import lombok.Builder;
import lombok.Getter;

@Getter
public class RequestExistsNicknameDto {
    private String nickname;

    @Builder
    public RequestExistsNicknameDto(String nickname) {
        this.nickname = nickname;
    }

    /* RequestExistsNicknameVo -> RequestExistsNicknameDto */
    public static RequestExistsNicknameDto from(RequestExistsNicknameVo requestExistsNicknameVo) {
        return RequestExistsNicknameDto.builder()
                .nickname(requestExistsNicknameVo.getNickname())
                .build();
    }
}
