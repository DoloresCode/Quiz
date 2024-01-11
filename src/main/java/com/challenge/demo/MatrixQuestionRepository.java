package com.challenge.demo;

import org.springframework.data.jpa.repository.JpaRepository;

@Repository
public interface MatrixQuestionRepository extends JpaRepository<MatrixQuestion, Long> {
}
