package com.challenge.demo.entities.dtos;

import com.challenge.demo.entities.Question;
import com.challenge.demo.entities.QuestionAnswer;
import com.challenge.demo.entities.Site;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class QuestionDTO {

	private Long questionId;
	private Long siteId;
	private String question;
	private String type; // field for type of question
	private List<QuestionAnswer> answers = new ArrayList<>();

	// This are getter and setter for the type field.
    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

	public static QuestionDTO build(Question question) {
		final QuestionDTO obj = new QuestionDTO();
		obj.setSiteId(question.getSite().getSiteId());
		obj.setQuestionId(question.getQuestionId());
		obj.setQuestion(question.getQuestion());
		obj.setType(question.getType());
		obj.setAnswers(question.getAnswers());
		return obj;
	}

	public static List<QuestionDTO> build(List<Question> questions) {
		final List<QuestionDTO> ret = new ArrayList<>();

		for (Question question : questions) {
			ret.add(build(question));
		}

		return ret;
	}

	// Create a new Question entity from the QuestionDTO and Site objects
	public static Question createQuestion(final QuestionDTO incomingQuestion, final Site site) {
		final Question newQuestion = new Question();
		newQuestion.setSite(site);
		newQuestion.setQuestion(incomingQuestion.getQuestion());
		newQuestion.setType(incomingQuestion.getType());
		newQuestion.setCreatedAt(new Date());
		newQuestion.setUpdatedAt(new Date());
		return newQuestion;
	}

	public Long getSiteId() {
		return siteId;
	}

	public void setSiteId(final Long siteId) {
		this.siteId = siteId;
	}

	public String getQuestion() {
		return question;
	}

	public void setQuestion(final String question) {
		this.question = question;
	}

	public List<QuestionAnswer> getAnswers() {
		return answers;
	}

	public void setAnswers(List<QuestionAnswer> answers) {
		this.answers = answers;
	}

	public Long getQuestionId() {
		return questionId;
	}

	public void setQuestionId(Long questionId) {
		this.questionId = questionId;
	}
}
