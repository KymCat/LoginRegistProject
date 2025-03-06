package com.example.loginProject.service;

import com.example.loginProject.dto.UserForm;
import com.example.loginProject.entity.User;
import com.example.loginProject.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Slf4j
@Service
@RequiredArgsConstructor
public class UserService {
    private final UserRepository userRepository;

    public User registerUser(UserForm form) {
        log.info("Sign in Form : " + form.toString());

        User userEntity = form.toEntity();
        User saved = userRepository.save(userEntity);
        log.info("saved user : " + saved);

        return saved;
    }

    public Boolean login(UserForm form) {
        log.info("login form : " + form.toString());

        return userRepository.findByEmail(form.getEmail())
                .map(user-> user.getPassword().equals(form.getPassword()))
                .orElse(null);
    }
}
