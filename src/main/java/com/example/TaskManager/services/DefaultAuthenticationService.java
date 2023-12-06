package com.example.TaskManager.services;

import com.example.TaskManager.dto.authentication.request.UserAuthenticateDTO;
import com.example.TaskManager.dto.authentication.response.JWTokenDTO;
import com.example.TaskManager.utils.JWTokenUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class DefaultAuthenticationService {
    private final UserDetailsService userService;
    private final AuthenticationManager authenticationManager;
    private final JWTokenUtil jwtUtil;

    public JWTokenDTO createAuthToken(UserAuthenticateDTO user) {
        try {
            authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(
                            user.getEmail(),
                            user.getPassword()
                    ));
        } catch (AuthenticationException e) {
            throw new BadCredentialsException("bad credentials");
        }

        UserDetails userDetails = userService.loadUserByUsername(user.getEmail());
        String token = jwtUtil.generateToken(userDetails);

        return JWTokenDTO.builder()
                .token(token)
                .build();
    }
}
