package com.example.StartProject.service;

import com.example.StartProject.dto.AuthDTO;
import com.example.StartProject.dto.AuthResponseDTO;
import com.example.StartProject.entity.UserEntity;
import com.example.StartProject.repository.UserRepository;
import com.example.StartProject.jwt.JwtUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AuthService {

    private final UserRepository userRepository;
    private final JwtUtil jwtUtil;
    private final BCryptPasswordEncoder passwordEncoder;

    public AuthResponseDTO login(AuthDTO request) {

        // 이메일로 유저 찾기
        UserEntity user = userRepository.findByEmail(request.getEmail());

        if (user == null) {
            throw new RuntimeException("존재하지 않는 유저입니다.");
        }
        // 비밀번호 검증
        if (!passwordEncoder.matches(request.getPassword(), user.getPasswordHash())) {
            throw new RuntimeException("비밀번호가 틀렸습니다.");
        }

        // 토큰 발급
        String token = jwtUtil.generateToken(user.getEmail());

        // 응답 DTO 리턴
        return new AuthResponseDTO(token, user.getUserId(), user.getName());
    }
}