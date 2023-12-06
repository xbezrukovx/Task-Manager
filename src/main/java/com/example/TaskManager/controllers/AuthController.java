package com.example.TaskManager.controllers;

import com.example.TaskManager.dto.authentication.request.UserAuthenticateDTO;
import com.example.TaskManager.dto.authentication.response.JWTokenDTO;
import com.example.TaskManager.services.DefaultAuthenticationService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/auth")
public class AuthController {
    private final DefaultAuthenticationService authService;

    @PostMapping
    public JWTokenDTO getAuthToken(@RequestBody UserAuthenticateDTO user) {
        return authService.createAuthToken(user);
    }
}
