package com.example.StartProject.service;

import com.example.StartProject.dto.ChatroomDTO;
import com.example.StartProject.dto.ChatroomSearch;
import com.example.StartProject.entity.ChatbotEntity;
import com.example.StartProject.entity.ChatroomChatbotEntity;
import com.example.StartProject.entity.ChatroomEntity;
import com.example.StartProject.repository.ChatbotRepository;
import com.example.StartProject.repository.ChatroomChatbotRepository;
import com.example.StartProject.repository.ChatroomRepository;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;
import lombok.RequiredArgsConstructor;
import com.example.StartProject.dto.ChatroomSearch;
import java.util.List;
import java.util.stream.Collectors;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ChatroomService {

    private final ChatbotRepository chatbotRepository;
    private final ChatroomRepository chatroomRepository;
    private final ChatroomChatbotRepository chatroomChatbotRepository;

    //챗룸 생성
    @Transactional
    public void createChatroom(ChatroomDTO chatroomDTO) {
        System.out.println("채팅방 이름 : " + chatroomDTO.getChatroomName());

        ChatroomEntity chatroomEntity = new ChatroomEntity();
        chatroomEntity.setChatroomName(chatroomDTO.getChatroomName());
        chatroomEntity.setUserId(chatroomDTO.getUserId());
        chatroomEntity.setLastMessage("");
        chatroomRepository.save(chatroomEntity);
        for (Long chatbotId : chatroomDTO.getChatbotIds()) {
            ChatbotEntity chatbot = chatbotRepository.findById(chatbotId).orElse(null);
            if (chatbot != null) {
                boolean exists = chatroomChatbotRepository.existsByChatroomAndChatbot(chatroomEntity, chatbot);
                if (!exists) {
                    ChatroomChatbotEntity mapping = new ChatroomChatbotEntity();
                    mapping.setChatroom(chatroomEntity);
                    mapping.setChatbot(chatbot);
                    chatroomChatbotRepository.save(mapping);
                }
            }
        }


    }
    //챗룸 사용자ID로 조회
    public List<ChatroomSearch> getChatroomsByUserId(Long userId) {
        List<ChatroomEntity> entities = chatroomRepository.findAllByUserId(userId);
        return entities.stream()
                .map(chatroom -> new ChatroomSearch(
                        chatroom.getId(),
                        chatroom.getChatroomName(),
                        chatroom.getUserId(),
                        chatroom.getLastMessage(),
                        chatroom.getCreateTime()
                ))
                .collect(Collectors.toList());
    }
    // 채팅방 단일 조회 (챗룸ID)
    public ChatroomSearch getChatroomById(Long chatroomId) {
        ChatroomEntity chatroom = chatroomRepository.findById(chatroomId)
                .orElse(null);

        return new ChatroomSearch(
                chatroom.getId(),
                chatroom.getChatroomName(),
                chatroom.getUserId(),
                chatroom.getLastMessage(),
                chatroom.getCreateTime()
        );
    }
    //채팅방 단일 조회2 (챗룸 이름)
    public ChatroomSearch getChatroomByName(String chatroomName) {
        ChatroomEntity chatroom = chatroomRepository.findByChatroomName(chatroomName)
                .orElse(null);
        if (chatroom == null) return null;

        return new ChatroomSearch(
                chatroom.getId(),
                chatroom.getChatroomName(),
                chatroom.getUserId(),
                chatroom.getLastMessage(),
                chatroom.getCreateTime()
        );

    }
    //키워드를 통해 채팅방 전체 조회
    public List<ChatroomSearch> searchByChatroomNameKeyword(String keyword) {
        List<ChatroomEntity> results = chatroomRepository.findByChatroomNameContaining(keyword);

        return results.stream()
                .map(chatroom -> new ChatroomSearch(
                        chatroom.getId(),
                        chatroom.getChatroomName(),
                        chatroom.getUserId(),
                        chatroom.getLastMessage(),
                        chatroom.getCreateTime()
                ))
                .collect(Collectors.toList());
    }

    @Transactional
    public void updateChatroomName(Long chatroomId, String newName) {
        ChatroomEntity chatroom = chatroomRepository.findById(chatroomId)
                .orElseThrow(() -> new RuntimeException("채팅방이 존재하지 않습니다."));
        chatroom.setChatroomName(newName);
        chatroomRepository.save(chatroom);
    }
    //챗룸 삭제
    @Transactional
    public void deleteChatroom(Long chatroomId) {
        // chatroom_chatbot 매핑 먼저 삭제
        chatroomChatbotRepository.deleteByChatroomId(chatroomId);
        // chatroom삭제
        chatroomRepository.deleteById(chatroomId);
    }
}
