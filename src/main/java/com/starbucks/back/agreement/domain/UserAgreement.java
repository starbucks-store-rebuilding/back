package com.starbucks.back.agreement.domain;

import com.starbucks.back.common.entity.BaseEntity;
import jakarta.persistence.*;
import lombok.Builder;


public class UserAgreement extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "agreement_id")
    private Long agreementId;

    @Column(name = "agreed", nullable = false)
    private boolean agreed;

    @Column(name = "user_uuid", nullable = false, length = 36)
    private String userUuid;

    @Builder
    public UserAgreement(Long id, Long agreementId, boolean agreed, String userUuid) {
        this.id = id;
        this.agreementId = agreementId;
        this.agreed = agreed;
        this.userUuid = userUuid;
    }
}
