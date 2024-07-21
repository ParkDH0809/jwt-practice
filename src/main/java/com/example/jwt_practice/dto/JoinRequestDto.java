package com.example.jwt_practice.dto;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class JoinRequestDto {

    private String name;
    private String tel;
    private String email;
    private String password;
    private String role;

}
