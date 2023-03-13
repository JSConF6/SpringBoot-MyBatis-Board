package com.jsconf.board.dto.board;

import lombok.Data;

@Data
public class BoardDto {
    private int id;
    private int userId;
    private String title;
    private String content;
    private int readCount;
    private String createDate;
    private int fileId;
    private String fileOriginalName;
    private boolean boardOwnerState;
}
