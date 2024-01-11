package com.challenge.demo;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import java.io.Serializable;
import java.util.Date;
import java.util.Objects;

@Entity
@Table(name = "matrix_question_answer")
public class MatrixQuestionAnswer implements Serializable {
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "matrix_question_answer_id")
    private Long id;

    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    @JoinColumn(name = "matrix_question_id", referencedColumnName = "id")
    private MatrixQuestion matrixQuestion;

    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id", referencedColumnName = "id")
    private User user;

    // Fields to store the selected row and column in the matrix question
    private String selectedRow;
    private String selectedColumn;

    // Timestamp indicating when the answer was created
    @Column(nullable = false)
    @Temporal(TemporalType.TIMESTAMP)
    private Date createdAt;

    // Getters and setters to accessing and modifying thefields of the entity
    // equals and hashCode methods for entity comparison
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        MatrixQuestionAnswer that = (MatrixQuestionAnswer) o;
        return Objects.equals(id, that.id) &&
                Objects.equals(matrixQuestion, that.matrixQuestion) &&
                Objects.equals(user, that.user) &&
                Objects.equals(selectedRow, that.selectedRow) &&
                Objects.equals(selectedColumn, that.selectedColumn);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, matrixQuestion, user, selectedRow, selectedColumn);
    }
}
