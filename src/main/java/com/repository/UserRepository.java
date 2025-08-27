package com.repository;

import java.util.List;
import java.util.Optional;

import com.entities.User;
import com.entities.UserRole;

public interface UserRepository {
	User save(User user);
    Optional<User> findById(Integer id);
    Optional<User> findByUsername(String username);
    Optional<User> findByEmail(String email);
    List<User> findAll();
    List<User> findByRole(UserRole role);
    List<User> findByIsActive(Boolean isActive);
    void deleteById(Integer id);
    boolean existsByUsername(String username);
    boolean existsByEmail(String email);
}
