package com.jsconf.board.web;

import com.jsconf.board.domain.User;
import com.jsconf.board.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@RequiredArgsConstructor
@RequestMapping("/user")
@Controller
public class UserController {
    private final UserService userService;

    @GetMapping("/{userId}/update")
    public String updateForm(@PathVariable int userId, Model model) {
        User user = userService.getUser(userId);
        model.addAttribute("user", user);
        return "user/userUpdateForm";
    }

    @PostMapping("/update")
    public String update(User user) {
        userService.userUpdate(user);
        return "redirect:/user/" + user.getId() + "/update";
    }
}
