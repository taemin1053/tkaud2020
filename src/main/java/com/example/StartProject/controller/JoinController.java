package com.example.StartProject.controller;

import com.example.StartProject.service.JoinService;
import com.example.StartProject.dto.JoinDTO;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/auth")
public class JoinController {
    private final JoinService joinService;

    public JoinController(JoinService joinService){
        this.joinService = joinService;
    }

    @PostMapping("/join")
    public ResponseEntity<String> joinProcess(@RequestBody @Valid JoinDTO joinDTO){
        try {
            joinService.joinProcess(joinDTO);
            return ResponseEntity.ok("JOIN SUCCESS.");
        } catch (IllegalStateException e) {
            return ResponseEntity.status(HttpStatus.CONFLICT).body(e.getMessage());
        }
    }

    @GetMapping("/join")
    public String joinTest(){
        return "GET request successful";
    }
}
