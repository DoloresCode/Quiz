package com.challenge.demo.repositories;

import com.challenge.demo.entities.UserResponse;
import org.springframework.data.jpa.repository.JpaRepository;

// It retrieves the User entity by its UUID.
public interface UserResponseRepository extends JpaRepository<UserResponse, Long> {
}