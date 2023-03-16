package com.jsconf.board.mapper;

import com.jsconf.board.domain.User;
import com.jsconf.board.dto.auth.SignUpDto;
import org.apache.ibatis.annotations.Mapper;

import java.util.Optional;

@Mapper
public interface UserMapper {

    Optional<User> findByUsername(String username);

    Optional<User> findById(int userId);

    void save(SignUpDto signUpDto);

    void updateById(User user);
}
