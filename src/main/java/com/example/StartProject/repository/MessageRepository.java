package com.example.StartProject.repository;

import com.example.StartProject.entity.MessageEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface MessageRepository extends JpaRepository<MessageEntity, Long> {

    //채팅방 아이디로 메세지목록 조회(시간순)
    List<MessageEntity> findByChatroomIdOrderByCreateTimeAsc(Long chatroomId);
}
