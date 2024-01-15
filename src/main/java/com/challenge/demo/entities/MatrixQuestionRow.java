package com.challenge.demo.entities;

import com.fasterxml.jackson.annotation.JsonBackReference;
import org.hibernate.validator.constraints.Length;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotBlank;

@Entity
@Table(name = "matrix_question_row")
public class MatrixQuestionRow {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @ManyToOne(optional = true,fetch = FetchType.EAGER)
    @JoinColumn(name = "matrix_question_id")
    @JsonBackReference(value="rows")
    private MatrixQuestion matrixQuestion;

    @NotBlank
    @Length(min = 0, max = 250)
    private String row;

    private int index;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public MatrixQuestion getMatrixQuestion() {
        return matrixQuestion;
    }

    public void setMatrixQuestion(MatrixQuestion matrixQuestion) {
        this.matrixQuestion = matrixQuestion;
    }

    public String getRow() {
        return row;
    }

    public void setRow(String row) {
        this.row = row;
    }

    public int getIndex() {
        return index;
    }

    public void setIndex(int index) {
        this.index = index;
    }
}
