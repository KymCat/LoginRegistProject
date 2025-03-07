package com.example.loginProject.config;

import com.example.loginProject.handler.CustomAuthenticationFailureHandler;
import com.example.loginProject.handler.CustomLogoutSuccessHandler;
import com.example.loginProject.service.CustomUserDetailService;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;


@Configuration
@EnableWebSecurity
@RequiredArgsConstructor
public class SpringSecurity {
    private final CustomUserDetailService customUserDetailService;
    private final CustomLogoutSuccessHandler customLogoutSuccessHandler;
    private final CustomAuthenticationFailureHandler customAuthenticationFailureHandler;

    @Bean
    public BCryptPasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
                .userDetailsService(customUserDetailService) // 커스텀 userDetailService 사용
                .authorizeHttpRequests((requests) -> requests
                        .requestMatchers( "/css/**", "/js/**", "/auth/regist").permitAll() // 해당 URL은 인증없이 접근가능
                        .anyRequest().authenticated() // 그 외의 요청은 인증된 사용자만 접근 가능
                )
                .formLogin((form) -> form
                        .loginPage("/auth") // Login 페이지
                        .loginProcessingUrl("/auth/login") // POST ACTION URL
                        .usernameParameter("email") // 사용자 이름 파라미터 name 속성
                        .passwordParameter("password") // 비밀번호 파라미터 name 속성
                        .defaultSuccessUrl("/", true) // 로그인 성공시
                        .failureHandler(customAuthenticationFailureHandler)
                        .permitAll() // 로그인 페이지는 모두 허용
                )
                .logout((logout) -> logout
                        .logoutUrl("/auth/logout")
                        .logoutSuccessHandler(customLogoutSuccessHandler)
                        .invalidateHttpSession(true) // 세션 무효화
                        .clearAuthentication(true) // 인증 정보 삭제
                        .permitAll()
                );

        return http.build();
    }


}
