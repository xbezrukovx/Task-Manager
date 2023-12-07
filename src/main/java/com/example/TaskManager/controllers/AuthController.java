package com.example.TaskManager.controllers;

import com.example.TaskManager.dto.authentication.request.UserAuthenticateDTO;
import com.example.TaskManager.dto.authentication.request.UserCreateDTO;
import com.example.TaskManager.dto.authentication.response.JWTokenDTO;
import com.example.TaskManager.services.UserService;
import com.example.TaskManager.services.implementation.DefaultAuthenticationService;
import io.swagger.v3.oas.annotations.Operation;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/auth")
public class AuthController {
    private final DefaultAuthenticationService authService;
    private final UserService userService;

    @Operation(
            summary = "Authenticates by email and password",
            description = "Authenticating provided user credentials and returns a token."
    )
    @GetMapping("/")
    public JWTokenDTO getAuthToken(@RequestBody UserAuthenticateDTO user) {
        return authService.createAuthToken(user);
    }

    @Operation(
            summary = "Registers a new user",
            description = "Registers a new user with email, password, confirm password."
    )
    @PostMapping("/")
    public void createUser(@RequestBody UserCreateDTO userData) {
        userService.createNewUser(userData);
    }
}
