package com.example.jwt_practice.service;

import com.example.jwt_practice.entity.RefreshToken;
import com.example.jwt_practice.repository.RefreshTokenRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class RefreshTokenService {

    @Autowired
    private RefreshTokenRepository refreshTokenRepository;

    public void addRefreshToken(RefreshToken refreshToken) {
        refreshTokenRepository.save(refreshToken);
    }

    public void findRefreshToken(String refreshToken) {
        if(refreshTokenRepository.existsByRefreshToken(refreshToken)) {

        }
    }

}