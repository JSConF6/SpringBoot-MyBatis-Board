package com.jsconf.board.dto.auth;

import com.jsconf.board.domain.user.User;
import lombok.Data;

@Data
public class SignUpDto {
    private String username;
    private String password;
    private String name;
    private String email;
    private String role;
}
