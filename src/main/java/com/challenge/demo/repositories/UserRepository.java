package com.challenge.demo.repositories;

import com.challenge.demo.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.UUID;

// It retrieves the User entity by its UUID.
public interface UserRepository extends JpaRepository<User, Long> {
    User findByUuid(UUID uuid);
}