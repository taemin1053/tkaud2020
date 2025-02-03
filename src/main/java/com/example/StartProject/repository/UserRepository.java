package com.example.StartProject.repository;

import com.example.StartProject.entity.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<UserEntity,Integer> {
    Boolean existsByUsername(String username);
}
