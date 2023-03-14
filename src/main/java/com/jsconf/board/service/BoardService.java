package com.jsconf.board.service;

import com.jsconf.board.domain.Board;
import com.jsconf.board.dto.board.BoardDto;
import com.jsconf.board.dto.board.BoardSaveDto;
import com.jsconf.board.dto.board.BoardUpdateDto;
import com.jsconf.board.dto.file.FileDto;
import com.jsconf.board.handler.ex.CustomException;
import com.jsconf.board.mapper.BoardMapper;
import com.jsconf.board.mapper.FileMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.UUID;

@RequiredArgsConstructor
@Service
public class BoardService {

    private final BoardMapper boardMapper;
    private final FileMapper fileMapper;

    @Value("${file.path}")
    private String uploadFolder;

    public void boardUpdate(BoardUpdateDto boardUpdateDto) {
        boardMapper.updateBoard(boardUpdateDto);
    }

    public void boardDelete(int boardId) {
        boardMapper.deleteById(boardId);
    }

    public BoardDto getBoard(int boardId) {
        BoardDto boardDto = boardMapper.findById(boardId);
        return boardDto;
    }

    @Transactional
    public void save(BoardSaveDto boardSaveDto) {
        boardMapper.save(boardSaveDto);

        if(!boardSaveDto.getFile().isEmpty()) {
            UUID uuid = UUID.randomUUID();
            String fileOriginalName = boardSaveDto.getFile().getOriginalFilename();
            String fileName = uuid + "_" + boardSaveDto.getFile().getOriginalFilename();

            Path filePath = Paths.get(uploadFolder + fileName);

            try{
                Files.write(filePath, boardSaveDto.getFile().getBytes());
                FileDto fileDto = FileDto.builder()
                        .boardId(boardSaveDto.getId())
                        .fileName(fileName)
                        .fileOriginalName(fileOriginalName)
                        .filePath(filePath.toString())
                        .build();
                fileMapper.save(fileDto);
            } catch(Exception e) {
                throw new CustomException("파일 업로드 중 오류가 발생했습니다.");
            }
        }
    }
}
