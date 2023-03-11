package com.jsconf.board.web;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@RequiredArgsConstructor
@RequestMapping("/board")
@Controller
public class BoardController {

    @GetMapping("/save")
    public String saveForm() {
        return "board/saveForm";
    }

    @GetMapping("/update")
    public String updateForm() {
        return "board/updateForm";
    }

    @GetMapping("/detail")
    public String detail() {
        return "board/detail";
    }
}
