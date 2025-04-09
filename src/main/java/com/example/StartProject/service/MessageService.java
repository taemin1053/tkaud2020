package com.example.StartProject.service;

import com.example.StartProject.dto.MessageListDTO;
import com.example.StartProject.dto.MessageSendDTO;
import com.example.StartProject.entity.MessageEntity;
import com.example.StartProject.repository.ChatroomRepository;
import com.example.StartProject.repository.MessageRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class MessageService {

    private final MessageRepository messageRepository;
    private final ChatroomRepository chatroomRepository;

    //메세지 저장후 채팅방 테이블 last_message에 저장
    public void saveMessage(MessageSendDTO messageSendDTO) {
    MessageEntity message = new MessageEntity();
    message.setChatroomId(messageSendDTO.getChatroomId());
    message.setSenderId(messageSendDTO.getSenderId());
    message.setSenderType(messageSendDTO.getSenderType());
    message.setContent(messageSendDTO.getContent());
    message.setCreateTime(LocalDateTime.now());

    messageRepository.save(message);

    //Last_message 업데이트
    chatroomRepository.findById(messageSendDTO.getChatroomId()).ifPresent(chatroom -> {
        chatroom.setLastMessage(messageSendDTO.getContent());
        chatroomRepository.save(chatroom);//변경사항 저장
    });
    }
    //채팅방 ID를 활용해 메세지 전체 조회
    public List<MessageListDTO> getMessageByChatroomId(Long chatroomId){
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");

        return messageRepository.findByChatroomIdOrderByCreateTimeAsc(chatroomId)
                .stream()
                .map(message -> {
                    MessageListDTO messageListDTO = new MessageListDTO();
                    messageListDTO.setId(message.getId());
                    messageListDTO.setChatroomId(message.getChatroomId());
                    messageListDTO.setSenderId(message.getSenderId());
                    messageListDTO.setSendertype(message.getSenderType());
                    messageListDTO.setContent(message.getContent());
                    message.setCreateTime(LocalDateTime.now());
                    return messageListDTO;
                })
                .collect(Collectors.toList());
    }
}

