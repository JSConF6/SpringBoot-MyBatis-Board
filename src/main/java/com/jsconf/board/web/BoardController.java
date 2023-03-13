package com.jsconf.board.web;

import com.jsconf.board.config.auth.PrincipalDetails;
import com.jsconf.board.dto.board.BoardDto;
import com.jsconf.board.dto.board.BoardSaveDto;
import com.jsconf.board.service.BoardService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RequiredArgsConstructor
@RequestMapping("/board")
@Controller
public class BoardController {

    private final BoardService boardService;

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
        return "redirect:/board/" + boardSaveDto.getId() + "/detail";
    }

    @GetMapping("/update")
    public String updateForm() {
        return "board/updateForm";
    }

    @GetMapping("/{boardId}/detail")
    public String detail(@PathVariable int boardId, Model model) {
        BoardDto boardDto = boardService.getBoard(boardId);
        model.addAttribute("boardDto", boardDto);
        return "board/detail";
    }
}
