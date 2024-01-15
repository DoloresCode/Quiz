package com.challenge.demo.services;

import com.challenge.demo.entities.MatrixQuestionColumn;
import com.challenge.demo.entities.MatrixQuestionRow;
import com.challenge.demo.repositories.MatrixQuestionColumnRepository;
import com.challenge.demo.repositories.MatrixQuestionRepository;
import com.challenge.demo.repositories.MatrixQuestionRowRepository;
import com.challenge.demo.repositories.UserResponseRepository;
import com.challenge.demo.entities.MatrixQuestion;
import com.challenge.demo.entities.UserResponse;
import com.challenge.demo.entities.User;
import org.springframework.stereotype.Service;

import org.springframework.beans.factory.annotation.Autowired;

import javax.transaction.Transactional;
import java.util.NoSuchElementException;
import java.util.List;

@Service
public class MatrixQuestionService {

    @Autowired
    private MatrixQuestionRepository matrixQuestionRepository;

    @Autowired
    private MatrixQuestionRowRepository matrixQuestionRowRepository;

    @Autowired
    private MatrixQuestionColumnRepository matrixQuestionColumnRepository;

    @Autowired
    private UserResponseRepository userResponseRepository;

    // With this method we will save or update a matrix question
    public MatrixQuestion saveOrUpdateMatrixQuestion(MatrixQuestion matrixQuestion) {
        return matrixQuestionRepository.save(matrixQuestion);
    }

    @Transactional
    public MatrixQuestionRow saveOrUpdateMatrixQuestionRow(MatrixQuestionRow matrixQuestionRow){
        return matrixQuestionRowRepository.save(matrixQuestionRow);
    }

    public MatrixQuestionColumn saveOrUpdateMatrixQuestionColumn(MatrixQuestionColumn matrixQuestionColumn){
        return matrixQuestionColumnRepository.save(matrixQuestionColumn);
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

    public List<MatrixQuestion> findAll() {
        return matrixQuestionRepository.findAll();
    }
}