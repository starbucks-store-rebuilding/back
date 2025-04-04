package com.starbucks.back.shippingaddress.infrastructure;

import com.starbucks.back.shippingaddress.application.UserShippingAddressService;
import com.starbucks.back.shippingaddress.domain.UserShippingAddress;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface UserShippingAddressRepository extends JpaRepository<UserShippingAddress, Long> {

    /**
     * 유저 UUID로 기본외배송지List 조회
     * @param userUuid
     * @return
     */
    List<UserShippingAddress> findByUserUuidAndDefaultedFalseAndDeletedFalse(String userUuid);

    /**
     * 유저 UUID로 기본배송지 UUID 조회
     * @param userUuid
     * @return
     */
    Optional<UserShippingAddress> findByUserUuidAndDefaultedTrueAndDeletedFalse(String userUuid);

    /**
     * 유저배송지 존재 여부 파악
     * @param userUuid
     * @param shippingAddressUuid
     * @return
     */
    boolean existsByUserUuidAndShippingAddressUuidAndDeletedFalse(String userUuid, String shippingAddressUuid);

    /**
     * 유저 UUID로 배송지리스트 조회
     * @param userUuid
     * @return
     */
    List<UserShippingAddress> findAllByUserUuidAndDeletedFalse(String userUuid);

    /**
     * 유저배송지 전부 삭제 by userUuid
     */
    @Modifying
    @Query("""
        UPDATE UserShippingAddress usa
        SET usa.deleted = true
        WHERE usa.userUuid = :userUuid AND usa.deleted = false
    """)
    void bulkSoftDeleteUserShippingAddressesByUserUuid(@Param("userUuid") String userUuid);

    /**
     * 유저배송지 defaulted 변경
     * @param
     */
    Optional<UserShippingAddress> findByUserUuidAndShippingAddressUuidAndDeletedFalse(String userUuid, String shippingAddressUuid);
}
