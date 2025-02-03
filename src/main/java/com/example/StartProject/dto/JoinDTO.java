//회원가입
package com.example.StartProject.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class JoinDTO {

    @NotBlank(message = "사용자 이름은 필수입니다.")
    private String username;
    @NotBlank(message = "비밀번호는 필수입니다.")
    @Size(min = 6, message = "비밀번호는 최소 6자리 이상이어야 합니다.")
    private String password;
    private int age;
}
