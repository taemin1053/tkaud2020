package com.example.StartProject.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class MessageListDTO {
    private Long id;
    private Long chatroomId;
    private String sendertype;
    private String content;
    private String createdAt;
    private Long senderId;
}
