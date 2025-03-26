package com.starbucks.back.agreement.infrastructure;

import com.starbucks.back.agreement.domain.Agreement;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AgreementRepository extends JpaRepository<Agreement, Long> {

}
