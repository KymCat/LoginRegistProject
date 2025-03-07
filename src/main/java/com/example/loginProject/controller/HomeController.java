package com.example.loginProject.controller;

import com.example.loginProject.dto.UserForm;
import com.example.loginProject.service.UserService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.csrf.CsrfToken;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;


@Slf4j
@Controller
@RequiredArgsConstructor // Class에 선언된 final 변수들을 매개변수로 하는 생성자 자동생성
public class HomeController {
    private final UserService userService;

    @GetMapping("/")
    public String mainPage(@AuthenticationPrincipal UserDetails user, Model model, CsrfToken csrfToken) {
        log.info("Login User : " + user.getUsername());
        model.addAttribute("_csrf", csrfToken);
        return "main";
    }

    @GetMapping("/auth")
    public String successPage(@RequestParam(required = false) String error,Model model, CsrfToken csrfToken) {
        // csrf 추가
        model.addAttribute("_csrf", csrfToken);

        if (error != null)
            model.addAttribute("loginError", "아이디 또는 비밀번호가 잘못되었습니다.");
        return "auth";
    }

    @PostMapping("/regist")
    public String createAccount(UserForm form) {
        userService.registerUser(form);
        return "redirect:/auth";
    }

}
