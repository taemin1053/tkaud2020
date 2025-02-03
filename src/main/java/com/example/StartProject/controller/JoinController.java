package com.example.StartProject.controller;

import com.example.StartProject.Service.JoinService;
import com.example.StartProject.dto.JoinDTO;
import jakarta.validation.Valid;
import org.springframework.stereotype.Controller;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api")

public class JoinController {
    private final JoinService joinService;

    public JoinController(JoinService joinService){
        this.joinService = joinService;
    }

    @PostMapping("/join")
    public String joinProcess(@RequestBody @Valid JoinDTO joinDTO){

        System.out.println("Username: " + joinDTO.getUsername());
        joinService.joinProcess(joinDTO);

        return "ok";
    }
    @GetMapping("/join")
    public String joinTest(){
        return "GET request successful";
    }
}
