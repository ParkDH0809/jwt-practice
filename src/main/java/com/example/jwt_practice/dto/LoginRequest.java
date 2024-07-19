package com.example.jwt_practice.dto;

import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class LoginRequest {

    private final String email;
    private final String password;

}
