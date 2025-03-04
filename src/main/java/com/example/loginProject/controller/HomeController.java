package com.example.loginProject.controller;

import com.example.loginProject.dto.UserForm;
import com.example.loginProject.entity.User;
import com.example.loginProject.repository.UserRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

@Slf4j
@Controller
public class HomeController {
    @Autowired
    private UserRepository userRepository;

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
        log.info("Sign in Form : " + form.toString());

        User user = form.toEntity();
        User saved = userRepository.save(user);
        log.info("saved : " + saved);

        return "redirect:/";
    }

    @PostMapping("/login")
    public String login(UserForm form) {
        log.info("login form : " + form.toString());
        User userEntity = userRepository.findByEmail(form.getEmail()).orElse(null);
        if (userEntity != null) {
            if (userEntity.getPassword().equals(form.getPassword())) {
                return "redirect:/main";
            }
        }

        return "redirect:/fail";
    }
}
