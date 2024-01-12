package com.challenge.demo.repositories;

import com.challenge.demo.entities.UserResponse;
import com.challenge.demo.entities.User;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

// It reset the questions for a user. For tht we delete their responses
@Repository
public interface UserResponseRepository extends JpaRepository<UserResponse, Long> {
    @Transactional
    @Modifying
    @Query("DELETE FROM UserResponse ur WHERE ur.user = :user")
    void deleteByUser(User user);
}