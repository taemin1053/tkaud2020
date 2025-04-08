package com.example.StartProject.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
@AllArgsConstructor
public class ChatroomSearch {
    private Long id;
    private String chatroomName;
    private Long userId;
    private String lastMessage;
    private LocalDateTime lastMessageTime;
}
