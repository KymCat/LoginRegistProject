package com.example.loginProject.controller;

import com.example.loginProject.dto.UserForm;
import com.example.loginProject.entity.User;
import com.example.loginProject.service.UserService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

@Slf4j
@Controller
@RequiredArgsConstructor //
public class HomeController {
    @Autowired
    private final UserService userService;

    @GetMapping("/")
    public String mainPage() {
        return "home";
    }

    @GetMapping("/main")
    public String successPage() {
        return "success";
    }

    @GetMapping("/fail")
    public String failPage() {
        return "fail";
    }

    @PostMapping("/regist")
    public String createAccount(UserForm form) {
        userService.registerUser(form);
        return "redirect:/";
    }

    @PostMapping("/login")
    public String login(UserForm form) {
        Boolean loginSuccess = userService.login(form);
        return Boolean.TRUE.equals(loginSuccess)? "redirect:/main" : "redirect:/fail";
        //  Boolean.TRUE.equals는 null일때도 false 반환 => null 안전성 보장
    }
}
