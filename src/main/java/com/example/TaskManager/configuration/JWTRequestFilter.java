package com.example.TaskManager.configuration;

import com.example.TaskManager.utils.JWTokenUtil;
import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.SignatureException;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;
import java.util.List;

@Slf4j
@Component
@RequiredArgsConstructor
public class JWTRequestFilter extends OncePerRequestFilter {
    private final JWTokenUtil jwtUtil;
    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        String authHeader = request.getHeader("Authorization");
        String username = null;
        String jwt = null;

        if (authHeader != null && authHeader.startsWith("Bearer ")) {
            try {
                jwt = authHeader.substring(7);
                username = jwtUtil.getUsername(jwt);
            } catch (ExpiredJwtException e ) {
                log.debug("JWT is expired");
            } catch (SignatureException e) {
                log.debug("Signature is wrong");
            }
        }

        SecurityContext securityContext = SecurityContextHolder.getContext();
        if (username != null && securityContext.getAuthentication() == null) {
            UsernamePasswordAuthenticationToken token;
            token = new UsernamePasswordAuthenticationToken(username, null, List.of());
            securityContext.setAuthentication(token);
        }

        filterChain.doFilter(request, response);
    }
}
