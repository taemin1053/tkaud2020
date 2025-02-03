package com.example.StartProject.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "test") //test 테이블 맵핑하기
@Getter
@Setter
public class UserEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)

    private int id;

    private String username;
    private String password;
    private int age;

    private String role;
}
