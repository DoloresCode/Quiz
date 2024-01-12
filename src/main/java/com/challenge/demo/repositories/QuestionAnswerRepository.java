package com.challenge.demo.repositories;

import com.challenge.demo.entities.QuestionAnswer; 
import org.springframework.data.jpa.repository.JpaRepository;

public interface QuestionAnswerRepository extends JpaRepository<QuestionAnswer, Long> {
}
