package com.challenge.demo.entities.dtos;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class MatrixQuestionDTO {

    private Long id;
    private Long questionId;
    private List<String> rows;
    private List<String> columns;

    // Getters
    public Long getId() {
        return id;
    }

    public Long getQuestionId() {
        return questionId;
    }

    public List<String> getRows() {
        return rows;
    }

    public List<String> getColumns() {
        return columns;
    }

    // Setters
    public void setId(Long id) {
        this.id = id;
    }

    public void setQuestionId(Long questionId) {
        this.questionId = questionId;
    }

    public void setRows(List<String> rows) {
        this.rows = rows;
    }

    public void setColumns(List<String> columns) {
        this.columns = columns;
    }

    public static MatrixQuestionDTO fromEntity(MatrixQuestion matrixQuestion) {
        MatrixQuestionDTO dto = new MatrixQuestionDTO();
        dto.setId(matrixQuestion.getId());
        dto.setQuestionId(matrixQuestion.getQuestionId());
        dto.setRows(new ArrayList<>(matrixQuestion.getRows()));
        dto.setColumns(new ArrayList<>(matrixQuestion.getColumns()));
        return dto;
    }

    public static MatrixQuestion toEntity(MatrixQuestionDTO matrixQuestionDTO) {
        MatrixQuestion question = new MatrixQuestion();
        question.setId(matrixQuestionDTO.getId());
        question.setQuestionId(matrixQuestionDTO.getQuestionId());
        question.setRows(new ArrayList<>(matrixQuestionDTO.getRows()));
        question.setColumns(new ArrayList<>(matrixQuestionDTO.getColumns()));
        return question;
    }
}