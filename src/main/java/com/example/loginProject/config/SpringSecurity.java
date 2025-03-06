package com.example.loginProject.config;

import com.example.loginProject.service.CustomUserDetailService;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.savedrequest.HttpSessionRequestCache;


@Configuration
@EnableWebSecurity
@RequiredArgsConstructor
public class SpringSecurity {
    private final CustomUserDetailService customUserDetailService;

    @Bean
    public BCryptPasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
                .userDetailsService(customUserDetailService) // 커스텀 userDetailService 사용
                .authorizeHttpRequests((requests) -> requests
                        .requestMatchers( "/css/**", "/js/**").permitAll() // 해당 URL은 인증없이 접근가능
                        .anyRequest().authenticated() // 그 외의 요청은 인증된 사용자만 접근 가능
                )
                .formLogin((form) -> form
                        .loginPage("/auth") // Login 페이지
                        .loginProcessingUrl("/auth/login") // POST ACTION URL
                        .usernameParameter("email") // 사용자 이름 파라미터 name 속성
                        .passwordParameter("password") // 비밀번호 파라미터 name 속성
                        .defaultSuccessUrl("/", true)
                        .permitAll() // 로그인 페이지는 모두 허용
                );

        return http.build();
    }


}
