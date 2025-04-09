package com.example.StartProject.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class MessageSendDTO {

    private Long chatroomId; //채팅방 아이디 -> 메세지가 속한 채팅방을 식별할려고,,
    private String senderType; //user인지 bot인지
    private String content;
    private Long senderId;


}
