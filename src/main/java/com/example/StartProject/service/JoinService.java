package com.example.StartProject.service;

import com.example.StartProject.dto.JoinDTO;
import com.example.StartProject.entity.UserEntity;
import com.example.StartProject.repository.UserRepository;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@Service

public class JoinService {
    private final UserRepository userRepository;
    private final BCryptPasswordEncoder bCryptPasswordEncoder;

    public JoinService(UserRepository userRepository, BCryptPasswordEncoder bCryptPasswordEncoder) {
        this.userRepository = userRepository;
        this.bCryptPasswordEncoder = bCryptPasswordEncoder;
    }

    public void joinProcess(JoinDTO joinDTO) {
        String name = joinDTO.getName();
        String email = joinDTO.getEmail();
        String rawPassword = joinDTO.getPassword();

        if (userRepository.existsByName(name)) {
            throw new IllegalStateException("이미 사용중인 이름입니다.");
        }

        if (userRepository.findByEmail(email) != null) {
            throw new IllegalStateException("이미 사용중인 이메일입니다.");
        }
        if(!joinDTO.getPassword().equals(joinDTO.getConfirmPassword())){
            throw new IllegalStateException("비밀번호가 일치하지 않습니다.");
        }
        UserEntity data = new UserEntity();
        data.setName(name);
        data.setEmail(email);
        data.setPasswordHash(bCryptPasswordEncoder.encode(rawPassword)); // 비밀번호를 암호화해서 저장
        data.setRole("ROLE_USER");

        userRepository.save(data);
    }
}
