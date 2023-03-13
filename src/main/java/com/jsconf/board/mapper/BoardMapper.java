package com.jsconf.board.mapper;

import com.jsconf.board.domain.Board;
import com.jsconf.board.dto.board.BoardDto;
import com.jsconf.board.dto.board.BoardSaveDto;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface BoardMapper {

    void save(BoardSaveDto boardSaveDto);

    BoardDto findById(int boardId);
}
