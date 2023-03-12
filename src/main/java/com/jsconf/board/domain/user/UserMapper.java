package com.jsconf.board.domain.user;

import com.jsconf.board.dto.auth.SignUpDto;
import org.apache.ibatis.annotations.Mapper;

import java.util.Optional;

@Mapper
public interface UserMapper {

    Optional<User> findByUsername(String username);

    int save(SignUpDto signUpDto);
}
