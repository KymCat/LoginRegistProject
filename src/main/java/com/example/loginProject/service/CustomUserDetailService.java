package com.example.loginProject.service;

import com.example.loginProject.entity.User;
import com.example.loginProject.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Slf4j
@Service
@RequiredArgsConstructor
public class CustomUserDetailService implements UserDetailsService {
    private final UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        User account = userRepository.findByEmail(email).orElse(null);
        if (account == null) { // 예외처리
            log.info("null");
            throw new UsernameNotFoundException(email);
        }

        log.info("=================================================================");
        log.info("사용자 입력 이메일 : " + email);
        log.info("데이터 베이스 정보 : " + account.getEmail());
        log.info("=================================================================");


        return org.springframework.security.core.userdetails.User.builder()
                .username(account.getEmail())
                .password(account.getPassword())
                .roles("USER")
                .build();
    }
}
