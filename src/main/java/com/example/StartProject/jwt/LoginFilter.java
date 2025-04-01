package com.example.StartProject.jwt;

import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.servlet.FilterChain;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import java.io.IOException;
import java.util.Map;

public class LoginFilter extends UsernamePasswordAuthenticationFilter {

    private final AuthenticationManager authenticationManager;

    public LoginFilter(AuthenticationManager authenticationManager) {
        this.authenticationManager = authenticationManager;
        System.out.println("LoginFilter생성자 호출");
    }

    @Override
    public Authentication attemptAuthentication(HttpServletRequest request, HttpServletResponse response) {
        System.out.println("🚀 [LoginFilter] attemptAuthentication 호출");

        try {
            // JSON 데이터 읽기
            ObjectMapper objectMapper = new ObjectMapper();
            Map<String, String> requestBody = objectMapper.readValue(request.getInputStream(), Map.class);

            String email = requestBody.get("email");
            String password = requestBody.get("password");
            System.out.println("🔐 [LoginFilter] password 값 확인: " + password);
            System.out.println("🔐 [LoginFilter] password 타입 확인: " + (password == null ? "null" : password.getClass().getName()));

            System.out.println("📌 [LoginFilter] 입력된 email: " + email);
            System.out.println("📌 [LoginFilter] 입력된 password: " + password);

            if (email == null || password == null) {
                throw new RuntimeException("❌ [LoginFilter] email 또는 password가 null입니다.");
            }

            // 🔥 Spring Security가 중복 호출하지 않도록 명시적으로 처리
            if (authenticationManager == null) {
                throw new IllegalStateException("❌ [LoginFilter] AuthenticationManager가 null입니다.");
            }

            Authentication authResult = authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(email, password)
            );
            System.out.println("✅ [LoginFilter] 인증 성공: " + authResult.getName());

            return authResult;

        } catch (IOException e) {
            throw new RuntimeException("❌ [LoginFilter] 요청 바디 JSON 파싱 실패", e);
        } catch (AuthenticationException e) {
            System.out.println("❌ [LoginFilter] 인증 실패: " + e.getMessage());
            throw e;
        }
    }


    // 로그인 성공시 실행되는 메소드
    @Override
    protected void successfulAuthentication(HttpServletRequest request, HttpServletResponse response, FilterChain chain, Authentication authentication) {
        System.out.println("🎉 [LoginFilter] 로그인 성공! 사용자: " + authentication.getName());
    }

    // 로그인 실패시 실행하는 메소드
    @Override
    protected void unsuccessfulAuthentication(HttpServletRequest request, HttpServletResponse response, AuthenticationException authenticationException) {
        System.out.println("🚨 [LoginFilter] 로그인 실패! 오류 메시지: " + authenticationException.getMessage());
    }
}
