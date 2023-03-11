package com.jsconf.board.web;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@RequiredArgsConstructor
@Controller
public class AuthController {

    @GetMapping("/signin")
    public String signin() {
        return "auth/signin";
    }

    @GetMapping("/signup")
    public String signup() {
        return "auth/signup";
    }
}
