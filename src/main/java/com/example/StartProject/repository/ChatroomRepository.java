package com.example.StartProject.repository;

import com.example.StartProject.entity.ChatroomEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ChatroomRepository extends JpaRepository<ChatroomEntity, Long> {
    List<ChatroomEntity> findAllByUserId(Long userId);
}