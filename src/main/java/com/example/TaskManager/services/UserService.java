package com.example.TaskManager.services;

import com.example.TaskManager.dto.authentication.request.UserCreateDTO;
import com.example.TaskManager.models.User;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import java.util.UUID;

/**
 * @author Denis Bezrukov
 * @since 12/07/2023
 */
public interface UserService {

    /**
     * This method returns current user.
     * @return {@link User} - Current user.
     * @throws UsernameNotFoundException
     */
    UUID getCurrentUserId() throws UsernameNotFoundException;

    /**
     * This method creates a new user by provided {@link UserCreateDTO}.
     *
     * @param userData
     */
    void createNewUser(UserCreateDTO userData);

    /**
     * This method returns a user by provided identifier.
     *
     * @param userId An user identifier.
     * @return {@link User} - An user
     * @throws RuntimeException
     */
    User getUserById(UUID userId) throws RuntimeException;
}
