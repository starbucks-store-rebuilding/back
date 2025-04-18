package com.starbucks.back.shippingaddress.presentation;

import com.starbucks.back.common.entity.BaseResponseEntity;
import com.starbucks.back.common.entity.BaseResponseStatus;
import com.starbucks.back.common.util.SecurityUtil;
import com.starbucks.back.shippingaddress.application.UserShippingAddressService;
import com.starbucks.back.shippingaddress.dto.in.RequestDeleteShippingAddressDto;
import com.starbucks.back.shippingaddress.dto.in.RequestShippingAddressAndUserDto;
import com.starbucks.back.shippingaddress.dto.in.RequestUpdateShippingAddressDto;
import com.starbucks.back.shippingaddress.dto.in.RequestUpdateUserShippingAddressDto;
import com.starbucks.back.shippingaddress.dto.out.ResponseReadShippingAddressWithDefaultedDto;
import com.starbucks.back.shippingaddress.dto.out.ResponseReadUserShippingAddressDto;
import com.starbucks.back.shippingaddress.vo.in.RequestDeleteShippingAddressVo;
import com.starbucks.back.shippingaddress.vo.in.RequestShippingAddressAndUserVo;
import com.starbucks.back.shippingaddress.vo.in.RequestUpdateShippingAddressVo;
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
    private final SecurityUtil securityUtil;

    /**
     * 배송지 List 조회 by userUuid
     * @return
     */
    @Operation(summary = "getUserShippingAddressAllListByUserUuid API", description = "배송지 List 조회 API 입니다.", tags = {"ShippingAddress-Service"})
    @GetMapping("/list")
    public BaseResponseEntity<List<ResponseReadShippingAddressListVo>> getUserShippingAddressAllListByUserUuid() {
        String userUuid = securityUtil.getCurrentUserUuid();

        List<ResponseReadShippingAddressListVo> result = userShippingAddressService
                .getUserShippingAddressAllListByUserUuid(userUuid)
                .stream()
                .map(ResponseReadUserShippingAddressDto::toVo)
                .toList();

        return new BaseResponseEntity<>(result);
    }

//    /**
//     * 기본외배송지 List 조회 by userUuid
//     * @param userUuid
//     * @return
//     */
//    @Operation(summary = "getUserShippingAddressListByUserUuid API", description = "기본외배송지 List 조회 API 입니다.", tags = {"ShippingAddress-Service"})
//    @GetMapping("/user/not-default")
//    public BaseResponseEntity<List<ResponseReadShippingAddressListVo>> getUserShippingAddressListByUserUuid(
//            @RequestHeader("userUuid") String userUuid
//    ) {
//        List<ResponseReadShippingAddressListVo> result = userShippingAddressService
//                .getUserShippingAddressListByUserUuid(userUuid)
//                .stream()
//                .map(ResponseReadUserShippingAddressDto::toVo)
//                .toList();
//
//        return new BaseResponseEntity<>(result);
//    }

    /**
     * 기본배송지 객체 조회 by userUuid
     * @return
     */
    @Operation(summary = "getUserDefaultShippingAddressUuid API", description = "기본배송지 객체 조회 API 입니다.", tags = {"ShippingAddress-Service"})
    @GetMapping("/user/default")
    public BaseResponseEntity<ResponseShippingAddressVo> getUserDefaultShippingAddress() {
        String userUuid = securityUtil.getCurrentUserUuid();
        ResponseReadShippingAddressWithDefaultedDto responseReadShippingAddressWithDefaultedDto = userShippingAddressService
                .getDefaultShippingAddressByUserUuid(userUuid);

        return new BaseResponseEntity<>(responseReadShippingAddressWithDefaultedDto.toVo());
    }

    /**
     * 배송지 삭제 by userUuid, shippingAddressUuid
     * @param
     */
    @Operation(summary = "DeleteShippingAddress API", description = "배송지 객체 삭제 API 입니다.", tags = {"ShippingAddress-Service"})
    @Transactional
    @DeleteMapping
    public BaseResponseEntity<Void> deleteShippingAddress(
            @RequestBody RequestDeleteShippingAddressVo requestDeleteShippingAddressVo
    ) {
        String userUuid = securityUtil.getCurrentUserUuid();

        userShippingAddressService.deleteShippingAddress(
                RequestDeleteShippingAddressDto.from(userUuid, requestDeleteShippingAddressVo)
        );

        return new BaseResponseEntity<>(BaseResponseStatus.SUCCESS);
    }

    /**
     * 배송지 전부 삭제 by userUuid
     */
    @Operation(summary = "deleteAllShippingAddressByUserUuid API", description = "배송지 전체 삭제 API 입니다.", tags = {"ShippingAddress-Service"})
    @Transactional
    @DeleteMapping("/user")
    public BaseResponseEntity<Void> deleteAllShippingAddressByUserUuid() {
        String userUuid = securityUtil.getCurrentUserUuid();

        userShippingAddressService.deleteAllShippingAddressByUserUuid(userUuid);

        return new BaseResponseEntity<>(BaseResponseStatus.SUCCESS);
    }

