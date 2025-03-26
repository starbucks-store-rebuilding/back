package com.starbucks.back.agreement.domain;

import com.starbucks.back.common.entity.BaseEntity;
import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class UserAgreement extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    private Agreement agreement;

    @Column(name = "agreed", nullable = false)
    private boolean agreed;

    @Column(name = "user_uuid", nullable = false, length = 36)
    private String userUuid;

    @Builder
    public UserAgreement(Long id, Agreement agreement, boolean agreed, String userUuid) {
        this.id = id;
        this.agreement = agreement;
        this.agreed = agreed;
        this.userUuid = userUuid;
    }
}
