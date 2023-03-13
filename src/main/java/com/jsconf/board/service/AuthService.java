package com.jsconf.board.service;

import com.jsconf.board.mapper.UserMapper;
import com.jsconf.board.dto.auth.SignUpDto;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@RequiredArgsConstructor
@Service
public class AuthService {

    private final UserMapper userMapper;
    private final BCryptPasswordEncoder bCryptPasswordEncoder;

    @Transactional
    public void signup(SignUpDto signUpDto) {
        String rawPassword = signUpDto.getPassword();
        String encPassword = bCryptPasswordEncoder.encode(rawPassword);
        signUpDto.setPassword(encPassword);
        signUpDto.setRole("ROLE_USER");
        userMapper.save(signUpDto);
    }
}
