package com.jsconf.board.dto.file;

import lombok.Builder;
import lombok.Data;

@Data
public class FileDto {
    private int id;
    private int boardId;
    private String fileName;
    private String fileOriginalName;
    private String filePath;

    @Builder
    public FileDto(int id, int boardId, String fileName, String fileOriginalName, String filePath) {
        this.id = id;
        this.boardId = boardId;
        this.fileName = fileName;
        this.fileOriginalName = fileOriginalName;
        this.filePath = filePath;
    }
}
