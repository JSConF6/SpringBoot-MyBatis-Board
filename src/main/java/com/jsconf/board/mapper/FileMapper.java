package com.jsconf.board.mapper;

import com.jsconf.board.dto.file.FileDto;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface FileMapper {
    void save(FileDto fileDto);
}
