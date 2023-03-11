package com.jsconf.board.web;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

@RequiredArgsConstructor
@RequestMapping("/user")
@Controller
public class UserController {

    @GetMapping("/{userId}/update")
    public String updateForm(@PathVariable int userId) {
        return "user/userUpdateForm";
    }
}
