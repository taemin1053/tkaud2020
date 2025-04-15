package com.example.StartProject.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.persistence.Column;
import lombok.Getter;

import java.util.List;

@Getter
public class ChatroomDTO {
    @Schema(description = "채팅방 제목", example = "내 첫 챗봇방")

    private String chatroomName;

    @Schema(description = "사용자 ID", example = "1")
    private Long userId;

    @Schema(description = "선택한 챗봇들의 ID 리스트", example = "[1, 2, 3]")
    private List<Long> chatbotIds;

}
