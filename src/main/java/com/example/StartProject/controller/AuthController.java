package com.example.StartProject.controller;

import com.example.StartProject.dto.AuthDTO;
import com.example.StartProject.entity.UserEntity;
import com.example.StartProject.jwt.JwtUtil;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.web.bind.annotation.*;
import com.example.StartProject.repository.UserRepository;
import com.example.StartProject.dto.AuthResponseDTO;
import java.util.Map;

@RestController
@RequestMapping("/api/auth")
@RequiredArgsConstructor
public class AuthController {

    private final AuthenticationManager authenticationManager;
    private final JwtUtil jwtUtil;
    private final UserRepository userRepository;
    @Operation(
            summary = "로그인",
            description = "이메일과 비밀번호를 통해 로그인하고, JWT 토큰과 사용자 정보를 응답합니다.",
            responses = {
                    @ApiResponse(responseCode = "200", description = "로그인 성공", content = @Content(
                            mediaType = "application/json",
                            schema = @Schema(implementation = AuthResponseDTO.class)
                    )),
                    @ApiResponse(responseCode = "401", description = "로그인 실패 (아이디 또는 비밀번호 불일치)"),
                    @ApiResponse(responseCode = "500", description = "서버 오류")
            }
    )    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody AuthDTO authDTO) {
        try {
            // 1. Spring Security 로 이메일 & 비번 검증
            Authentication authentication = authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(authDTO.getEmail(), authDTO.getPassword())
            );

            // 2. DB에서 userId, name 조회
            UserEntity user = userRepository.findByEmail(authDTO.getEmail());

            if (user == null) {
                throw new RuntimeException("존재하지 않는 유저입니다.");
            }
            // 3. 토큰 발급
            String token = jwtUtil.generateToken(authDTO.getEmail());

            // 4. 최종 응답 리턴 (token, userId, name)
            return ResponseEntity.ok(new AuthResponseDTO(token, user.getUserId(), user.getName()));

        } catch (AuthenticationException e) {
            return ResponseEntity.status(401).body("login fail: " + e.getMessage());
        }
    }
}
