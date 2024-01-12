package com.challenge.demo.repositories;

import com.challenge.demo.entities.Question;
import com.challenge.demo.entities.MatrixQuestion;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


// It finds a matrix question that the user hasn't answered yet
@Repository
public interface MatrixQuestionRepository extends JpaRepository<MatrixQuestion, Long> {
    @Query("SELECT mq FROM MatrixQuestion mq WHERE mq NOT IN (SELECT ur.question FROM UserResponse ur WHERE ur.user = :user)")
    Optional<MatrixQuestion> findUniqueQuestionForUser(User user);
}
