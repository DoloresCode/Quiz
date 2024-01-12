package com.challenge.demo.entities;

import javax.persistence.*;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Entity
@Table(name = "matrix_question")
public class MatrixQuestion implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "question_id")
    private Question question;

    @ElementCollection
    @CollectionTable(name = "matrix_question_rows", joinColumns = @JoinColumn(name = "matrix_question_id"))
    @Column(name = "row_value")
    private List<String> rows = new ArrayList<>();

    @ElementCollection
    @CollectionTable(name = "matrix_question_columns", joinColumns = @JoinColumn(name = "matrix_question_id"))
    @Column(name = "column_value")
    private List<String> columns = new ArrayList<>();

    // Constructors
    public MatrixQuestion() {}

    // Standard getters and setters
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Question getQuestion() {
        return question;
    }

    public void setQuestion(Question question) {
        this.question = question;
    }

    public List<String> getRows() {
        return rows;
    }

    public void setRows(List<String> rows) {
        this.rows = rows;
    }

    public List<String> getColumns() {
        return columns;
    }

    public void setColumns(List<String> columns) {
        this.columns = columns;
    }

    // equals and hashCode methods
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        MatrixQuestion that = (MatrixQuestion) o;

        if (!Objects.equals(id, that.id)) return false;
        return Objects.equals(question, that.question);
    }

    @Override
    public int hashCode() {
        int result = id != null ? id.hashCode() : 0;
        result = 31 * result + (question != null ? question.hashCode() : 0);
        return result;
    }

}
