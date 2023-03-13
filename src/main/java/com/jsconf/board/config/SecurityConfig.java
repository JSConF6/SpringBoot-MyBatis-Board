package com.jsconf.board.config;

import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@RequiredArgsConstructor
@Configuration
@EnableWebSecurity // 스프링 시큐리티 필터가 스프링 필터체인에 등록이 된다.
public class SecurityConfig {

    private final LoginFailureHandler loginFailureHandler; // 로그인 실패 핸들러 의존성 주입

    @Bean
    public BCryptPasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public SecurityFilterChain filter(HttpSecurity http) throws Exception {
        http.csrf().disable(); // CSRF 비활성화
        http.authorizeRequests()
                .antMatchers("/user/**").authenticated()
                .antMatchers("/board/save", "/board/update").access("hasRole('ROLE_USER')")
                .anyRequest().permitAll() // 그외 요청은 권한 없이 접근 가능
                .and()
                .formLogin()
                .loginPage("/signin") // 로그인 페이지는 /signin으로 설정
                .loginProcessingUrl("/login") // 로그인 로직은 /login 호출
                .failureHandler(loginFailureHandler) // 로그인 실패시 타는 핸들러 설정
                .defaultSuccessUrl("/"); // 로그인에 성공하면 /로 이동

        return http.build();
    }
}
