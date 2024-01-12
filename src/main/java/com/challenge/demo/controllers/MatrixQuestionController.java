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

    @GetMapping("/{userUuid}/{siteUuid}")
    public ResponseEntity<MatrixQuestion> getQuestionForUser(@PathVariable UUID userUuid) {
        User user = userService.createUserWithUUID(userUuid);
        MatrixQuestion question = matrixQuestionService.getNextQuestionForUser(user);
        return new ResponseEntity<>(question, HttpStatus.OK);
    }

}
