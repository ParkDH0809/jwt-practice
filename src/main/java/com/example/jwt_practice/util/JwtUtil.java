package com.example.jwt_practice.util;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import jakarta.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;
import java.nio.charset.StandardCharsets;
import java.util.Date;

@Component
public class JwtUtil {

    @Value("${spring.application.name}")
    private String issuer;
    @Value("${jwt.token.secret}")
    private String secretKey;
    @Value("${jwt.token.expired-time}")
    private long expiredTime;

    private static SecretKey key;
    private static String ISSUER;
    private static long EXPIRED_TIME;

    @PostConstruct
    public void init() {
        ISSUER = this.issuer;
        EXPIRED_TIME = this.expiredTime;
        key = new SecretKeySpec(secretKey.getBytes(StandardCharsets.UTF_8), Jwts.SIG.HS256.key().build().getAlgorithm());
    }

    public String createJwt(String userName){
        return Jwts.builder()
                .issuer(ISSUER)
                .issuedAt(new Date(System.currentTimeMillis()))
                .expiration(new Date(System.currentTimeMillis() + EXPIRED_TIME))
                .claim("memberEmail", userName)
                .signWith(key)
                .compact();
    }

    //member Email 반환
    public static String getLoginEmail(String token) {
        return decodeJwt(token).get("memberEmail").toString();
    }

    // 밝급된 Token이 만료 시간이 지났는지 체크]
    public static boolean isExpired(String token) {
        Date expiredDate = decodeJwt(token).getExpiration();
        // Token의 만료 날짜가 지금보다 이전인지 check
        return expiredDate.before(new Date());
    }

    public static Claims decodeJwt(String token) {
        return Jwts.parser()
                .verifyWith(key)
                .build()
                .parseSignedClaims(token)
                .getPayload();
    }

}