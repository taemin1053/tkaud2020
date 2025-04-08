package com.example.StartProject.service;

import com.example.StartProject.dto.ChatbotResponseDTO;
import com.example.StartProject.repository.ChatbotRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ChatbotService {

    private final ChatbotRepository chatbotRepository;

    public List<ChatbotResponseDTO> getAllChatbots() {
        return chatbotRepository.findAll().stream()
                .map(bot-> new ChatbotResponseDTO(
                        bot.getId(),
                        bot.getName(),
                        bot.getChatbotDetail(),
                        bot.getTags().stream()
                                .map(tag -> tag.getContent())
                                .collect(Collectors.toList())
                ))
                .collect(Collectors.toList());
    }
}
