package com.challenge.demo.entities;

import com.challenge.demo.entities.Question;

import com.fasterxml.jackson.annotation.JsonManagedReference;
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
import java.util.HashSet;
import java.util.List;
import java.util.Objects;
import java.util.Set;


@Entity
@Table(name = "question")
// the createAt and updateAt fields will automatically be populated whenever an entity is created or updated
@EntityListeners(AuditingEntityListener.class)
public class Question implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "question_id")
	private Long questionId;

	// There is Many-to-One relationship between Site and Question. A site can have many questions.
	@ManyToOne(optional = false, fetch = FetchType.EAGER)
	@JoinColumn(name = "site_id", referencedColumnName = "site_id")
	private Site site;

	// The question field is required and must be between 0 and 250 characters long.
	@NotBlank
	@Length(min = 0, max = 250)
	private String question;

	// Type of question. Example: trivia, poll, checkbox, matrix
	private String type;

	// There is One-to-Many relationship between Question and QuestionAnswer. A question can have many answers.
	@OneToMany(mappedBy = "question", fetch = FetchType.EAGER)
	@JsonManagedReference(value="answers")
	private List<QuestionAnswer> answers = new ArrayList<>();

	@OneToMany(mappedBy = "question", fetch = FetchType.EAGER)
	@JsonManagedReference(value="userResponses")
	private Set<UserResponse> userResponses = new HashSet<>();

	// The createdAt and updatedAt fields will automatically be populated whenever an entity is created or updated
	@Column(nullable = false, updatable = false)
	@Temporal(TemporalType.TIMESTAMP)
	@CreatedDate
	private Date createdAt;

	@Column(nullable = false)
	@Temporal(TemporalType.TIMESTAMP)
	@LastModifiedDate
	private Date updatedAt;

	public String getQuestion() {
		return question;
	}

	public void setQuestion(String question) {
		this.question = question;
	}

	public Long getQuestionId() {
		return questionId;
	}

	// Getter and setter for the type field
	public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

	@JsonProperty(access = JsonProperty.Access.READ_ONLY)
	public Date getCreatedAt() {
		return createdAt;
	}

	@JsonProperty(access = JsonProperty.Access.READ_ONLY)
	public Date getUpdatedAt() {
		return updatedAt;
	}

	public Site getSite() {
		return site;
	}

	public void setSite(Site site) {
		this.site = site;
	}

	public List<QuestionAnswer> getAnswers() {
		return answers;
	}

	public void setQuestionId(Long questionId) {
		this.questionId = questionId;
	}

	public void setAnswers(List<QuestionAnswer> answers) {
		this.answers = answers;
	}

	public Set<UserResponse> getUserResponses() {
		return userResponses;
	}

	public void setUserResponses(Set<UserResponse> userResponses) {
		this.userResponses = userResponses;
	}

	public void setCreatedAt(Date createdAt) {
		this.createdAt = createdAt;
	}

	public void setUpdatedAt(Date updatedAt) {
		this.updatedAt = updatedAt;
	}

	@Override
	public boolean equals(final Object o) {
		if (this == o) return true;
		if (o == null || getClass() != o.getClass()) return false;
		final Question question1 = (Question) o;
		return Objects.equals(questionId, question1.questionId) &&
				Objects.equals(site, question1.site) &&
				Objects.equals(question, question1.question) &&
				Objects.equals(answers, question1.answers) &&
				Objects.equals(createdAt, question1.createdAt) &&
				Objects.equals(updatedAt, question1.updatedAt);
	}


	@Override
	public int hashCode() {
		return Objects.hash(questionId, site, question, answers, createdAt, updatedAt);
	}
}
