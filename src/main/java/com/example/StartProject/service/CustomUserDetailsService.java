package com.example.StartProject.service;

import com.example.StartProject.entity.UserEntity;
import com.example.StartProject.repository.UserRepository;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class CustomUserDetailsService implements UserDetailsService {

    private final UserRepository userRepository;

    public CustomUserDetailsService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        System.out.println("🔎 [CustomUserDetailsService] 사용자 검색: " + email);

        //DB에서 조회
        UserEntity userData = userRepository.findByEmail(email);

        if (userData == null) {
            System.out.println("❌ [CustomUserDetailsService] 사용자 없음: " + email);
            throw new UsernameNotFoundException("사용자를 찾을 수 없음 ");
        }

        System.out.println("✅ [CustomUserDetailsService] 사용자 찾음: " + userData.getEmail());
        System.out.println("🔐 [CustomUserDetailsService] 저장된 비밀번호: " + userData.getPasswordHash());

        return org.springframework.security.core.userdetails.User.builder()
            .username(userData.getEmail())
            .password(userData.getPasswordHash())
            .roles(userData.getRole().replace("ROLE_", ""))
            .build();
    }
}
