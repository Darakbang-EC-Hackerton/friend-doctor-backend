package com.youngandhun.moduleapi.auth.util.jwt;


import java.nio.charset.StandardCharsets;
import java.time.Instant;
import java.util.Date;

import javax.crypto.SecretKey;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.MalformedJwtException;
import io.jsonwebtoken.UnsupportedJwtException;
import io.jsonwebtoken.security.Keys;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import com.youngandhun.moduleapi.auth.exception.CustomSecurityException;
import com.youngandhun.moduleapi.auth.exception.SecurityErrorCode;

import jakarta.servlet.http.HttpServletRequest;
import lombok.extern.slf4j.Slf4j;


@Component
@Slf4j
public class JwtProvider {

    private final SecretKey secretKey;
    private final Long accessExpMs;

    public static final String AUTHORIZATION = "Authorization";

    public JwtProvider(
            @Value("${security.jwt.secret}") String secretKey,
            @Value("${security.jwt.token.access-expiration-time}") Long accessExpMs) {

        this.secretKey = Keys.hmacShaKeyFor(secretKey.getBytes(StandardCharsets.UTF_8));
        this.accessExpMs = accessExpMs;
    }

    public String resolveAccessToken(HttpServletRequest request) {
        String authorization = request.getHeader(AUTHORIZATION);

        if (authorization == null || !authorization.startsWith("Bearer ")) {
            return null;
        }

        return authorization.split(" ")[1];
    }

    public String issueJwtAccessToken(Long id, String email, String username) {
        Instant issuedAt = Instant.now();
        Instant expiration = issuedAt.plusMillis(accessExpMs);

        return createToken(id, email, username, issuedAt, expiration);
    }

    private String createToken(Long id, String email, String username, Instant issuedAt, Instant expiration) {
        return Jwts.builder()
                .setSubject(id.toString())
                .claim("email", email)
                .claim("username", username)
                .setIssuedAt(Date.from(issuedAt))
                .setExpiration(Date.from(expiration))
                .signWith(secretKey)
                .compact();
    }

    private Claims getClaims(String token) {
        try {
            return Jwts.parserBuilder()
                    .setSigningKey(secretKey)
                    .build()
                    .parseClaimsJws(token)
                    .getBody();
        } catch (UnsupportedJwtException | MalformedJwtException | IllegalArgumentException e) {
            throw new CustomSecurityException(SecurityErrorCode.INVALID_TOKEN);
        }
    }

    public String getSubject(String token) {
        return getClaims(token).getSubject();
    }

    public Long getId(String token) {
        return Long.parseLong(getSubject(token));
    }

    public String getEmail(String token) {
        return getClaims(token).get("email", String.class);
    }

    public String getAuthority(String token) {
        return getClaims(token).get("auth", String.class);
    }
}
