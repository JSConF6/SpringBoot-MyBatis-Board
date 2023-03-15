package com.jsconf.board.service;

import com.jsconf.board.dto.auth.SignUpDto;
import com.jsconf.board.dto.file.FileDto;
import com.jsconf.board.mapper.FileMapper;
import com.jsconf.board.mapper.UserMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@RequiredArgsConstructor
@Service
public class FileService {

    private final FileMapper fileMapper;


    public FileDto getFile(int fileId) {
        return fileMapper.findById(fileId);
    }
}
