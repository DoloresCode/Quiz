package com.challenge.demo.services;

import com.challenge.demo.repositories.UserRepository;
import com.challenge.demo.entities.User; 
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    // Create a new user with a given UUID
    public User createUserWithUUID(UUID uuid) {
        // Check if user with the given UUID already exists
        User existingUser = userRepository.findByUuid(uuid);
        if (existingUser != null) {
            return existingUser; // Return existing user if found
        }
        // If user does not exist, create a new one
        User newUser = new User();
        newUser.setUuid(uuid); // Set the UUID of the new user
        return userRepository.save(User);
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