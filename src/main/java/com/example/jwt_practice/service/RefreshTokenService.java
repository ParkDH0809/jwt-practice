package com.example.jwt_practice.service;

import com.example.jwt_practice.entity.RefreshToken;
import com.example.jwt_practice.jwt.JwtUtil;
import com.example.jwt_practice.repository.RefreshTokenRepository;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.net.http.HttpResponse;

@Service
public class RefreshTokenService {

    @Autowired
    private RefreshTokenRepository refreshTokenRepository;

    public void addRefreshToken(RefreshToken refreshToken) {
        refreshTokenRepository.save(refreshToken);
    }

    public boolean findRefreshToken(String refreshToken) {
        if(!refreshTokenRepository.existsByRefreshToken(refreshToken)) {
            System.out.println("refreshToken not found");
            return false;
        }

        if(!JwtUtil.getLoginEmail(JwtUtil.removeBearerPrefix(refreshToken)).equals(getMemberEmail(refreshToken))) {
            System.out.println("refreshToken not found");
            return false;
        }
        return true;
    }

    public String getMemberEmail(String refreshToken) {
        return refreshTokenRepository.findByRefreshToken(refreshToken).getMemberEmail();
    }

    public String createAccessToken(String email) {
        return JwtUtil.createAccessToken(email);
    }

}