package com.example.loginProject.controller;

import com.example.loginProject.dto.UserForm;
import com.example.loginProject.service.UserService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.web.csrf.CsrfToken;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;


@Slf4j
@Controller
@RequiredArgsConstructor // Class에 선언된 final 변수들을 매개변수로 하는 생성자 자동생성
public class HomeController {
    @Autowired
    private final UserService userService;

    @GetMapping("/")
    public String mainPage() {
        return "success";
    }

    @GetMapping("/signIn")
    public String successPage(Model model, CsrfToken csrfToken) {

        // csrf 추가
        model.addAttribute("_csrf", csrfToken);
        return "home";
    }

    @PostMapping("/regist")
    public String createAccount(UserForm form) {
        userService.registerUser(form);
        return "redirect:/login";
    }

//    @PostMapping("/login")
//    public String login(UserForm form, RedirectAttributes redirectAttributes) {
//        Boolean loginSuccess = userService.login(form);
//        if (Boolean.TRUE.equals(loginSuccess)) //  Boolean.False.equals는 null일때도 False 반환 => null 안전성 보장
//            return "redirect:/main";
//        else {
//            redirectAttributes.addFlashAttribute("errorMessage", "아이디 또는 비밀번호가 틀립니다.");
//            return "redirect:/";
//        }
//    }
}
