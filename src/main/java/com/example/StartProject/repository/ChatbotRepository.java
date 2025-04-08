package com.example.StartProject.repository;

import com.example.StartProject.entity.ChatbotEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ChatbotRepository extends JpaRepository<ChatbotEntity, Long> {
}
