package com.example.StartProject.repository;

import com.example.StartProject.dto.ChatbotResponseDTO;
import com.example.StartProject.entity.ChatbotEntity;
import com.example.StartProject.entity.ChatroomChatbotEntity;
import com.example.StartProject.entity.ChatroomEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ChatroomChatbotRepository extends JpaRepository<ChatroomChatbotEntity, Long> {
    List<ChatroomChatbotEntity> findAllByChatroomId(Long chatroomId);
    boolean existsByChatroomAndChatbot(ChatroomEntity chatroom, ChatbotEntity chatbot);
    //특정 채팅방에 연결된 챗봇들 조회

}
