package com.example.jwt_practice.repository;

import com.example.jwt_practice.entity.RefreshToken;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface RefreshTokenRepository extends JpaRepository<RefreshToken, Long> {

    Optional<RefreshToken> findByRefreshToken(String refreshToken);
    boolean existsByRefreshToken(String refreshToken);
    boolean existsByMemberEmail(String memberEmail);
    void deleteByMemberEmail(String memberEmail);

}
