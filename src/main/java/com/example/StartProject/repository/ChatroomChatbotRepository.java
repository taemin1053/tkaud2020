package com.example.StartProject.repository;

import com.example.StartProject.entity.ChatbotEntity;
import com.example.StartProject.entity.ChatroomChatbotEntity;
import com.example.StartProject.entity.ChatroomEntity;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ChatroomChatbotRepository extends JpaRepository<ChatroomChatbotEntity, Long> {
    List<ChatroomChatbotEntity> findAllByChatroomId(Long chatroomId);
    boolean existsByChatroomAndChatbot(ChatroomEntity chatroom, ChatbotEntity chatbot);
    //챗룸 아이디를 기준으로 매핑, 데이터 삭제
    @Transactional //트랜잭션을 처리함
    @Modifying //삭제 업데이트 쿼리 사용시 필수로 사용함
    @Query("DELETE FROM ChatroomChatbotEntity c WHERE c.chatroom.id = :chatroomId")
    void deleteByChatroomId(@Param("chatroomId") Long chatroomId);
}
