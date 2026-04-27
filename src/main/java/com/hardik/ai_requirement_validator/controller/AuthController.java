package com.hardik.ai_requirement_validator.controller;

import com.hardik.ai_requirement_validator.common.RequirementApiResponse;
import com.hardik.ai_requirement_validator.dto.LoginRequest;
import com.hardik.ai_requirement_validator.security.JwtUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/auth")
@RequiredArgsConstructor
public class AuthController {

    private final AuthenticationManager authenticationManager;
    private final JwtUtil jwtUtil;

    @PostMapping("/login")
    public RequirementApiResponse<String> login(@RequestBody LoginRequest request) {
        BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();

        System.out.println(
                encoder.matches(
                        "password",
                        "$2a$10$Dow1qBqK5uY6fP9w9O9G3u0l2gWk6L9Xb6nK6Yz2cJ5gQmF9m9k9G"
                )
        );
        BCryptPasswordEncoder encoder1 = new BCryptPasswordEncoder();
        System.out.println(encoder.encode("admin"));
        Authentication authentication =
                authenticationManager.authenticate(
                        new UsernamePasswordAuthenticationToken(
                                request.getUsername(),
                                request.getPassword()
                        )
                );

        String token = jwtUtil.generateToken(authentication.getName());

        return RequirementApiResponse.<String>builder()
                .isSuccess(true)
                .message("Login successful")
                .data(token)
                .build();
    }
}