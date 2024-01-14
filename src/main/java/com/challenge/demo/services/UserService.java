package com.challenge.demo.services;

import com.challenge.demo.entities.MatrixQuestion;
import com.challenge.demo.entities.Question;
import com.challenge.demo.entities.Site;
import com.challenge.demo.entities.dtos.NextQuestionDTO;
import com.challenge.demo.repositories.MatrixQuestionRepository;
import com.challenge.demo.repositories.QuestionRepository;
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
    @Autowired
    QuestionRepository questionRepository;
    @Autowired
    MatrixQuestionRepository matrixQuestionRepository;

    // Create a new user with a given UUID we included the Site parameter too
    public User createUserWithUUID(UUID uuid, Site site) {
        // Check if user with the given UUID already exists
        User existingUser = userRepository.findByUuid(uuid);
        if (existingUser != null) {
            return existingUser; // Return existing user if found
        }
        // If user does not exist, create a new one
        User newUser = new User();
        newUser.setUuid(uuid); // Set the UUID of the new user
        newUser.setSite(site);
        return userRepository.save(newUser);
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

    // Method to find next question for a user using their ID
    public Optional<NextQuestionDTO> findNextQuestion(Long userId) {
        List<Question> questions = questionRepository.findNextQuestion(userId);
        if(questions==null || questions.isEmpty()){
            return findNextMatrixQuestion(userId);
        }
        Question nextQuestion = questions.get(0);
        return Optional.of(NextQuestionDTO.fromQuestion(nextQuestion));
    }

     // Method to find next Matrix question for a user using their ID
    private Optional<NextQuestionDTO> findNextMatrixQuestion(Long userId) {
        List<MatrixQuestion> questions = matrixQuestionRepository.findNextQuestion(userId);
        if(questions==null || questions.isEmpty()){
            return Optional.empty();
        }
        MatrixQuestion nextQuestion = questions.get(0);
        return Optional.of(NextQuestionDTO.fromMatrixQuestion(nextQuestion));
    }
}