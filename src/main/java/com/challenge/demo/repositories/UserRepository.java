package com.challenge.demo.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

// It retrieves the User entity by its UUID.
public interface UserRepository extends JpaRepository<User, Long> {
    User findByUuid(UUID uuid);
}