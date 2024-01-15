package com.challenge.demo.entities;

import com.fasterxml.jackson.annotation.JsonBackReference;
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
    @JsonBackReference(value="userResponses")
    private Question question;

    @ManyToOne
    @JsonBackReference(value="userResponsesMatrix")
    private MatrixQuestion matrixQuestion;

    @ManyToOne
    private QuestionAnswer questionAnswer;

    @ManyToOne
    private MatrixQuestionRow matrixQuestionRow;
    @ManyToOne
    private MatrixQuestionColumn matrixQuestionColumn;

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

    public Question getQuestion() {
        return question;
    }

    public void setQuestion(Question question) {
        this.question = question;
    }

    public QuestionAnswer getQuestionAnswer() {
        return questionAnswer;
    }

    public void setQuestionAnswer(QuestionAnswer questionAnswer) {
        this.questionAnswer = questionAnswer;
    }

    public MatrixQuestion getMatrixQuestion() {
        return matrixQuestion;
    }

    public void setMatrixQuestion(MatrixQuestion matrixQuestion) {
        this.matrixQuestion = matrixQuestion;
    }

    public MatrixQuestionRow getMatrixQuestionRow() {
        return matrixQuestionRow;
    }

    public void setMatrixQuestionRow(MatrixQuestionRow matrixQuestionRow) {
        this.matrixQuestionRow = matrixQuestionRow;
    }

    public MatrixQuestionColumn getMatrixQuestionColumn() {
        return matrixQuestionColumn;
    }

    public void setMatrixQuestionColumn(MatrixQuestionColumn matrixQuestionColumn) {
        this.matrixQuestionColumn = matrixQuestionColumn;
    }
}