package com.challenge.demo.entities.dtos;

import com.challenge.demo.entities.MatrixQuestion;
import com.challenge.demo.entities.MatrixQuestionColumn;
import com.challenge.demo.entities.MatrixQuestionRow;
import com.challenge.demo.entities.Site;

import java.util.Date;
import java.util.List;
import java.util.Set;

public class MatrixQuestionDTO {

    private Long id;
    private Long siteId;
    private String matrixQuestion;
    private String rowHeader;
    private String columnHeader;

    private Date createdAt;

    private Date updatedAt;
    private List<MatrixQuestionRow> rows;
    private Set<MatrixQuestionColumn> columns;

    // Getters
    public Long getId() {
        return id;
    }

    // Setters
    public void setId(Long id) {
        this.id = id;
    }

    public Long getSiteId() {
        return siteId;
    }

    public void setSiteId(Long siteId) {
        this.siteId = siteId;
    }

    public String getMatrixQuestion() {
        return matrixQuestion;
    }

    public void setMatrixQuestion(String matrixQuestion) {
        this.matrixQuestion = matrixQuestion;
    }

    public String getRowHeader() {
        return rowHeader;
    }

    public void setRowHeader(String rowHeader) {
        this.rowHeader = rowHeader;
    }

    public String getColumnHeader() {
        return columnHeader;
    }

    public void setColumnHeader(String columnHeader) {
        this.columnHeader = columnHeader;
    }

    public Date getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(Date createdAt) {
        this.createdAt = createdAt;
    }

    public Date getUpdatedAt() {
        return updatedAt;
    }

    public void setUpdatedAt(Date updatedAt) {
        this.updatedAt = updatedAt;
    }

    public List<MatrixQuestionRow> getRows() {
        return rows;
    }

    public void setRows(List<MatrixQuestionRow> rows) {
        this.rows = rows;
    }

    public Set<MatrixQuestionColumn> getColumns() {
        return columns;
    }

    public void setColumns(Set<MatrixQuestionColumn> columns) {
        this.columns = columns;
    }

    public static MatrixQuestionDTO fromEntity(MatrixQuestion matrixQuestion) {
        MatrixQuestionDTO dto = new MatrixQuestionDTO();
        dto.setId(matrixQuestion.getId());
        dto.setSiteId(matrixQuestion.getSite().getSiteId());
        dto.setMatrixQuestion(matrixQuestion.getMatrixQuestion());
        dto.setRowHeader(matrixQuestion.getRowHeader());
        dto.setColumnHeader(matrixQuestion.getColumnHeader());
        dto.setRows(matrixQuestion.getRows());
        dto.setColumns(matrixQuestion.getColumns());
        dto.setCreatedAt(matrixQuestion.getCreatedAt());
        dto.setUpdatedAt(matrixQuestion.getUpdatedAt());
        return dto;
    }

    public static MatrixQuestion toEntity(MatrixQuestionDTO matrixQuestionDTO, Site site) {
        MatrixQuestion question = new MatrixQuestion();
        question.setId(matrixQuestionDTO.getId());
        question.setSite(site);
        question.setMatrixQuestion(matrixQuestionDTO.getMatrixQuestion());
        question.setColumnHeader(matrixQuestionDTO.getColumnHeader());
        question.setRowHeader(matrixQuestionDTO.getRowHeader());
        question.setCreatedAt(new Date());
        question.setUpdatedAt(new Date());
        return question;
    }
}