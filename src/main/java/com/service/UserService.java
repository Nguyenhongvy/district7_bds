package com.service;

import java.util.List;
import java.util.Optional;

import com.entities.User;
import com.entities.UserRole;

public interface UserService {
	User createUser(User user);
    User updateUser(User user);
    Optional<User> findById(Integer id);
    Optional<User> findByUsername(String username);
    Optional<User> findByEmail(String email);
    List<User> findAll();
    List<User> findByRole(UserRole role);
    List<User> findActiveUsers();
    void deleteUser(Integer id);
    void activateUser(Integer id);
    void deactivateUser(Integer id);
    boolean existsByUsername(String username);
    boolean existsByEmail(String email);
    boolean authenticate(String username, String password);
    String encodePassword(String rawPassword);
}
