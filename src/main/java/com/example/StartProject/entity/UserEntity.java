package com.example.StartProject.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.CreationTimestamp;

import java.time.LocalDateTime;

@Entity
@Table(name = "users") //user 테이블 맵핑하기
@Getter
@Setter
public class UserEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)

    private Long userId;

    private String name;
    private String passwordHash;
    private String email;

    @Column(nullable = false)
    private String role ="USER";
    @CreationTimestamp
    @Column(updatable = false)
    private LocalDateTime createdAt;

}
