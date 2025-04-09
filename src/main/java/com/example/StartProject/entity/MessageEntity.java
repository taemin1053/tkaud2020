package com.example.StartProject.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
@Entity
@Table(name = "chat")
public class MessageEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "chat_id")
    private Long id;

    @Column(name = "chatroom_id", nullable = false)
    private Long chatroomId; //어떤 채팅방 채팅인지 확인을 위해

    @Column(name = "sender_type", nullable = false)
    private String senderType; //누가 보낸 메세지인지 확인을 위해

    @Column(name = "sender_id",nullable = false)
    private Long senderId;
    @Column(nullable = false)
    private String content; //내용

    @Column(name = "created_at")
    private LocalDateTime createTime;

}
