package com.challenge.demo.entities;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import org.hibernate.validator.constraints.Length;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Objects;
import java.util.Set;

@Entity
@Table(name = "matrix_question")
@JsonIgnoreProperties(value= {"rows","columns"})
public class MatrixQuestion implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    // There is Many-to-One relationship between Site and Question. A site can have many questions.
    @ManyToOne(optional = false, fetch = FetchType.EAGER)
    @JoinColumn(name = "site_id", referencedColumnName = "site_id")
    private Site site;

    @NotBlank
    @Length(min = 0, max = 250)
    private String matrixQuestion;

    @NotBlank
    @Length(min = 0, max = 250)
    private String rowHeader;

    @NotBlank
    @Length(min = 0, max = 250)
    private String columnHeader;

    @Column(nullable = false, updatable = false)
    @Temporal(TemporalType.TIMESTAMP)
    @CreatedDate
    private Date createdAt;

    @Column(nullable = false)
    @Temporal(TemporalType.TIMESTAMP)
    @LastModifiedDate
    private Date updatedAt;

    @OneToMany(mappedBy = "matrixQuestion", fetch = FetchType.EAGER)
    @JsonManagedReference(value="rows")
    private List<MatrixQuestionRow> rows = new ArrayList<>();

    @OneToMany(mappedBy = "matrixQuestion", fetch = FetchType.EAGER)
    @JsonManagedReference(value="columns")
    private Set<MatrixQuestionColumn> columns = new HashSet<>();

    @OneToMany(mappedBy = "question", fetch = FetchType.EAGER)
    @JsonManagedReference(value="userResponsesMatrix")
    private Set<UserResponse> userResponses = new HashSet<>();


    // Constructors
    public MatrixQuestion() {}

    // Standard getters and setters
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Site getSite() {
        return site;
    }

    public void setSite(Site site) {
        this.site = site;
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

    public Set<UserResponse> getUserResponses() {
        return userResponses;
    }

    public void setUserResponses(Set<UserResponse> userResponses) {
        this.userResponses = userResponses;
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

    // equals and hashCode methods
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        MatrixQuestion that = (MatrixQuestion) o;

        if (!Objects.equals(id, that.id)) return false;
        return Objects.equals(matrixQuestion, that.matrixQuestion);
    }

    @Override
    public int hashCode() {
        int result = id != null ? id.hashCode() : 0;
        result = 31 * result + (matrixQuestion != null ? matrixQuestion.hashCode() : 0);
        return result;
    }

}
