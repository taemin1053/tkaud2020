package com.example.StartProject.controller;

import com.example.StartProject.dto.AuthDTO;
import com.example.StartProject.jwt.JwtUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping("/api/auth")
@RequiredArgsConstructor
public class AuthController {

    private final AuthenticationManager authenticationManager;
    private final JwtUtil jwtUtil;

    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody AuthDTO authDTO) {
        try {
            Authentication authentication = authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(authDTO.getEmail(), authDTO.getPassword())
            );
            String token = jwtUtil.generateToken(authDTO.getEmail());
            return ResponseEntity.ok(Map.of("message", "login ok", "token", token));
        } catch (AuthenticationException e) {
            return ResponseEntity.status(401).body("login fail: " + e.getMessage());
        }
    }
}
