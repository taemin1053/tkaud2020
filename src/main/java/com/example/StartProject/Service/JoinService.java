package com.example.StartProject.Service;

import com.example.StartProject.dto.JoinDTO;
import com.example.StartProject.entity.UserEntity;
import com.example.StartProject.repository.UserRepository;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@Service

public class JoinService {
    private final UserRepository userRepository;
    private  final BCryptPasswordEncoder bCryptPasswordEncoder;

    public JoinService(UserRepository userRepository, BCryptPasswordEncoder bCryptPasswordEncoder) {

        this.userRepository = userRepository;
        this.bCryptPasswordEncoder = bCryptPasswordEncoder;
    }

    public void joinProcess(JoinDTO joinDTO){

        String username = joinDTO.getUsername();
        String password = joinDTO.getPassword();
        int age = joinDTO.getAge();

        Boolean isExit = userRepository.existsByUsername(username);

        if (isExit){
            return;
        }

        UserEntity data = new UserEntity();

        data.setUsername(username);
        data.setAge(age);
        data.setPassword(bCryptPasswordEncoder.encode(password)); //비밀번호를 암호화해서 저장
        data.setRole("ROLE_ADMIN");
        userRepository.save(data);
    }
}
