package com.challenge.demo.repositories;

import com.challenge.demo.entities.Question;
import com.challenge.demo.entities.MatrixQuestion;

import com.challenge.demo.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.data.jpa.repository.Query;

import java.util.Optional;

// It finds a matrix question that the user hasn't answered yet
@Repository
public interface MatrixQuestionRepository extends JpaRepository<MatrixQuestion, Long> {
    @Query("SELECT mq FROM MatrixQuestion mq")
    Optional<MatrixQuestion> findUniqueQuestionForUser(User user);
}
