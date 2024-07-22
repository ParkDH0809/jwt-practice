package com.example.jwt_practice.handler;

import com.example.jwt_practice.dto.LoginResponseDto;
import com.example.jwt_practice.entity.RefreshToken;
import com.example.jwt_practice.jwt.JwtUtil;
import com.example.jwt_practice.service.RefreshTokenService;
import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.nio.charset.StandardCharsets;

@RequiredArgsConstructor
@Component
public class LoginSuccessHandler implements AuthenticationSuccessHandler {


    private final ObjectMapper objectMapper = new ObjectMapper();

    @Autowired
    private RefreshTokenService refreshTokenService;

    @Override
    public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response,
                                        Authentication authentication) throws IOException {

        String accessToken = JwtUtil.createAccessToken(authentication.getName());
        String refreshToken = JwtUtil.createRefreshToken(authentication.getName());

        LoginResponseDto loginResponseDto = new LoginResponseDto(accessToken, refreshToken);

        RefreshToken refreshTokenEntity = new RefreshToken(refreshToken, authentication.getName());
        refreshTokenService.addRefreshToken(refreshTokenEntity);

        response.setStatus(HttpStatus.OK.value());
        response.setContentType(MediaType.APPLICATION_JSON_VALUE);
        response.setCharacterEncoding(StandardCharsets.UTF_8.name());
        objectMapper.writeValue(response.getWriter(), loginResponseDto);
    }

}