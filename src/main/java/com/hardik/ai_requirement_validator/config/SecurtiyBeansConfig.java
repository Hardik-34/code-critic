package com.hardik.ai_requirement_validator.config;

import com.hardik.ai_requirement_validator.security.CustomUserDetailService;
import com.hardik.ai_requirement_validator.security.JwtAuthenticationFilter;
import com.hardik.ai_requirement_validator.security.JwtUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

@Configuration
@RequiredArgsConstructor
public class SecurtiyBeansConfig {
    private final JwtUtil jwtUtil;
    private final CustomUserDetailService customUserDetailService;
    @Bean
    public PasswordEncoder passwordEncoder(){
        return new BCryptPasswordEncoder();
    }
    @Bean
    public JwtAuthenticationFilter jwtAuthenticationFilter() {

        return new JwtAuthenticationFilter(jwtUtil, customUserDetailService);
    }
}
