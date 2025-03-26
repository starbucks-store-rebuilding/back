package com.starbucks.back.option.dto.out;

import com.starbucks.back.option.domain.Color;
import com.starbucks.back.option.vo.in.RequestColorVo;
import com.starbucks.back.option.vo.out.ResponseColorVo;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class ResponseColorDto {

    private Long id;
    private String name;

    @Builder
    public ResponseColorDto(Long id, String name) {
        this.id = id;
        this.name = name;
    }

    public static ResponseColorDto from(Color color) {
        return ResponseColorDto.builder()
                .id(color.getId())
                .name(color.getName())
                .build();
    }

    public ResponseColorVo toVo() {
        return ResponseColorVo.builder()
                .id(id)
                .name(name)
                .build();
    }

}
