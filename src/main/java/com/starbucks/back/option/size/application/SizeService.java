package com.starbucks.back.option.size.application;

import com.starbucks.back.option.size.dto.in.RequestSizeDto;
import com.starbucks.back.option.size.dto.out.ResponseSizeDto;

import java.util.List;

public interface SizeService {

    /**
     * 사이즈 추가
     * @param requestSizeDto
     */
    void addSize(RequestSizeDto requestSizeDto);

    /**
     * 사이즈 id로 사이즈 조회
     * @param id
     */
    ResponseSizeDto getSizeById(Long id);

    /**
     * 사이즈 이름으로 사이즈 조회
     * @param name
     */
    ResponseSizeDto getSizeByName(String name);

    /**
     * 사이즈 전체 조회
     */
    List<ResponseSizeDto> getAllSizes();

    /**
     * 사이즈 수정
     * @param requestSizeDto
     */
    void updateSize(RequestSizeDto requestSizeDto);

    /**
     * 사이즈 삭제
     * @param requestSizeDto
     */
    void deleteSize(RequestSizeDto requestSizeDto);

}
