package com.starbucks.back.shippingaddress.infrastructure;

import com.starbucks.back.shippingaddress.domain.ShippingAddress;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Optional;

public interface ShippingAddressRepository extends JpaRepository<ShippingAddress, Long> {

    /**
     * 배송지 uuid로 배송지 조회
     * @param uuid
     * @return
     */
    Optional<ShippingAddress> findByShippingAddressUuidAndDeletedFalse(String uuid);

    /**
     * 배송지 겹치는지 확인
     * @param zipCode
     * @param baseAddress
     * @param detailAddress
     * @return
     */
    Boolean existsByZipCodeAndBaseAddressAndDetailAddressAndDeletedFalse(
            String zipCode,
            String baseAddress,
            String detailAddress
    );

    /**
     * 배송지테이블 전체 삭제 by userUuid
     * @param userUuid
     * @return
     */
    @Modifying
    @Query("""
        UPDATE ShippingAddress sa SET sa.deleted = true
        WHERE sa.shippingAddressUuid IN (
            SELECT usa.shippingAddressUuid
            FROM UserShippingAddress usa
            WHERE usa.userUuid = :userUuid AND usa.deleted = false
        )
        AND sa.deleted = false
    """)
    void bulkSoftDeleteShippingAddressesByUserUuid(@Param("userUuid") String userUuid);
}
