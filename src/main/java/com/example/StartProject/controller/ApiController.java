package com.example.StartProject.controller;

import com.example.StartProject.Service.JoinService;
import com.example.StartProject.dto.JoinDTO;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api")
public class ApiController {
    private final JoinService joinService;

    public ApiController(JoinService joinService){
        this.joinService = joinService;
    }

    @PostMapping ( "/user/join")
    public String joinProcess(@RequestBody JoinDTO joinDTO){
        joinService.joinProcess(joinDTO);
        return "회원가입 성공";
    }
}