//    /**
//     * 배송지 default 변경 by [{userUuid, shippingAddressUuid, defaulted}, ..]
//     */
//    @Operation(summary = "updateUserShippingAddressDefaulted API", description = "배송지 defaulted 수정 API 입니다.", tags = {"ShippingAddress-Service"})
//    @Transactional
//    @PutMapping("/user/default")
//    public BaseResponseEntity<Void> updateUserShippingAddressDefaulted(
//            @RequestHeader("userUuid") String userUuid,
//            @RequestBody List<RequestUpdateUserShippingAddressVo> requestUpdateUserShippingAddressVoList
//    ) {
//
//        List<RequestUpdateUserShippingAddressDto> dtoList = requestUpdateUserShippingAddressVoList
//                .stream()
//                .map(vo -> RequestUpdateUserShippingAddressDto.of(userUuid, vo))
//                .toList();
//
//        userShippingAddressService.updateUserShippingAddressDefaulted(dtoList);
//
//        return new BaseResponseEntity<>(BaseResponseStatus.SUCCESS);
//    }

    /**
     * 배송지 수정 by shippingAddressUuid
     * @param requestUpdateShippingAddressVo
     */
    @Operation(summary = "UpdateShippingAddressByUuid API", description = "배송지 객체 수정 API 입니다.", tags = {"ShippingAddress-Service"})
    @Transactional
    @PutMapping
    public BaseResponseEntity<Void> updateShippingAddressByUuid(
            @RequestBody RequestUpdateShippingAddressVo requestUpdateShippingAddressVo
    ) {
        String userUuid = securityUtil.getCurrentUserUuid();

        userShippingAddressService.updateShippingAddress(
                RequestUpdateShippingAddressDto.from(userUuid, requestUpdateShippingAddressVo));

        return new BaseResponseEntity<>(BaseResponseStatus.SUCCESS);
    }

    /**
     * 배송지 추가 by userUuid, requestShippingAddressAndUserVo
     * @param
     */
    @Operation(summary = "AddShippingAddress API", description = "배송지 추가 API 입니다.", tags = {"ShippingAddress-Service"})
    @Transactional
    @PostMapping
    public BaseResponseEntity<Void> addShippingAddress(
            @RequestBody RequestShippingAddressAndUserVo requestShippingAddressAndUserVo
    ) {
        String userUuid = securityUtil.getCurrentUserUuid();

        RequestShippingAddressAndUserDto requestShippingAddressAndUserDto= RequestShippingAddressAndUserDto.from(
                userUuid,
                requestShippingAddressAndUserVo
        );
        userShippingAddressService.addUserShippingAddress(requestShippingAddressAndUserDto);
        return new BaseResponseEntity<>(BaseResponseStatus.SUCCESS);
    }
}
