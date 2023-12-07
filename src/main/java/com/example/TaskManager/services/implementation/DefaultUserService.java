package com.example.TaskManager.services.implementation;

import com.example.TaskManager.dto.authentication.request.UserCreateDTO;
import com.example.TaskManager.exceptions.TaskServiceGeneralException;
import com.example.TaskManager.exceptions.handle.GlobalExceptionHandler;
import com.example.TaskManager.models.User;
import com.example.TaskManager.repositories.UserRepository;
import com.example.TaskManager.services.UserService;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class DefaultUserService implements UserService, UserDetailsService {
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    @Override
    @Transactional
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        User user = userRepository.findByEmail(email).orElseThrow(() -> new UsernameNotFoundException("User is not found."));
        return new org.springframework.security.core.userdetails.User(
                user.getEmail(),
                user.getPassword(),
                List.of()
        );
    }

    @Override
    public UUID getCurrentUserId() throws UsernameNotFoundException{
        Object auth = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        if (!(auth instanceof String)) {
            throw new UsernameNotFoundException("User is not found.");
        }
        return userRepository.findByEmail((String) auth).orElseThrow(() -> new UsernameNotFoundException("User isn't exist."))
                        .getId();
    }

    @Override
    public void createNewUser(UserCreateDTO userData) {
        if (!userData.getPassword().equals(userData.getPasswordConfirm())) {
            throw new TaskServiceGeneralException("Password and password confirm aren't equal");
        }
        userRepository.findByEmail(userData.getEmail()).ifPresent((a) -> {
            throw new TaskServiceGeneralException("Email: " + a.getEmail() + " is already uses by another user.");
        });
        User user = User.builder()
                .email(userData.getEmail())
                .password(passwordEncoder.encode(userData.getPassword()))
                .build();

        userRepository.save(user);
    }

    public User getUserById(UUID userId) throws RuntimeException {
        return userRepository.findById(userId).orElseThrow(() -> new RuntimeException("Not such user"));
    }
}
