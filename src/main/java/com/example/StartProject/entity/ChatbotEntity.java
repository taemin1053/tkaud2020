package com.example.StartProject.entity;


import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Entity
@Getter
@Setter
@Table(name= "chatbot")
public class ChatbotEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "chatbot_id")
    private Long id;

    @Column(name = "chatbot_name",nullable = false)
    private String name; //챗봇의 이름


    @Column(name = "chatbot_detail")
    private String chatbotDetail; // 챗봇 설명

    @ManyToMany
    @JoinTable(
            name = "chatbot_tag_mapping",
            joinColumns = @JoinColumn(name = "chatbot_id"),
            inverseJoinColumns = @JoinColumn(name = "tag_id")
    )
    private List<ChatbotTagEntity> tags;
}
