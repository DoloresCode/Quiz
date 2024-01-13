package com.challenge.demo.services;

import com.challenge.demo.repositories.MatrixQuestionRepository;
import com.challenge.demo.repositories.UserResponseRepository;
import com.challenge.demo.entities.MatrixQuestion;
import com.challenge.demo.entities.UserResponse;
import com.challenge.demo.entities.User;
import com.challenge.demo.repositories.UserRepository;
import org.springframework.stereotype.Service;

import org.springframework.beans.factory.annotation.Autowired;
import java.util.NoSuchElementException;
import java.util.Objects;
import java.util.UUID;
import java.util.Optional;

@Service
public class MatrixQuestionService {

    @Autowired
    private MatrixQuestionRepository matrixQuestionRepository;

    @Autowired
    private UserResponseRepository userResponseRepository;

    @Autowired
    private UserRepository userRepository;

    // With this method we will save or update a matrix question
    public MatrixQuestion saveOrUpdateMatrixQuestion(MatrixQuestion matrixQuestion) {
        return matrixQuestionRepository.save(matrixQuestion);
    }

    // Creates a new user if one does not exist (use the save method from UserRepository to do it), and then get the next question for that user
    public MatrixQuestion serveQuestion(UUID userUUID) {
        User user = userRepository.findByUuid(userUUID);
        if (user == null) {
            user = new User();
            user.setUuid(userUUID);
            userRepository.save(user);
        }

        Optional<MatrixQuestion> question = matrixQuestionRepository.findUniqueQuestionForUser(user);
        if (!question.isPresent()) {
            // Resets the list of questions the user has seen and get a new question
            userResponseRepository.deleteByUser(user);
            question = matrixQuestionRepository.findUniqueQuestionForUser(user);
        }
        return question.get();
    }

    // Method to capture a user's response to a question
    public UserResponse captureResponse(UserResponse response) {
        return userResponseRepository.save(response);
    }

    public MatrixQuestion getNextQuestionForUser(User user) {
        return new MatrixQuestion();
    }

    // Method to get a MatrixQuestion by its ID from the MatrixQuestionRepository. If the MatrixQuestion does not exist, it throws a NoSuchElementException is thrown with a message indicating the ID of the MatrixQuestion that could not be found.
    public MatrixQuestion getMatrixQuestionById(Long id) {
        return matrixQuestionRepository.findById(id)
            .orElseThrow(() -> new NoSuchElementException("MatrixQuestion not found with id : " + id));
    }
}