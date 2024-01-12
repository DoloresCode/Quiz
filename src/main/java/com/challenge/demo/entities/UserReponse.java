package com.challenge.demo.entities;

import com.challenge.demo.entities.User;
import com.challenge.demo.entities.MatrixQuestion;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

@Entity
public class UserResponse {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @ManyToOne
    private User user;

    @ManyToOne
    private MatrixQuestion matrixQuestion;

    private String response;

    // Getters and Setters

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public MatrixQuestion getMatrixQuestion() {
        return matrixQuestion;
    }

    public void setMatrixQuestion(MatrixQuestion matrixQuestion) {
        this.matrixQuestion = matrixQuestion;
    }

    public String getResponse() {
        return response;
    }

    public void setResponse(String response) {
        this.response = response;
    }
}