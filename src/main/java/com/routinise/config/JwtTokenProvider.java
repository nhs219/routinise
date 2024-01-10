package com.routinise.config;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;
import java.sql.SQLOutput;
import java.sql.Timestamp;
import java.time.Instant;
import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.Base64;
import java.util.Date;

@Configuration
@PropertySource("classpath:jwt.yml")
public class JwtTokenProvider {

    @Value("${secret-key}")
    private String secretKey;

    @Value("${expiration-hours}")
    private int expirationHours;

    @Value("${issuer}")
    private String issuer;

    public String createToken(String userId) {
        SecretKey key = Keys.hmacShaKeyFor(Base64.getDecoder().decode(secretKey));

        return Jwts.builder()
                .signWith(key)
                .setSubject(userId)
                .setIssuer(issuer)  // JWT 토큰 발급자
                .setIssuedAt(Timestamp.valueOf(LocalDateTime.now()))    // JWT 토큰 발급 시간
                .setExpiration(Date.from(Instant.now().plus(expirationHours, ChronoUnit.HOURS)))    // JWT 토큰 만료 시간
                .compact(); // JWT 토큰 생성
    }

}
