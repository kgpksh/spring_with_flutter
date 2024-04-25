package com.codecrafter.spring_with_flutter.base.security.jwt;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import javax.crypto.SecretKey;
import java.util.Date;

@Component
public class JwtProvider {
    @Value("${custom.jwt.secretKey}")
    String secretKey;
    private static final long TOKEN_DURATION  = 1000L * 60 * 60 * 3;
    private static final String USERNAME_KEY = "username";

    public String createJwt(String username) {
        return Jwts.builder()
                .claim(USERNAME_KEY, username)
                .expiration(new Date(System.currentTimeMillis() + TOKEN_DURATION))
                .signWith(SignatureAlgorithm.HS512, secretKey)
                .compact();
    }

    public boolean isTokenExpired(String token) {
        Date expiration = getClaims(token).getExpiration();
        return expiration.before(new Date());
    }

    public String getUserName(String token) {
        if (isTokenExpired(token)) {
            return null;
        }
        return getClaims(token).get(USERNAME_KEY, String.class);
    }

    private Claims getClaims(String token) {
        return Jwts.parser().verifyWith(getSecretKey()).build().parseSignedClaims(token).getPayload();
    }

    private SecretKey getSecretKey() {
        byte[] keyBytes = Decoders.BASE64.decode(secretKey);
        return Keys.hmacShaKeyFor(keyBytes);
    }
}
