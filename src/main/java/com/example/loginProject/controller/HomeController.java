package com.example.loginProject.controller;

import com.example.loginProject.dto.UserForm;
import com.example.loginProject.service.UserService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.csrf.CsrfToken;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;


@Slf4j
@Controller
@RequiredArgsConstructor // Class에 선언된 final 변수들을 매개변수로 하는 생성자 자동생성
public class HomeController {
    private final UserService userService;
    public static final String MESSAGE = "message";

    @GetMapping("/")
    public String mainPage(@AuthenticationPrincipal UserDetails user, Model model, CsrfToken csrfToken) {
        log.info("Login User : " + user.getUsername());
        model.addAttribute("_csrf", csrfToken);
        return "main";
    }

    @GetMapping("/auth")
    public String authPage(HttpSession session, Model model, CsrfToken csrfToken) {
        // csrf 추가
        model.addAttribute("_csrf", csrfToken);

        String message = (String) session.getAttribute(MESSAGE);
        if (message != null) {
            model.addAttribute(MESSAGE, message);
            session.removeAttribute(MESSAGE);
        }
        return "auth";
    }

    @PostMapping("/auth/regist")
    public String createAccount(HttpServletRequest request, UserForm form, Model model, CsrfToken csrfToken) {
        model.addAttribute("_csrf", csrfToken);

        if (Boolean.FALSE.equals(userService.save(form)))
            request.getSession().setAttribute(MESSAGE, "이미 존재하는 이메일 입니다.");
        else request.getSession().setAttribute(MESSAGE, "회원 가입 완료 다시 로그인 해주세요.");

        return "redirect:/auth";
    }

}
