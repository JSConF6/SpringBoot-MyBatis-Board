package com.jsconf.board.domain;

import lombok.Data;

@Data
public class Board {
    private int id;
    private int userId;
    private String title;
    private String content;
    private int readCount;
    private String createDate;
    private int fileId;
    private String fileOriginalName;
}
