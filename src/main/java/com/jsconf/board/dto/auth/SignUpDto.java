package com.jsconf.board.dto.auth;

import lombok.Data;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;

@Data
public class SignUpDto {
    @NotBlank(message = "아이디는 공백일 수 없습니다")
    private String username;
    @NotBlank(message = "비밀번호는 공백일 수 없습니다")
    private String password;
    @NotBlank(message = "이름은 공백일 수 없습니다")
    private String name;
    @NotBlank(message = "이메일은 공백일 수 없습니다")
    @Email(message = "이메일 형식에 맞게 입력해주세요")
    private String email;

    private String role;
}
