package com.jsconf.board.service;

import com.jsconf.board.domain.User;
import com.jsconf.board.handler.ex.CustomException;
import com.jsconf.board.mapper.UserMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;

@RequiredArgsConstructor
@Service
public class UserService {
    private final UserMapper userMapper;
    private final BCryptPasswordEncoder passwordEncoder;

    public User getUser(int userId){
        User user = userMapper.findById(userId).orElseThrow(() -> {
            throw new CustomException("회원번호와 일치하는 회원이 없습니다.");
        });

        return user;
    }

    @Transactional
    public void userUpdate(User user) {
        if(StringUtils.hasText(user.getPassword())) {
            String rawPassword = user.getPassword();
            String encPassword = passwordEncoder.encode(rawPassword);
            user.setPassword(encPassword);
        }
        userMapper.updateById(user);
    }
}
