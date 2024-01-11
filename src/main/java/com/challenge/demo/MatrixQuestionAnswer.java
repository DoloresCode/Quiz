package com.challenge.demo;

import com.fasterxml.jackson.annotation.JsonProperty;
import org.hibernate.validator.constraints.Length;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EntityListeners;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotBlank;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
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

    private String selectedRow;
    private String selectedColumn;

    @Column(nullable = false)
    @Temporal(TemporalType.TIMESTAMP)
    private Date createdAt;

    // Getters and setters to accessing and modifying thefields of the entity

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
