package com.jsconf.board.web;

import com.jsconf.board.config.auth.PrincipalDetails;
import com.jsconf.board.dto.board.BoardDto;
import com.jsconf.board.dto.board.BoardSaveDto;
import com.jsconf.board.dto.board.BoardUpdateDto;
import com.jsconf.board.dto.file.FileDto;
import com.jsconf.board.handler.ex.CustomException;
import com.jsconf.board.service.BoardService;
import com.jsconf.board.service.FileService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.FileCopyUtils;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;
import java.io.File;
import java.io.FileInputStream;
import java.io.OutputStream;
import java.net.URLEncoder;

@RequiredArgsConstructor
@RequestMapping("/board")
@Controller
public class BoardController {

    private final BoardService boardService;
    private final FileService fileService;

    @Value("${file.path}")
    private String path;

    @GetMapping("/save")
    public String saveForm(Model model) {
        model.addAttribute("save", new BoardSaveDto());
        return "board/saveForm";
    }

    @PostMapping("/save")
    public String save(@Valid @ModelAttribute("save") BoardSaveDto boardSaveDto,
                       BindingResult bindingResult,
                       @AuthenticationPrincipal PrincipalDetails principalDetails) {
        if (bindingResult.hasErrors()) {
            return "board/saveForm";
        }
        boardSaveDto.setUserId(principalDetails.getUser().getId());
        boardService.save(boardSaveDto);
        return "redirect:/board/detail?id=" + boardSaveDto.getId();
    }

    @GetMapping("/update")
    public String updateForm(@RequestParam(value = "id") int id, Model model) {
        BoardDto boardDto = boardService.getBoard(id);
        model.addAttribute("boardDto", boardDto);
        return "board/updateForm";
    }

    @PostMapping("/update")
    public String save(@Valid @ModelAttribute("boardDto") BoardUpdateDto boardUpdateDto,
                       BindingResult bindingResult) {
        System.out.println(boardUpdateDto);
        if (bindingResult.hasErrors()) {
            return "board/updateForm";
        }
        boardService.boardUpdate(boardUpdateDto);
        return "redirect:/board/detail?id=" + boardUpdateDto.getId();
    }

    @GetMapping("/detail")
    public String detail(@RequestParam(value = "id") int id, Model model) {
        BoardDto boardDto = boardService.getBoard(id);
        model.addAttribute("boardDto", boardDto);
        return "board/detail";
    }

    @GetMapping("/delete")
    public String delete(@RequestParam(value = "id") int id, Model model) {
        boardService.boardDelete(id);
        return "redirect:/";
    }

    @GetMapping("/{fileId}/download")
    public void download(@PathVariable int fileId, HttpServletResponse response) {
        try{
            FileDto fileDto = fileService.getFile(fileId);

            String fileOriginalName = URLEncoder.encode(fileDto.getFileOriginalName(), "UTF-8");

            File file = new File(fileDto.getFilePath());

            response.setContentType("application/download");
            response.setContentLength((int)file.length());
            response.setHeader("Content-disposition", "attachment;filename=\"" + fileOriginalName + "\"");

            OutputStream out = response.getOutputStream();

            FileInputStream fis = new FileInputStream(file);

            int read = 0;
            byte[] buffer = new byte[1024];
            while ((read = fis.read(buffer)) != -1) {
                out.write(buffer, 0, read);
            }
            fis.close();
            out.close();
        } catch (Exception e) {
            throw new CustomException("파일 다운로드 중 오류 발생");
        }
    }
}
