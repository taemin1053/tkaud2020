package com.example.StartProject.controller;

import com.example.StartProject.dto.MessageSendDTO;
import com.example.StartProject.dto.MessageListDTO;
import com.example.StartProject.service.MessageService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/message")
@RequiredArgsConstructor
public class MessageController {

    private final MessageService messageService;

    // 메시지 전송 API (POST 요청)
    @PostMapping
    @Operation(summary = "메시지 전송", description = "메시지 전송 요청을 받아 데이터베이스에 저장하고, 해당 채팅방의 마지막 메시지를 업데이트합니다.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "메시지 저장 성공"),
            @ApiResponse(responseCode = "500", description = "서버 오류")
    })
    public ResponseEntity<Void> sendMessage(@RequestBody MessageSendDTO messageSendDTO) {
        messageService.saveMessage(messageSendDTO);
        return ResponseEntity.ok().build();
    }

    // 채팅방 메시지 조회 API (GET 요청)
    @GetMapping("/chatroom/{chatroomId}")
    @Operation(summary = "채팅방 메시지 조회", description = "특정 채팅방의 모든 메시지를 시간 순(오름차순)으로 조회합니다.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "조회 성공"),
            @ApiResponse(responseCode = "404", description = "메시지 없음"),
            @ApiResponse(responseCode = "500", description = "서버 오류")
    })
    public ResponseEntity<List<MessageListDTO>> getMessagesByChatroomId(@PathVariable Long chatroomId) {
        List<MessageListDTO> messages = messageService.getMessageByChatroomId(chatroomId);
        return ResponseEntity.ok(messages);
    }
}