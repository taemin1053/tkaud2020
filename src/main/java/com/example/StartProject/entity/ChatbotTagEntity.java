package com.example.StartProject.entity;


import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Entity
@Getter
@Setter
@Table(name = "chatbot_tag")
public class ChatbotTagEntity {
    @Id
    private Long id;
    @Column(name = "content")
    private String content;

    @ManyToMany(mappedBy = "tags")
    private List<ChatbotEntity> chatbots;
}
