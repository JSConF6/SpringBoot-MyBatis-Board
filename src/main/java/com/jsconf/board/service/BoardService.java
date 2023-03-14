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
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
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
        try{
            boardMapper.updateBoard(boardUpdateDto);

            if (!boardUpdateDto.getFile().isEmpty()) {
                FileDto fileDto = getFileDto(boardUpdateDto.getFile(), boardUpdateDto.getId());
                if (boardUpdateDto.getFileId() != 0) {
                    fileMapper.updateFile(fileDto);
                } else {
                    fileMapper.save(fileDto);
                }
            }
        } catch(Exception e) {
            throw new CustomException("게시글 수정 중 오류가 발생했습니다.");
        }
    }

    private FileDto getFileDto(MultipartFile file, int id) throws IOException {
        UUID uuid = UUID.randomUUID();
        String fileOriginalName = file.getOriginalFilename();
        String fileName = uuid + "_" + file.getOriginalFilename();

        Path filePath = Paths.get(uploadFolder + fileName);

        Files.write(filePath, file.getBytes());
        return FileDto.builder()
                .boardId(id)
                .fileName(fileName)
                .fileOriginalName(fileOriginalName)
                .filePath(filePath.toString())
                .build();
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
        try{
            boardMapper.save(boardSaveDto);

            if(!boardSaveDto.getFile().isEmpty()) {
                FileDto fileDto = getFileDto(boardSaveDto.getFile(), boardSaveDto.getId());
                fileMapper.save(fileDto);
            }
        } catch(Exception e) {
            throw new CustomException("게시글 저장 중 오류가 발생했습니다.");
        }
    }
}
