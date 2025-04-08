package com.example.StartProject.controller;

import com.example.StartProject.dto.ChatroomDTO;
import com.example.StartProject.service.ChatroomService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/chatrooms")
@Tag(name = "Chatroom", description = "채팅방 관련 API")
@RequiredArgsConstructor
public class ChatroomController {

    private final ChatroomService chatroomService;
    @Operation(
            summary = "채팅방 생성",
            description = "유저 ID와 선택된 챗봇 ID 리스트을 통해 채팅방을 생성.",
            responses = {
                    @ApiResponse(responseCode = "200", description = "채팅방 생성 성공"),
                    @ApiResponse(responseCode = "400", description = "잘못된 요청", content = @Content),
                    @ApiResponse(responseCode = "500", description = "서버 오류", content = @Content)
            }
    )
    @PostMapping
    public void createChatroom(@RequestBody ChatroomDTO dto) {
        chatroomService.createChatroom(dto);
    }
}