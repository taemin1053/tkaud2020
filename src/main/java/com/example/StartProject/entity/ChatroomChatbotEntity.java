package com.example.StartProject.entity;


import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
@Table(name = "chatroom_chatbot")
public class ChatroomChatbotEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "chatroom_id")
    private ChatroomEntity chatroom;

    @ManyToOne
    @JoinColumn(name = "chatbot_id")
    private ChatbotEntity chatbot;
}
