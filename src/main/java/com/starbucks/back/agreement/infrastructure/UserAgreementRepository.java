package com.starbucks.back.agreement.infrastructure;

import com.starbucks.back.agreement.domain.UserAgreement;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserAgreementRepository extends JpaRepository<UserAgreement, Long> {
    Optional<UserAgreement> findByUserUuid(String userUuid);
}
