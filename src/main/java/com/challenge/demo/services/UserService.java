package com.challenge.demo.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    // Create a new user
    public User createUser(User user) {
        return userRepository.save(user);
    }

    // Update an existing user
    public User updateUser(User user) {
        return userRepository.save(user);
    }

    // Delete a user
    public void deleteUser(Long id) {
        userRepository.deleteById(id);
    }

    // Finds a user by  UUID
    public User findByUuid(UUID uuid) {
        return userRepository.findByUuid(uuid);
    }

    // Finds a user by ID
    public Optional<User> findById(Long id) {
        return userRepository.findById(id);
    }

    // Finds all users
    public List<User> findAll() {
        return userRepository.findAll();
    }
}