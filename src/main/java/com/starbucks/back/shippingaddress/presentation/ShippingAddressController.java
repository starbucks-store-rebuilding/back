package com.starbucks.back.shippingaddress.presentation;

import com.starbucks.back.common.entity.BaseResponseEntity;
import com.starbucks.back.common.entity.BaseResponseStatus;
import com.starbucks.back.shippingaddress.application.ShippingAddressService;
import com.starbucks.back.shippingaddress.dto.in.RequestDeleteShippingAddressDto;
import com.starbucks.back.shippingaddress.dto.in.RequestUpdateShippingAddressDto;
import com.starbucks.back.shippingaddress.dto.out.ResponseReadShippingAddressDto;
import com.starbucks.back.shippingaddress.vo.in.RequestDeleteShippingAddressVo;
import com.starbucks.back.shippingaddress.vo.in.RequestUpdateShippingAddressVo;
import com.starbucks.back.shippingaddress.vo.out.ResponseShippingAddressVo;
import io.swagger.v3.oas.annotations.Operation;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/shipping-address")
@RequiredArgsConstructor
public class ShippingAddressController {

    private final ShippingAddressService shippingAddressService;
//    /**
//     * 배송지 추가
//     * @param
//     */
//    @Operation(summary = "AddShippingAddress API", description = "AddShippingAddress API 입니다.", tags = {"ShippingAddress-Service"})
//    @Transactional
//    @PostMapping
//    public BaseResponseEntity<Void> addShippingAddress(
//            @RequestHeader("userUuid") String userUuid,
//            @RequestBody RequestShippingAddressAndUserVo requestShippingAddressAndUserVo
//    ) {
//        RequestShippingAddressAndUserDto requestShippingAddressAndUserDto= RequestShippingAddressAndUserDto.from(
//                userUuid,
//                requestShippingAddressAndUserVo
//        );
//        shippingAddressService.addShippingAddress(requestShippingAddressAndUserDto);
//        return new BaseResponseEntity<>(BaseResponseStatus.SUCCESS);
//    }

    /**
     * 배송지 uuid로 배송지 조회
     * @param shippingAddressUuid
     */
    @Operation(summary = "GetShippingAddressByUuid API", description = "GetShippingAddressByUuid API 입니다.", tags = {"ShippingAddress-Service"})
    @GetMapping("/{shippingAddressUuid}")
    public BaseResponseEntity<ResponseShippingAddressVo> getShippingAddress(@PathVariable("shippingAddressUuid") String shippingAddressUuid) {
        ResponseReadShippingAddressDto responseReadShippingAddressDto = shippingAddressService.getShippingAddressByUuid(shippingAddressUuid);
        return new BaseResponseEntity<>(responseReadShippingAddressDto.toVo());
    }

    /**
     * 배송지 수정
     * @param requestUpdateShippingAddressVo
     */
    @PutMapping
    public BaseResponseEntity<Void> updateShippingAddressByUuid(
            @RequestBody RequestUpdateShippingAddressVo requestUpdateShippingAddressVo) {
        shippingAddressService
                .updateShippingAddress(
                        RequestUpdateShippingAddressDto.from(requestUpdateShippingAddressVo));
        return new BaseResponseEntity<>(BaseResponseStatus.SUCCESS);
    }

    /**
     * 배송지 삭제
     * @param
     */
    @Operation(summary = "DeleteShippingAddress API", description = "DeleteShippingAddress API 입니다.", tags = {"ShippingAddress-Service"})
    @DeleteMapping()
    public BaseResponseEntity<Void> deleteShippingAddressByUuid(@RequestBody RequestDeleteShippingAddressVo requestDeleteShippingAddressVo) {
        shippingAddressService.deleteShippingAddress(RequestDeleteShippingAddressDto.of(requestDeleteShippingAddressVo));
        return new BaseResponseEntity<>(BaseResponseStatus.SUCCESS);
    }
}
