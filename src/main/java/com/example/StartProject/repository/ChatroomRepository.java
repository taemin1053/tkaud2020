package com.example.StartProject.repository;

import com.example.StartProject.entity.ChatroomEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface ChatroomRepository extends JpaRepository<ChatroomEntity, Long> {
    List<ChatroomEntity> findAllByUserId(Long userId);
    Optional<ChatroomEntity> findByChatroomName( String chatroomName);
    List<ChatroomEntity> findByChatroomNameContaining(String keyword);

}