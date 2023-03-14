package com.jsconf.board.dto.board;

import lombok.Data;
import org.springframework.web.multipart.MultipartFile;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

@Data
public class BoardUpdateDto {
    private int id;

    @Size(min =  5, max = 30, message = "제목은 5에서 30 사이여야 합니다")
    @NotBlank(message = "제목을 입력해주세요")
    private String title;

    @NotBlank(message = "내용을 입력해주세요")
    private String content;

    private MultipartFile file;
}
