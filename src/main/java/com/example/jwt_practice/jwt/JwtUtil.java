package com.example.jwt_practice.jwt;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import jakarta.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Component;

import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;
import java.nio.charset.StandardCharsets;
import java.util.Date;
import java.util.concurrent.TimeUnit;

@Component
public class JwtUtil {

    private static final String JTW_PREFIX = "Bearer ";

    @Value("${spring.application.name}")
    private String issuer;
    @Value("${jwt.token.secret}")
    private String secretKey;
    @Value("${jwt.token.access-expired-time}")
    private long accessExpiredTime;
    @Value("${jwt.token.refresh-expired-time}")
    private long refreshExpiredTime;

    private static SecretKey KEY;
    private static String ISSUER;
    private static long ACCESS_EXPIRED_TIME;
    private static long REFRESH_EXPIRED_TIME;

    @PostConstruct
    public void init() {
        ISSUER = this.issuer;
        ACCESS_EXPIRED_TIME = this.accessExpiredTime;
        REFRESH_EXPIRED_TIME = this.refreshExpiredTime;
        KEY = new SecretKeySpec(secretKey.getBytes(StandardCharsets.UTF_8), Jwts.SIG.HS256.key().build().getAlgorithm());
    }

    public static String createAccessToken(String userName){
        return JTW_PREFIX + Jwts.builder()
                .issuer(ISSUER)
                .issuedAt(new Date(System.currentTimeMillis()))
                .expiration(new Date(System.currentTimeMillis() + ACCESS_EXPIRED_TIME))
                .claim("memberEmail", userName)
                .signWith(KEY)
                .compact();
    }

    public static String createRefreshToken(String userName){
        return JTW_PREFIX + Jwts.builder()
                .issuer(ISSUER)
                .issuedAt(new Date(System.currentTimeMillis()))
                .expiration(new Date(System.currentTimeMillis() + REFRESH_EXPIRED_TIME))
                .claim("memberEmail", userName)
                .signWith(KEY)
                .compact();
    }

    //member Email 반환
    public static String getLoginEmail(String token) {
        return decodeJwt(token).get("memberEmail").toString();
    }

    public static Long getExpiredTime(String token) {
        return decodeJwt(token).getExpiration().getTime();
    }
    // 발급된 Token이 만료 시간이 지났는지 체크]
    public static boolean isExpired(String token) {
        Date expiredDate = decodeJwt(token).getExpiration();
        // Token의 만료 날짜가 지금보다 이전인지 check
        return expiredDate.before(new Date());
    }

    public static Claims decodeJwt(String token) {
        return Jwts.parser()
                .verifyWith(KEY)
                .build()
                .parseSignedClaims(token)
                .getPayload();
    }

    public static String removeBearerPrefix(String token) {
        return token.split(" ")[1];
    }

}