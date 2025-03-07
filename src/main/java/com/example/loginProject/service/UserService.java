package com.example.loginProject.service;

import com.example.loginProject.dto.UserForm;
import com.example.loginProject.entity.User;
import com.example.loginProject.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@Slf4j
@Service
@RequiredArgsConstructor
public class UserService {
    private final UserRepository userRepository;
    private final BCryptPasswordEncoder bCryptPasswordEncoder;

    public Boolean save(UserForm form) {
        log.info("Form Data : " + form.toString());

        User userEntity = form.toEntity();
        if (userRepository.findByEmail(userEntity.getEmail()).orElse(null) != null)
            return false;

        userEntity.setPassword(bCryptPasswordEncoder.encode(userEntity.getPassword()));
        User saved = userRepository.save(userEntity);
        log.info("Saved User : " + saved);

        return true;
    }

}
