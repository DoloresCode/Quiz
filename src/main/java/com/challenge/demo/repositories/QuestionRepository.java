package com.challenge.demo.repositories;

import com.challenge.demo.entities.Question;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface QuestionRepository extends JpaRepository<Question, Long> {

	@Query(value = "SELECT q.* FROM questions q WHERE q.site_id = ?1", nativeQuery = true)
	List<Question> findSiteQuestions(Long siteId);

	// Method to find questions by their type
    List<Question> findByType(String type);
}