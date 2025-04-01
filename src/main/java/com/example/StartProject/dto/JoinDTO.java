//회원가입
package com.example.StartProject.dto;

import com.example.StartProject.entity.UserEntity;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

@Getter
@Setter
public class JoinDTO {

    @NotBlank(message = "사용자 이름은 필수입니다.")
    private String name;
    @NotBlank(message = "비밀번호는 필수입니다.")
    @Size(min = 6, message = "비밀번호는 최소 6자리 이상이어야 합니다.")
    private String password;

    private String confirmPassword;

    @NotBlank(message = "email은 필수입니다.")
    private String email;

    public UserEntity toEntity(BCryptPasswordEncoder encoder) {
        UserEntity user = new UserEntity();
        user.setName(this.name);
        user.setEmail(this.email);
        user.setRole("USER");
        user.setPasswordHash(encoder.encode(this.password));
        return user;
    }

}
