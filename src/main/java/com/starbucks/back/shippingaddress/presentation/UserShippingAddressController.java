package com.starbucks.back.shippingaddress.presentation;

import com.starbucks.back.common.entity.BaseResponseEntity;
import com.starbucks.back.common.entity.BaseResponseStatus;
import com.starbucks.back.shippingaddress.application.UserShippingAddressService;
import com.starbucks.back.shippingaddress.dto.in.RequestDeleteShippingAddressDto;
import com.starbucks.back.shippingaddress.dto.in.RequestShippingAddressAndUserDto;
import com.starbucks.back.shippingaddress.dto.in.RequestUpdateUserShippingAddressDto;
import com.starbucks.back.shippingaddress.dto.out.ResponseReadShippingAddressDto;
import com.starbucks.back.shippingaddress.dto.out.ResponseReadUserShippingAddressDto;
import com.starbucks.back.shippingaddress.vo.in.RequestDeleteShippingAddressVo;
import com.starbucks.back.shippingaddress.vo.in.RequestShippingAddressAndUserVo;
import com.starbucks.back.shippingaddress.vo.in.RequestUpdateUserShippingAddressVo;
import com.starbucks.back.shippingaddress.vo.out.ResponseReadShippingAddressListVo;
import com.starbucks.back.shippingaddress.vo.out.ResponseShippingAddressVo;
import io.swagger.v3.oas.annotations.Operation;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/shipping-address")
@RequiredArgsConstructor
public class UserShippingAddressController {

    private final UserShippingAddressService userShippingAddressService;

    /**
     * 유저 UUID로 기본외배송지 UUID List 조회
     * @param userUuid
     * @return
     */
    @Operation(summary = "getUserShippingAddressListByUserUuid API", description = "getUserShippingAddressListByUserUuid API 입니다.", tags = {"ShippingAddress-Service"})
    @GetMapping("/user/not-default")
    public BaseResponseEntity<List<ResponseReadShippingAddressListVo>> getUserShippingAddressListByUserUuid(@RequestHeader("userUuid") String userUuid) {
        List<ResponseReadShippingAddressListVo> result = userShippingAddressService.getUserShippingAddressListByUserUuid(userUuid)
                .stream()
                .map(ResponseReadUserShippingAddressDto::toVo)
                .toList();
        return new BaseResponseEntity<>(result);
    }

    /**
     * 유저 UUID로 기본배송지 UUID 조회
     * @param userUuid
     * @return
     */
    @Operation(summary = "getUserDefaultShippingAddressUuid API", description = "getUserDefaultShippingAddressUuid API 입니다.", tags = {"ShippingAddress-Service"})
    @GetMapping("/user/default")
    public BaseResponseEntity<ResponseShippingAddressVo> getUserDefaultShippingAddress(
            @RequestHeader("userUuid") String userUuid
    ) {
        ResponseReadShippingAddressDto responseReadShippingAddressDto = userShippingAddressService
                .getDefaultShippingAddressByUserUuid(userUuid);

        return new BaseResponseEntity<>(responseReadShippingAddressDto.toVo());
    }

    /**
     * 배송지 삭제
     * @param
     */
    @Operation(summary = "DeleteShippingAddress API", description = "DeleteShippingAddress API 입니다.", tags = {"ShippingAddress-Service"})
    @Transactional
    @DeleteMapping
    public BaseResponseEntity<Void> deleteShippingAddress(
            @RequestHeader("userUuid") String userUuid,
            @RequestBody RequestDeleteShippingAddressVo requestDeleteShippingAddressVo
    ) {
        userShippingAddressService.deleteShippingAddress(RequestDeleteShippingAddressDto.from(userUuid, requestDeleteShippingAddressVo));
        return new BaseResponseEntity<>(BaseResponseStatus.SUCCESS);
    }

    /**
     * 배송지 전부 삭제 by userUuid
     */
    @Transactional
    @Operation(summary = "deleteAllShippingAddressByUserUuid API", description = "deleteAllShippingAddressByUserUuid API 입니다.", tags = {"ShippingAddress-Service"})
    @DeleteMapping("/user")
    public BaseResponseEntity<Void> deleteAllShippingAddressByUserUuid(@RequestHeader("userUuid") String userUuid) {

        userShippingAddressService.deleteAllShippingAddressByUserUuid(userUuid);
        return null;
    }

    /**
     * 배송지 default 변경 by userUuid, shippingAddressUuid, defaulted
     */
    @Transactional
    @Operation(summary = "updateUserShippingAddressDefaulted API", description = "updateUserShippingAddressDefaulted API 입니다.", tags = {"ShippingAddress-Service"})
    @PutMapping("/user/default")
    public BaseResponseEntity<Void> updateUserShippingAddressDefaulted(
            @RequestHeader("userUuid") String userUuid,
            @RequestBody RequestUpdateUserShippingAddressVo requestUpdateUserShippingAddressVo
    ) {
        userShippingAddressService.updateUserShippingAddressDefaulted(
                RequestUpdateUserShippingAddressDto.of(userUuid, requestUpdateUserShippingAddressVo)
        );
        return new BaseResponseEntity<>(BaseResponseStatus.SUCCESS);
    }

    /**
     * 배송지 추가
     * @param
     */
    @Operation(summary = "AddShippingAddress API", description = "AddShippingAddress API 입니다.", tags = {"ShippingAddress-Service"})
    @Transactional
    @PostMapping
    public BaseResponseEntity<Void> addShippingAddress(
            @RequestHeader("userUuid") String userUuid,
            @RequestBody RequestShippingAddressAndUserVo requestShippingAddressAndUserVo
    ) {
        RequestShippingAddressAndUserDto requestShippingAddressAndUserDto= RequestShippingAddressAndUserDto.from(
                userUuid,
                requestShippingAddressAndUserVo
        );
        userShippingAddressService.addUserShippingAddress(requestShippingAddressAndUserDto);
        return new BaseResponseEntity<>(BaseResponseStatus.SUCCESS);
    }
}
