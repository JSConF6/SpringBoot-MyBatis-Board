package com.jsconf.board.web;

import com.jsconf.board.dto.auth.SignInDto;
import com.jsconf.board.dto.auth.SignUpDto;
import com.jsconf.board.service.AuthService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.validation.Valid;

@RequiredArgsConstructor
@Controller
public class AuthController {

    private final AuthService authService;

    @GetMapping("/signin")
    public String signinForm(@RequestParam(value = "error", required = false) String error,
                         @RequestParam(value = "exception", required = false) String exception,
                         Model model) {
        model.addAttribute("error", error);
        model.addAttribute("exception", exception);
        model.addAttribute("signin", new SignInDto());
        return "auth/signin";
    }

    @GetMapping("/signup")
    public String signupForm(Model model) {
        model.addAttribute("signup", new SignUpDto());
        return "auth/signup";
    }

    @PostMapping("/signup")
    public String signup(@Valid @ModelAttribute("signup") SignUpDto signUpDto, BindingResult bindingResult) {
        // 서버에서 유효성검사 실패
        if (bindingResult.hasErrors()){
           return "auth/signup";
        }

        authService.signup(signUpDto);
        return "redirect:/signin";
    }
}
