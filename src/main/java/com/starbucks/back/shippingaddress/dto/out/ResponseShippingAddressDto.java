package com.starbucks.back.shippingaddress.dto.out;

import com.starbucks.back.shippingaddress.domain.ShippingAddress;
import com.starbucks.back.shippingaddress.vo.out.ResponseShippingAddressVo;
import lombok.Builder;
import lombok.Getter;

// id가 주어졌을 때 배송지 조회 dto
@Getter
public class ResponseShippingAddressDto {
    private final Long id;
    private final String addressName;
    private final String recipientName;
    private final String zipCode;
    private final String baseAddress;
    private final String detailAddress;
    private final String phoneNumber;
    private final String secondPhoneNumber;
    private final String shippingNote;
    private final Boolean defaultAddress;

    @Builder
    public ResponseShippingAddressDto(
            Long id,
            String addressName,
            String recipientName,
            String zipCode,
            String baseAddress,
            String detailAddress,
            String phoneNumber,
            String secondPhoneNumber,
            String shippingNote,
            Boolean defaultAddress
    ) {
        this.id = id;
        this.addressName = addressName;
        this.recipientName = recipientName;
        this.zipCode = zipCode;
        this.baseAddress = baseAddress;
        this.detailAddress = detailAddress;
        this.phoneNumber = phoneNumber;
        this.secondPhoneNumber = secondPhoneNumber;
        this.shippingNote = shippingNote;
        this.defaultAddress = defaultAddress;
    }

    // entity => dto
    public static ResponseShippingAddressDto from(ShippingAddress shippingAddress) {
        return ResponseShippingAddressDto.builder()
                .id(shippingAddress.getId())
                .addressName(shippingAddress.getAddressName())
                .recipientName(shippingAddress.getRecipientName())
                .zipCode(shippingAddress.getZipCode())
                .baseAddress(shippingAddress.getBaseAddress())
                .detailAddress(shippingAddress.getDetailAddress())
                .phoneNumber(shippingAddress.getPhoneNumber())
                .secondPhoneNumber(shippingAddress.getSecondPhoneNumber())
                .shippingNote(shippingAddress.getShippingNote())
                .defaultAddress(shippingAddress.getDefaultAddress())
                .build();
    }

    //
    public ResponseShippingAddressVo toVo() {
        return ResponseShippingAddressVo.builder()
                .id(id)
                .addressName(addressName)
                .recipientName(recipientName)
                .zipCode(zipCode)
                .baseAddress(baseAddress)
                .detailAddress(detailAddress)
                .phoneNumber(phoneNumber)
                .secondPhoneNumber(secondPhoneNumber)
                .shippingNote(shippingNote)
                .defaultAddress(defaultAddress)
                .build();

    }
}
