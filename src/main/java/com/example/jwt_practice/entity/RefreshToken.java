package com.example.jwt_practice.entity;

import jakarta.persistence.*;
import lombok.*;

@Table(name = "refresh_token")
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Entity
@ToString
public class RefreshToken {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String refreshToken;
    private String memberEmail;

    public RefreshToken(String refreshToken, String memberId) {
        this.refreshToken = refreshToken;
        this.memberEmail = memberId;
    }

}