package com.example.jwt_practice.util;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;
import org.springframework.stereotype.Component;

import javax.crypto.SecretKey;
import java.nio.charset.StandardCharsets;
import java.util.Date;

@Component
public class JwtUtil {

    private static final String signatureKey = "TestTestTestTestTestTestTestTestTestTestTestTestTestTestTestTestTestTestTest";
    private static SecretKey key = Keys.hmacShaKeyFor(signatureKey.getBytes(StandardCharsets.UTF_8));

    public static String createJwt(String userName){
        long expiredMs = 1000 * 60 * 60L;

        return Jwts.builder()
                .claim("userName", userName)
                .setIssuedAt(new Date(System.currentTimeMillis()))
                .setExpiration(new Date(System.currentTimeMillis() + expiredMs))
                .signWith(SignatureAlgorithm.HS256, signatureKey)
                .compact();
    }

//    // 밝급된 Token이 만료 시간이 지났는지 체크
//    public static boolean isExpired(String token) {
//        Date expiredDate = extractClaims(token).getExpiration();
//        // Token의 만료 날짜가 지금보다 이전인지 check
//        return expiredDate.before(new Date());
//    }

    // Claims에서 loginId 꺼내기
    public static String getLoginEmail(String token) {
        return Jwts.parser()
                .verifyWith(key)
                .build()
                .parseSignedClaims(token)
                .getPayload()
                .get("email", String.class);
    }

}