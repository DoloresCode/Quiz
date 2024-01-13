package com.challenge.demo.controllers;


import com.challenge.demo.services.MatrixQuestionService;
import com.challenge.demo.services.UserService;
import com.challenge.demo.entities.MatrixQuestion;
import com.challenge.demo.entities.Question;
import com.challenge.demo.entities.User;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;


@RestController
@RequestMapping("/matrix-questions")
public class MatrixQuestionController {

    @Autowired
    private MatrixQuestionService matrixQuestionService;

    @Autowired
    private UserService userService;

    @PostMapping
    public ResponseEntity<MatrixQuestion> createOrUpdateMatrixQuestion(@RequestBody MatrixQuestion matrixQuestion) {
        MatrixQuestion savedMatrixQuestion = matrixQuestionService.saveOrUpdateMatrixQuestion(matrixQuestion);
        return new ResponseEntity<>(savedMatrixQuestion, HttpStatus.CREATED);
    }

    // Create a new row for MatrixQuestion with a POST mapping method to /matrix-questions
    // Use URL like /matrix-questions/{matrixQuestionID}/rows - "http://localhost:8080/matrix-questions/1"
    @PostMapping("/{matrixQuestionID}/rows")
    public ResponseEntity<MatrixQuestion> createRow(@PathVariable Long matrixQuestionID, @RequestBody String row) {
        MatrixQuestion matrixQuestion = matrixQuestionService.getMatrixQuestionById(matrixQuestionID);
        matrixQuestion.getRows().add(row);
        MatrixQuestion updatedMatrixQuestion = matrixQuestionService.saveOrUpdateMatrixQuestion(matrixQuestion);
        return new ResponseEntity<>(updatedMatrixQuestion, HttpStatus.CREATED);
    }

    // Create a new column for MatrixQuestion with a POST mapping method to /matrix-questions
    // Use URL like /matrix-questions/{matrixQuestionID}/columns - "http://localhost:8080/matrix-questions/1"
    @PostMapping("/{matrixQuestionID}/columns")
    public ResponseEntity<MatrixQuestion> createColumn(@PathVariable Long matrixQuestionID, @RequestBody String column) {
        MatrixQuestion matrixQuestion = matrixQuestionService.getMatrixQuestionById(matrixQuestionID);
        matrixQuestion.getColumns().add(column);
        MatrixQuestion updatedMatrixQuestion = matrixQuestionService.saveOrUpdateMatrixQuestion(matrixQuestion);
        return new ResponseEntity<>(updatedMatrixQuestion, HttpStatus.CREATED);
    }

    // Use URL like /matrix_questions/{id}/rows - "http://localhost:8080/matrix-questions/1"
    @GetMapping("/{id}/rows")
    public ResponseEntity<List<String>> getMatrixQuestionRows(@PathVariable(value = "id") Long matrixQuestionId) {
        MatrixQuestion matrixQuestion = matrixQuestionService.getMatrixQuestionById(matrixQuestionId);
        return new ResponseEntity<>(matrixQuestion.getRows(), HttpStatus.OK);
    }

    // Use URL like /matrix_questions/{id}/columns - "http://localhost:8080/matrix-questions/1"
    @GetMapping("/{id}/columns")
    public ResponseEntity<List<String>> getMatrixQuestionColumns(@PathVariable(value = "id") Long matrixQuestionId) {
        MatrixQuestion matrixQuestion = matrixQuestionService.getMatrixQuestionById(matrixQuestionId);
        return new ResponseEntity<>(matrixQuestion.getColumns(), HttpStatus.OK);
    }

    // update the specified row for the specified matrix question
    // Use URL like /matrix-questions/{matrixQuestionID}/rows/{rowIndex} - "http://localhost:8080/matrix-questions/1/rows/0"
    @PutMapping("/{matrixQuestionID}/rows/{rowIndex}")
    public ResponseEntity<MatrixQuestion> updateRow(@PathVariable Long matrixQuestionID, @PathVariable int rowIndex, @RequestBody String updatedRow) {
        MatrixQuestion matrixQuestion = matrixQuestionService.getMatrixQuestionById(matrixQuestionID);
        matrixQuestion.getRows().set(rowIndex, updatedRow);
        MatrixQuestion updatedMatrixQuestion = matrixQuestionService.saveOrUpdateMatrixQuestion(matrixQuestion);
        return new ResponseEntity<>(updatedMatrixQuestion, HttpStatus.OK);
    }

    // update the specified column for the specified matrix question
    // Use URL like /matrix-questions/{matrixQuestionID}/columns/{columnIndex} -  "http://localhost:8080/matrix-questions/1/columns/0"
    @PutMapping("/{matrixQuestionID}/columns/{columnIndex}")
    public ResponseEntity<MatrixQuestion> updateColumn(@PathVariable Long matrixQuestionID, @PathVariable int columnIndex, @RequestBody String updatedColumn) {
        MatrixQuestion matrixQuestion = matrixQuestionService.getMatrixQuestionById(matrixQuestionID);
        matrixQuestion.getColumns().set(columnIndex, updatedColumn);
        MatrixQuestion updatedMatrixQuestion = matrixQuestionService.saveOrUpdateMatrixQuestion(matrixQuestion);
        return new ResponseEntity<>(updatedMatrixQuestion, HttpStatus.OK);
    }

    // delete the specified row for the specified matrix question
    // Use URL like /matrix-questions/{matrixQuestionID}/rows/{rowIndex}
    @DeleteMapping("/{matrixQuestionID}/rows/{rowIndex}")
    public ResponseEntity<Void> deleteRow(@PathVariable Long matrixQuestionID, @PathVariable int rowIndex) {
        MatrixQuestion matrixQuestion = matrixQuestionService.getMatrixQuestionById(matrixQuestionID);
        matrixQuestion.getRows().remove(rowIndex);
        matrixQuestionService.saveOrUpdateMatrixQuestion(matrixQuestion);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    // delete the specified column for the specified matrix question
    // Use URL like /matrix-questions/{matrixQuestionID}/columns/{columnIndex}
    @DeleteMapping("/{matrixQuestionID}/columns/{columnIndex}")
    public ResponseEntity<Void> deleteColumn(@PathVariable Long matrixQuestionID, @PathVariable int columnIndex) {
        MatrixQuestion matrixQuestion = matrixQuestionService.getMatrixQuestionById(matrixQuestionID);
        matrixQuestion.getColumns().remove(columnIndex);
        matrixQuestionService.saveOrUpdateMatrixQuestion(matrixQuestion);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @GetMapping("/{userUuid}/{siteUuid}")
    public ResponseEntity<MatrixQuestion> getQuestionForUser(@PathVariable UUID userUuid,@PathVariable UUID siteUuid) {
        User user = userService.createUserWithUUID(userUuid);
        MatrixQuestion question = matrixQuestionService.getNextQuestionForUser(user);
        return new ResponseEntity<>(question, HttpStatus.OK);
    }
}