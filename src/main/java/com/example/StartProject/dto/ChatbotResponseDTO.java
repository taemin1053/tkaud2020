package com.example.StartProject.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter

public class ChatbotResponseDTO {
    private Long chatId;
    private String chatbotName;
    private String chatbotDetail;
    private List<String> tags;

    public ChatbotResponseDTO(Long id, String name, String chatbotDetail, List<String> tags) {
        this.chatId = id;
        this.chatbotName = name;
        this.chatbotDetail = chatbotDetail;
        this.tags = tags;
    }
}
