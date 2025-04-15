package com.example.StartProject.controller;

import com.example.StartProject.dto.ChatroomDTO;
import com.example.StartProject.dto.ChatroomSearch;
import com.example.StartProject.dto.ChatroomUpdateDTO;
import com.example.StartProject.service.ChatroomService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

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
                    @ApiResponse(responseCode = "500", description = "서버 오류")
            }
    )
    @PostMapping
    public void createChatroom(@RequestBody ChatroomDTO dto) {
        chatroomService.createChatroom(dto);
    }
    @GetMapping("/user/{userId}")
    @Operation(summary = "사용자의 채팅방 목록 전체 조회", description = "userId를 기반으로 채팅방 목록을 반환합니다.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "조회 성공"),
            @ApiResponse(responseCode = "404", description = "사용자 없음"),
            @ApiResponse(responseCode = "500", description = "서버 오류")
    })
    public ResponseEntity<List<ChatroomSearch>> getChatroomsByUser(@PathVariable Long userId) {
        List<ChatroomSearch> chatrooms = chatroomService.getChatroomsByUserId(userId);
        return ResponseEntity.ok(chatrooms);
    }
    @GetMapping("/{chatroomId}")
    @Operation( description = "채팅방 ID를 통해 단일 채팅방 정보를 반환합니다.(상세정보 보기)")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "조회 성공"),
            @ApiResponse(responseCode = "404", description = "채팅방 없음"),
            @ApiResponse(responseCode = "500", description = "서버 오류")
    })
    public ResponseEntity<ChatroomSearch> getChatroomById(@PathVariable Long chatroomId) {
        ChatroomSearch chatroom = chatroomService.getChatroomById(chatroomId);
        return ResponseEntity.ok(chatroom);
    }
    @GetMapping("/name/{chatroomName}")
    @Operation(description = "chatroomName을 기반으로 채팅방을 조회합니다.(보조역할)")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "조회 성공"),
            @ApiResponse(responseCode = "404", description = "채팅방 없음"),
            @ApiResponse(responseCode = "500", description = "서버 오류")
    })
    public ResponseEntity<ChatroomSearch> getChatroomByName(@PathVariable String chatroomName) {
        ChatroomSearch chatroom = chatroomService.getChatroomByName(chatroomName);
        if (chatroom == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(chatroom);
    }
    @GetMapping("/search")
    @Operation(summary = "채팅방 이름으로 검색기능", description = "채팅방 이름이 들어간 채팅방 목록을 검색하는 API")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "조회 성공"),
            @ApiResponse(responseCode = "500", description = "서버 오류")
    })
    public ResponseEntity<List<ChatroomSearch>> searchChatroomsByKeyword(@RequestParam String keyword) {
        List<ChatroomSearch> chatrooms = chatroomService.searchByChatroomNameKeyword(keyword);
        return ResponseEntity.ok(chatrooms);
    }
    @PutMapping("/{chatroomId}")
    public ResponseEntity<?> updateChatroom(@PathVariable Long chatroomId, @RequestBody ChatroomDTO dto) {
        chatroomService.updateChatroomName(chatroomId, dto.getChatroomName());
        return ResponseEntity.ok("chartroom updated");
    }
    @DeleteMapping("/{chatroomId}")
    public ResponseEntity<?> deleteChatroom(@PathVariable Long chatroomId) {
        chatroomService.deleteChatroom(chatroomId);
        return ResponseEntity.ok("chatroom deleted");
    }
}