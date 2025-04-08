package com.example.StartProject.controller;


import com.example.StartProject.service.ChatbotService;
import com.example.StartProject.dto.ChatbotResponseDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/chatbots")
@RequiredArgsConstructor
public class ChatbotController {

    private final ChatbotService chatbotService;

    @GetMapping
    public ResponseEntity <List<ChatbotResponseDTO>> getAllChatbots() {
        return ResponseEntity.ok(chatbotService.getAllChatbots());
    }
}
