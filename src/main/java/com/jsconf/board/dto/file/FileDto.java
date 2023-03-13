package com.jsconf.board.dto.file;

import lombok.Builder;
import lombok.Data;

@Data
public class FileDto {
    private int id;
    private int boardId;
    private String filePath;

    @Builder
    public FileDto(int id, int boardId, String filePath) {
        this.id = id;
        this.boardId = boardId;
        this.filePath = filePath;
    }
}
