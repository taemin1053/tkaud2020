package com.example.StartProject.jwt;

import io.jsonwebtoken.*;
import io.jsonwebtoken.security.Keys;
import org.springframework.stereotype.Component;

import java.nio.charset.StandardCharsets;
import java.security.Key;
import java.util.Date;

@Component
public class JwtUtil {
    private static final String SECRET_KEY = "tkdaud2020tkdaud2020tkdaud2020tkdaud2020"; // 32자 이상 필수
    private static final long EXPIRATION_TIME = 60 * 60 * 1000; // 1시간

    // SecretKey를 생성하여 반환
    private Key getSigningKey() {
        return Keys.hmacShaKeyFor(SECRET_KEY.getBytes(StandardCharsets.UTF_8));
    }

    // 토큰 생성
    public String generateToken(String email) {
        return Jwts.builder()
                .setSubject(email) // 사용자 이름 설정
                .setIssuedAt(new Date()) // 발급 시간 설정
                .setExpiration(new Date(System.currentTimeMillis() + EXPIRATION_TIME)) // 만료 시간 설정
                .signWith(getSigningKey(), SignatureAlgorithm.HS256) // SecretKey와 알고리즘 설정
                .compact(); // 토큰 생성
    }

    // 토큰에서 사용자 이름 추출
    public String extractUsername(String token) {
        try {
            Claims claims = Jwts.parser()
                    .setSigningKey(getSigningKey()) // SecretKey 설정
                    .build() // JwtParser 생성
                    .parseClaimsJws(token) // 토큰 검증 및 파싱
                    .getBody(); // Claims 반환

            return claims.getSubject(); // 사용자 이름 반환
        } catch (JwtException e) {
            System.out.println("❌ [JwtUtil] JWT 검증 실패: " + e.getMessage());
            return null;
        }
    }
}
