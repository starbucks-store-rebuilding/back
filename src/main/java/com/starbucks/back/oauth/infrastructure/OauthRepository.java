package com.starbucks.back.oauth.infrastructure;

import com.starbucks.back.oauth.domain.Oauth;
import com.starbucks.back.oauth.domain.enums.SocialProvider;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface OauthRepository extends JpaRepository<Oauth, Long> {
    Optional<Oauth> findByProviderAndProviderUserId(SocialProvider provider, String providerUserId);
    List<Oauth> findByUserUuid(String userUuid);
    Boolean existsByUserUuid(String userUuid);
    void deleteByUserUuid(String userUuid);
}
