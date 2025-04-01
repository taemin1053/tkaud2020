package com.example.StartProject.repository;

import com.example.StartProject.entity.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<UserEntity,Integer> {
    Boolean existsByName(String name);
    Boolean existsByEmail(String email);
    //name을 받아 DB 테이블에서 회원을 조회하는 메소드 작성
    UserEntity findByName(String name);

    UserEntity findByEmail(String email);
}
