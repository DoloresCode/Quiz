package com.challenge.demo.entities.dtos;

import com.challenge.demo.entities.MatrixQuestion;
import com.challenge.demo.entities.MatrixQuestionColumn;
import com.challenge.demo.entities.MatrixQuestionRow;
import com.challenge.demo.entities.Question;
import com.challenge.demo.entities.QuestionAnswer;
import com.challenge.demo.entities.Site;

import java.util.Date;
import java.util.List;
import java.util.Set;

public class NextQuestionDTO {

    private Long id;
    private Long siteId;
    private String question;

    private String type;
    private String rowHeader;
    private String columnHeader;

    private Date createdAt;

    private Date updatedAt;

    List<QuestionAnswer> answers;
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

    public String getQuestion() {
        return question;
    }

    public void setQuestion(String question) {
        this.question = question;
    }

    public List<QuestionAnswer> getAnswers() {
        return answers;
    }

    public void setAnswers(List<QuestionAnswer> answers) {
        this.answers = answers;
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

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public static NextQuestionDTO fromQuestion(Question question) {
        NextQuestionDTO dto = new NextQuestionDTO();
        dto.setId(question.getQuestionId());
        dto.setSiteId(question.getSite().getSiteId());
        dto.setQuestion(question.getQuestion());
        dto.setType(question.getType());
        dto.setAnswers(question.getAnswers());
        dto.setCreatedAt(question.getCreatedAt());
        dto.setUpdatedAt(question.getUpdatedAt());
        return dto;
    }

    public static NextQuestionDTO fromMatrixQuestion(MatrixQuestion matrixQuestion) {
        NextQuestionDTO dto = new NextQuestionDTO();
        dto.setId(matrixQuestion.getId());
        dto.setSiteId(matrixQuestion.getSite().getSiteId());
        dto.setQuestion(matrixQuestion.getMatrixQuestion());
        dto.setRowHeader(matrixQuestion.getRowHeader());
        dto.setColumnHeader(matrixQuestion.getColumnHeader());
        dto.setRows(matrixQuestion.getRows());
        dto.setColumns(matrixQuestion.getColumns());
        dto.setCreatedAt(matrixQuestion.getCreatedAt());
        dto.setUpdatedAt(matrixQuestion.getUpdatedAt());
        return dto;
    }

    public static MatrixQuestion toEntity(NextQuestionDTO matrixQuestionDTO, Site site) {
        MatrixQuestion question = new MatrixQuestion();
        question.setId(matrixQuestionDTO.getId());
        question.setSite(site);
        question.setMatrixQuestion(matrixQuestionDTO.getQuestion());
        question.setColumnHeader(matrixQuestionDTO.getColumnHeader());
        question.setRowHeader(matrixQuestionDTO.getRowHeader());
        question.setCreatedAt(new Date());
        question.setUpdatedAt(new Date());
        return question;
    }
}