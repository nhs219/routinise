package com.routinise.config;

import com.routinise.security.JwtTokenProvider;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import javax.crypto.SecretKey;
import java.util.Base64;


@Slf4j
@SpringBootTest
class JwtTokenProviderTest {

    @Autowired
    JwtTokenProvider tokenProvider;

    @Test
    void makeKey() {
//        SecretKey key = Keys.secretKeyFor(SignatureAlgorithm.HS256);
//        String keyStr = Base64.getEncoder().encodeToString(key.getEncoded());
//        String token = tokenProvider.createToken("test:ADMIN");
//        log.info("token :: {}", token);
//
//        String validation = tokenProvider.validateTokenAndGetSubject(token);
//        log.info("validation :: {}", validation);
    }
}