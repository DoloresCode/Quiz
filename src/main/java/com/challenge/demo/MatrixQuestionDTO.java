package com.challenge.demo;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class MatrixQuestionDTO {

    private Long id;
    private Long questionId;
    private List<String> rows;
    private List<String> columns;

    // Constructors, getters, and setters

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